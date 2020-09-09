package br.com.nkey.api.controller;

import br.com.nkey.api.model.Event;
import br.com.nkey.api.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Retorna todos os eventos
     * @return List<Evento>
     */
    @GetMapping(value = {"/", ""})
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }

    /**
     * Retorna o evento pelo código de identificação
     * @param id Id do evento
     * @return Event
     */
    @GetMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<?> findOne(@PathVariable("id") String id) {
        return new ResponseEntity<>(eventService.findById(id), HttpStatus.OK);
    }

    /**
     * Salva um novo evento
     * @param event Event
     * @return Event
     */
    @PostMapping(value = {"/", ""})
    public ResponseEntity<?> save(@Valid @RequestBody Event event) {
        return new ResponseEntity<>(eventService.createNewEvent(event), HttpStatus.OK);
    }

    /**
     * Atualiza os dados do evento pelo código de identificação
     * @param id Id do evento
     * @param event Event
     * @return Event
     */
    @PutMapping(value = {"/{id}", "{id}"})
    public ResponseEntity<?> update(@PathVariable("id") String id, @Valid @RequestBody Event event) {
        return new ResponseEntity<>(eventService.updateEvent(id, event), HttpStatus.OK);
    }

    /**
     * Remove um evento pelo código de identificação
     * @param id - Id do evento
     * @return Void
     */
    @DeleteMapping(value = {"/{id}", "{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
