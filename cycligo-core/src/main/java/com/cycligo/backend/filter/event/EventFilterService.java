package com.cycligo.backend.filter.event;

import com.cycligo.backend.lookup.Lookup;
import com.cycligo.backend.lookup.LookupRepository;
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

    private LookupRepository lookupRepository;
    private EventFilterMapper mapper = new EventFilterMapper();

    @Autowired
    public EventFilterService(LookupRepository lookupRepository) {
        this.lookupRepository = lookupRepository;
    }

    @Transactional
    public List<EventFilterDto> fetchAll() {
        List<EventFilterDto> result = new ArrayList<>();
        Iterable<Lookup> filters = lookupRepository.findAll();
        for (Lookup lookup : filters) {
            result.add(mapper.entity2Dto(lookup));
        }

        return result;
    }

}
