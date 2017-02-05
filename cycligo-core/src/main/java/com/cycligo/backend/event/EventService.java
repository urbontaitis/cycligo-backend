package com.cycligo.backend.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
@Service
public class EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    public ActiveEvent activeRaces() {
        ActiveEvent result = new ActiveEvent();

        Iterable<Event> events = eventRepository.findAll();
        for(Event event  : events) {
            result.getEvents().add((new EventMapper()).entity2Dto(event));
        }

        return result;
    }

    public RecentEvents recentRaces() {
        RecentEvents result = new RecentEvents();
        //TODO create a query which should return N latest events
        Iterable<Event> events = eventRepository.findAll();
        for(Event event  : events) {
            result.getRecentEvents().add(new RecentEventMapper().entity2Dto(event));
        }

        return result;
    }

    public EventDto race(Long id) throws EventNotFoundException {

        Event event = eventRepository.findById(id).orElseThrow(
                () -> new EventNotFoundException(id));

        return (new EventMapper()).entity2Dto(event);
    }

    public Long save(EventDto input) {
        EventMapper mapper = new EventMapper();
        Event event = mapper.dto2Entity(input);

        event = eventRepository.save(event);

        return event.getId();
    }
}
