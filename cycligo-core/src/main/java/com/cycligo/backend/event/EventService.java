package com.cycligo.backend.event;

import com.cycligo.backend.event.race.EventSearchParams;
import com.cycligo.backend.lookup.LookupRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
@Service
public class EventService {

    private EventRepository eventRepository;
    private LookupRepository lookupRepository;
//    private LookupValueRepository lookupValueRepository;

    @Autowired
    public EventService(EventRepository eventRepository, LookupRepository lookupRepository) {
        this.eventRepository = eventRepository;
        this.lookupRepository = lookupRepository;
    }

    public Page<EventDto> activeRaces(EventSearchParams searchParams, Pageable pageRequest) {
        Predicate test = EventPredicates.eventSearchParamsContainsIgnoreCase(searchParams);

        Page<Event> searchResultPage = eventRepository.findAll(test, pageRequest);

        return EventMapper.mapEntity2DtoPage(pageRequest, searchResultPage);
    }


    @Deprecated
    public ActiveEvent activeRaces(EventSearchParams eventSearchParams) {
        ActiveEvent result = new ActiveEvent();

        Iterable<Event> events = null;
//        if (eventSearchParams.isNotEmpty()) {
//            events = eventRepository.findByEventSearchParams(
//                    eventSearchParams.getDiscipline(),
//                    eventSearchParams.getCategory(),
//                    eventSearchParams.getCountry(),
//                    null,
//                    null
//            );
//        } else {
//            events = eventRepository.findAll();
//        }

        for(Event event  : events) {
            result.getEvents().add((new EventMapper(lookupRepository)).entity2Dto(event));
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

        return (new EventMapper(lookupRepository)).entity2Dto(event);
    }

    public Long save(EventDto input) {
        EventMapper mapper = new EventMapper(lookupRepository);
        Event event = mapper.dto2Entity(input);

        event = eventRepository.save(event);

        return event.getId();
    }
}
