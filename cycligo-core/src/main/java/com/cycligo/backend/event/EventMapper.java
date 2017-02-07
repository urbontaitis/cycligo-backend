package com.cycligo.backend.event;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
class EventMapper {

    EventDto entity2Dto(Event event) {
        EventDto dto = new EventDto();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setDate(event.getDate());
        dto.setLocation(event.getLocation());
        dto.setEventType(event.getEventType());
        dto.setDistance(event.getDistance());
        dto.setElevation(event.getElevation());
        dto.setTicketPrice(event.getPrice());
        dto.setDescription(event.getDescription());

        return dto;
    }

    Event dto2Entity(EventDto dto) {
        Event entity = new Event();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDate(dto.getDate());
        entity.setDescription(dto.getDescription());
        entity.setDistance(dto.getDistance());
        entity.setElevation(dto.getElevation());
        entity.setEventType(dto.getEventType());
        entity.setLocation(dto.getLocation());
        entity.setPrice(dto.getTicketPrice());
        return entity;
    }

}
