package com.cycligo.backend.event;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
public class RecentEventMapper {

    RecentEvent entity2Dto(Event entity) {
        RecentEvent dto = new RecentEvent();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDate(entity.getStarts());
        dto.setTime(entity.getStarts());
        dto.setPhoto(entity.getPhotoId());

        return dto;
    }
}
