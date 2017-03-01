package com.cycligo.backend.event;

import com.cycligo.backend.location.Location;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
class EventMapper {

    EventDto entity2Dto(Event event) {
        EventDto dto = new EventDto();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setStarts(event.getStarts());
        dto.setEnds(event.getEnds());
        dto.setPhoto(event.getPhotoId());
        dto.setDescription(event.getDescription());
        dto.setDiscipline(event.getDiscipline().getName());
        dto.setCategory(event.getCategory().getName());
        dto.setLocation(entity2Dto(event.getLocation()));
        dto.setLinkToEvent(event.getLinkToEvent());
        for (EventDetail eventDetail : event.getEventDetails()) {
            dto.getDetails().add(entity2Dto(eventDetail));
        }

        return dto;
    }

    EventDetailDto entity2Dto(EventDetail eventDetail) {
        EventDetailDto dto = new EventDetailDto();
        dto.setDistance(eventDetail.getDistance());
        dto.setElevation(eventDetail.getElevation());
        dto.setPrice(eventDetail.getPrice().doubleValue());
        return dto;
    }

    LocationDto entity2Dto(Location entity) {
        LocationDto dto = new LocationDto();
        dto.setLabel(entity.getLabel());
        dto.setLatitude(entity.getLatitude().doubleValue());
        dto.setLongitude(entity.getLongitude().doubleValue());
        dto.setPlaceId(entity.getPlaceId());
        return dto;
    }

    Event dto2Entity(EventDto dto) {
        Event entity = new Event();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
//        entity.setDate(dto.getDate());
        entity.setDescription(dto.getDescription());
//        entity.setDistance(dto.getDistance());
//        entity.setElevation(dto.getElevation());
//        entity.setEventType(dto.getEventType());
//        entity.setLocation(dto.getLocation());
//        entity.setPrice(dto.getTicketPrice());
        return entity;
    }

}
