package com.cycligo.backend.event.race;

import com.cycligo.backend.base.handler.error.ClientErrorInformation;
import com.cycligo.backend.base.handler.error.ValidationException;
import com.cycligo.backend.event.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.hibernate.cfg.NotYetImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    Page<EventDto> getActiveEvents(EventSearchParams searchParams, Pageable pageRequest) {
//        logger.info("search criteria: {}", eventSearchParams);
        return eventService.activeRaces(searchParams, pageRequest);
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
    ResponseEntity<?> add(@Valid @RequestBody EventDto input, BindingResult result) throws ValidationException {
        //TODO add alternative ResponseEntity.noContent().build()
        (new EventValidator()).validate(input, result);
        if (result.hasFieldErrors()) {
            throw new ValidationException(result);
        }

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
