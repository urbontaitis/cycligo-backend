package com.cycligo.backend.event;

import com.cycligo.backend.lookup.Lookup;
import com.cycligo.backend.lookup.LookupValue;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
public class FilterMapper {

    static FilterDto entity2Dto(Lookup filter) {
        FilterDto dto = new FilterDto();
        dto.setId(filter.getId());
        dto.setTitle(filter.getName());
        for (LookupValue filterChoice : filter.getLookups()) {
            dto.getChoices().add(entity2Dto(filterChoice));
        }
        return dto;
    }

    static ChoiceDto entity2Dto(LookupValue filterChoice) {
        ChoiceDto dto = new ChoiceDto();
        dto.setId(filterChoice.getId());
        dto.setName(filterChoice.getName());
        dto.setValue(filterChoice.getValue());
        dto.setCount(0L); //TODO Do we need this?
        return dto;
    }

    static List<ChoiceDto> mapEntities2Dtos(Set<LookupValue> lookups) {
        return lookups.stream()
                .map(FilterMapper::entity2Dto)
                .collect(toList());
    }

}
