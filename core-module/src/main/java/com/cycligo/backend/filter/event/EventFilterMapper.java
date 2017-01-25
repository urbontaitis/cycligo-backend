package com.cycligo.backend.filter.event;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
public class EventFilterMapper {

    EventFilterDto entity2Dto(FilterEvent filter) {
        EventFilterDto dto = new EventFilterDto();
        dto.setId(filter.getId());
        dto.setTitle(filter.getTitle());
        for (FilterChoice filterChoice : filter.getFilterChoices()) {
            dto.getChoices().add(entity2Dto(filterChoice));
        }
        return dto;
    }

    ChoiceDto entity2Dto(FilterChoice filterChoice) {
        ChoiceDto dto = new ChoiceDto();
        dto.setId(filterChoice.getId());
        dto.setName(filterChoice.getName());
        dto.setValue(filterChoice.getValue());
        dto.setCount(0L); //TODO Do we need this?
        return dto;
    }
}
