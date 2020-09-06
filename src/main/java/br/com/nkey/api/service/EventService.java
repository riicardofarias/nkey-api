package br.com.nkey.api.service;

import br.com.nkey.api.exception.NotFoundException;
import br.com.nkey.api.model.Event;
import br.com.nkey.api.repository.EventRepository;
import br.com.nkey.api.security.UserSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository repository;
    private final UserSession session;

    public EventService(EventRepository repository, UserSession session) {
        this.repository = repository;
        this.session = session;
    }

    public List<Event> findAll() {
        return repository.findAll();
    }

    public Event createNewEvent(Event event){
        event.setUser(session.getLoggedUser());

        return repository.save(event);
    }

    public Event updateEvent(String id, Event eventDto){
        Event event = repository.findById(id).orElseThrow(NotFoundException::new);

        if(!event.getId().equals(id)){
            throw new RuntimeException("Operação inválida");
        }

        event.setName(eventDto.getName());
        event.setDate(eventDto.getDate());

        return repository.save(event);
    }

    public void deleteEvent(String id){
        repository.deleteById(id);
    }
}
