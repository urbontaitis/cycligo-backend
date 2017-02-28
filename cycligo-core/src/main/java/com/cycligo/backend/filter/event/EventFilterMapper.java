package com.cycligo.backend.filter.event;

import com.cycligo.backend.lookup.Lookup;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
public class EventFilterMapper {

    EventFilterDto entity2Dto(Lookup filter) {
        EventFilterDto dto = new EventFilterDto();
        dto.setId(filter.getId());
        dto.setTitle(filter.getName());
        for (Lookup filterChoice : filter.getLookups()) {
            dto.getChoices().add(entit2Dto(filterChoice));
        }
        return dto;
    }

    ChoiceDto entit2Dto(Lookup filterChoice) {
        ChoiceDto dto = new ChoiceDto();
        dto.setId(filterChoice.getId());
        dto.setName(filterChoice.getName());
        dto.setValue(filterChoice.getValue());
        dto.setCount(0L); //TODO Do we need this?
        return dto;
    }
}
