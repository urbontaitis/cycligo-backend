package com.cycligo.backend.filter.event;

import com.cycligo.backend.lookup.Lookup;
import com.cycligo.backend.lookup.LookupValue;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
public class EventFilterMapper {

    EventFilterDto entity2Dto(Lookup filter) {
        EventFilterDto dto = new EventFilterDto();
        dto.setId(filter.getId());
        dto.setTitle(filter.getName());
        for (LookupValue filterChoice : filter.getLookups()) {
            dto.getChoices().add(entity2Dto(filterChoice));
        }
        return dto;
    }

    ChoiceDto entity2Dto(LookupValue filterChoice) {
        ChoiceDto dto = new ChoiceDto();
        dto.setId(filterChoice.getId());
        dto.setName(filterChoice.getName());
        dto.setValue(filterChoice.getValue());
        dto.setCount(0L); //TODO Do we need this?
        return dto;
    }
}
