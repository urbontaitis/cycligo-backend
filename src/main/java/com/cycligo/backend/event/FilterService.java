package com.cycligo.backend.event;

import com.cycligo.backend.lookup.Lookup;
import com.cycligo.backend.lookup.LookupRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
@Service
public class FilterService {

    private LookupRepository lookupRepository;

    public FilterService(LookupRepository lookupRepository) {
        this.lookupRepository = lookupRepository;
    }

    @Transactional
    public List<FilterDto> fetchAllEvents() {
        List<FilterDto> result = new ArrayList<>();
        Iterable<Lookup> filters = lookupRepository.findAll();
        for (Lookup lookup : filters) {
            result.add(FilterMapper.entity2Dto(lookup));
        }

        return result;
    }

    public List<ChoiceDto> fetchBlogChoices() {
        Lookup blogLookup = lookupRepository.findByValue("BLOG_CATEGORIES");
        List<ChoiceDto> values = FilterMapper.mapEntities2Dtos(blogLookup.getLookups());

        return values;
    }
}
