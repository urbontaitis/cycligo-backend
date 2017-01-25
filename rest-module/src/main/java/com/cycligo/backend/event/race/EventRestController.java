package com.cycligo.backend.event.race;

import com.cycligo.backend.core.handler.error.ClientErrorInformation;
import com.cycligo.backend.event.ActiveEvent;
import com.cycligo.backend.event.EventDto;
import com.cycligo.backend.event.EventService;
import com.cycligo.backend.event.RecentEvents;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EventRestController {

    private EventService eventService;

    public EventRestController(EventService eventService) {
        this.eventService = eventService;
    }

    @CrossOrigin
    @RequestMapping(value = "/events", method = RequestMethod.GET)
    ActiveEvent getActiveEvents() {
        return eventService.activeRaces();
    }

    @CrossOrigin
    @RequestMapping(value = "/events/recent", method = RequestMethod.GET)
    RecentEvents getRecentEvents() {
        return eventService.recentRaces();
    }

    @CrossOrigin
    @RequestMapping(value = "/events/filter", method = RequestMethod.GET)
    List<EventDto> filterEvents() {
        throw new NotYetImplementedException("TODO implement events filter");
    }

    @CrossOrigin
    @RequestMapping(value = "/events/event/{id}", method = RequestMethod.GET)
    EventDto getRaceProfile(@PathVariable Long id) throws RaceEventNotFoundException {

        return eventService.race(id);
    }

    @ExceptionHandler(RaceEventNotFoundException.class)
    ResponseEntity<ClientErrorInformation> rulesForRaceEventNotFound(HttpServletRequest req, Exception ex) {
        ClientErrorInformation error = new ClientErrorInformation(ex.toString(), req.getRequestURL());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }
}
