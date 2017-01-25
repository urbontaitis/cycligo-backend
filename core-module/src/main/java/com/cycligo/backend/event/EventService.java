package com.cycligo.backend.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
@Service
public class EventService {

    private EventRepository eventRepository;
    private EventMapper mapper = new EventMapper();

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    public ActiveEvent activeRaces() {
        ActiveEvent result = new ActiveEvent();

        Iterable<Event> events = eventRepository.findAll();
        for(Event event  : events) {
            result.getEvents().add(mapper.entity2Dto(event));
        }

        return result;
    }

    public RecentEvents recentRaces() {
        RecentEvents result = new RecentEvents();

        return result;
    }

    public EventDto race(Long id) {
        EventDto result = new EventDto();
        result.setId(id);
        return result;
    }

}
