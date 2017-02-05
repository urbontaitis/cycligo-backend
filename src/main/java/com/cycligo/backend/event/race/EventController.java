package com.cycligo.backend.event.race;

import com.cycligo.backend.base.handler.error.ClientErrorInformation;
import com.cycligo.backend.event.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    ActiveEvent getActiveEvents() {
        return eventService.activeRaces();
    }

    @RequestMapping(value = "/events/recent", method = RequestMethod.GET)
    RecentEvents getRecentEvents() {
        return eventService.recentRaces();
    }

    @RequestMapping(value = "/events/filter", method = RequestMethod.GET)
    List<EventDto> filterEvents() {
        throw new NotYetImplementedException("TODO implement events filter");
    }

    @RequestMapping(value = "/events/event/{id}", method = RequestMethod.GET)
    EventDto getRaceProfile(@PathVariable Long id) throws EventNotFoundException {

        return eventService.race(id);
    }

    @RequestMapping(value="/events/event", method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody EventDto input) {
        //TODO add alternative ResponseEntity.noContent().build()
        Long eventId = eventService.save(input);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(eventId).toUri();
        return ResponseEntity.created(location).build();
    }

    @ExceptionHandler(EventNotFoundException.class)
    ResponseEntity<ClientErrorInformation> rulesForRaceEventNotFound(HttpServletRequest req, Exception ex) {
        ClientErrorInformation error = new ClientErrorInformation(ex.toString(), req.getRequestURL());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }
}
