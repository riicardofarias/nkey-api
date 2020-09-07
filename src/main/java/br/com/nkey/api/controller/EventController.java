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

    @GetMapping(value = {"/", ""})
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = {"/", ""})
    public ResponseEntity<?> save(@Valid @RequestBody Event event) {
        return new ResponseEntity<>(eventService.createNewEvent(event), HttpStatus.OK);
    }

    @PutMapping(value = {"/{id}", "{id}"})
    public ResponseEntity<?> update(@PathVariable("id") String id, @Valid @RequestBody Event event) {
        return new ResponseEntity<>(eventService.updateEvent(id, event), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/{id}", "{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
