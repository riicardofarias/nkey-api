package br.com.nkey.api.service;

import br.com.nkey.api.exception.NotFoundException;
import br.com.nkey.api.model.Event;
import br.com.nkey.api.repository.EventRepository;
import br.com.nkey.api.security.UserSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository repository;
    private final UserSession session;

    public EventService(EventRepository repository, UserSession session) {
        this.repository = repository;
        this.session = session;
    }

    /**
     * Busca todos os eventos
     * @return List<Event>
     */
    public List<Event> findAll() {
        return repository.findAll();
    }

    /**
     * Busca o evento pelo ID
     * @param id Identificador do evento
     * @return Event
     */
    public Optional<Event> findById(String id) {
        return repository.findById(id);
    }

    /**
     * Cria um novo evento
     * @param event Event
     * @return Event
     */
    public Event createNewEvent(Event event){
        event.setUser(session.getLoggedUser());

        return repository.save(event);
    }

    /**
     * Atualiza o evento
     * @param id Identificador do evento
     * @param eventDto Evento
     * @return Event
     */
    public Event updateEvent(String id, Event eventDto){
        Event event = repository.findById(id).orElseThrow(NotFoundException::new);

        if(!event.getId().equals(id)){
            throw new RuntimeException("Operação inválida");
        }

        event.setName(eventDto.getName());
        event.setDate(eventDto.getDate());

        return repository.save(event);
    }

    /**
     * Remove um evento pelo código de identificação
     * @param id Identificador do evento
     */
    public void deleteEvent(String id){
        repository.deleteById(id);
    }
}
