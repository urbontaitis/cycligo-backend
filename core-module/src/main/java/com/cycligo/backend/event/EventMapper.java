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

        return dto;
    }

}
