package com.cycligo.backend.filter.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
@Service
public class EventFilterService {

    private EventFilterRepository eventFilterRepository;
    private EventFilterMapper mapper = new EventFilterMapper();

    @Autowired
    public EventFilterService(EventFilterRepository eventFilterRepository) {
        this.eventFilterRepository = eventFilterRepository;
    }

    @Transactional
    public List<EventFilterDto> fetchAll() {
        List<EventFilterDto> result = new ArrayList<>();
        Iterable<FilterEvent> filters = eventFilterRepository.findAll();
        for (FilterEvent filter : filters) {
            result.add(mapper.entity2Dto(filter));
        }

        return result;
    }

}
