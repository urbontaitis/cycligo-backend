package com.cycligo.backend.filter.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
@RestController
public class EventFilterController {

    private EventFilterService eventFilterService;

    @Autowired
    EventFilterController(EventFilterService eventFilterService){
        this.eventFilterService = eventFilterService;
    }

    @CrossOrigin
    @RequestMapping(value = "/filter/events", method = RequestMethod.GET)
    List<EventFilterDto> fetchAll() {
        return eventFilterService.fetchAll();
    }
}
