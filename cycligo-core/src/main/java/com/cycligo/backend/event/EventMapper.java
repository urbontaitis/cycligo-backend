package com.cycligo.backend.event;

import com.cycligo.backend.location.Location;
import com.cycligo.backend.lookup.Lookup;
import com.cycligo.backend.lookup.LookupRepository;
import com.cycligo.backend.lookup.LookupValue;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
class EventMapper {

    private LookupRepository repository;

    EventMapper(LookupRepository repository) {
        this.repository = repository;
    }

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
        if (null != eventDetail.getDistance()) {
            dto.setDistance(eventDetail.getDistance().doubleValue());
        }
        if (null != eventDetail.getElevation()) {
            dto.setElevation(eventDetail.getElevation().doubleValue());
        }
        if (null != eventDetail.getPrice()) {
            dto.setPrice(eventDetail.getPrice().doubleValue());
        }
        return dto;
    }

    LocationDto entity2Dto(Location entity) {
        LocationDto dto = new LocationDto();
        dto.setLabel(entity.getLabel());
        if (null != entity.getLatitude()) {
            dto.setLatitude(entity.getLatitude().doubleValue());
        }
        if (null != entity.getLongitude()) {
            dto.setLongitude(entity.getLongitude().doubleValue());
        }
        dto.setPlaceId(entity.getPlaceId());
        return dto;
    }

    Event dto2Entity(EventDto dto) {
        Event entity = new Event();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setStarts(dto.getStarts());
        entity.setEnds(dto.getEnds());
        entity.setDescription(dto.getDescription());
        entity.setPhotoId(dto.getPhoto());
        entity.setLinkToEvent(dto.getLinkToEvent());
        Lookup discipline = getLookup(dto.getDiscipline());
        entity.setDiscipline(discipline);
        entity.setCategory(getLookupValue(dto.getCategory(), discipline.getLookups()));
        entity.setLocation(dto2Entity(dto.getLocation()));
        for (EventDetailDto detail : dto.getDetails()) {
            if (detail.getDistance() != null || detail.getElevation() != null || detail.getPrice() != null) {
                EventDetail eventDetail = dto2Entity(detail);
                entity.addEventDetaiils(eventDetail);
            }
        }

        return entity;
    }

    EventDetail dto2Entity(EventDetailDto dto) {
        EventDetail entity = new EventDetail();
        if(null != dto.getDistance()) {
            entity.setDistance(dto.getDistance().intValue());
        }
        if(null != dto.getElevation()) {
            entity.setElevation(dto.getElevation().intValue());
        }
        if (null != dto.getPrice()) {
            entity.setPrice(new BigDecimal(dto.getPrice()));
        }

        return entity;
    }

    Location dto2Entity(LocationDto dto) {
        Location entity = new Location();
        entity.setLabel(dto.getLabel());
        entity.setPlaceId(dto.getPlaceId());
        if ( null != dto.getLatitude()) {
            entity.setLatitude(new BigDecimal(dto.getLatitude()));
        }
        if (null != dto.getLongitude()) {
            entity.setLongitude(new BigDecimal(dto.getLongitude()));
        }
        entity.setContinentId(null);
        entity.setCountryId(null);
        entity.setCityId(null);

        return entity;
    }

    Lookup getLookup(String name) {
        Lookup lookup = repository.findByName(name);
        if (lookup == null) {
            throw new IllegalArgumentException("Trying to save with non existing property: " + name);
        }
        return lookup;
    }

    // TODO need to implement a more efficient way
    private LookupValue getLookupValue(String category, Set<LookupValue> lookups) {
        for(LookupValue val : lookups) {
            if (category.equals(val.getValue())) {
                return val;
            }
        }

        return null;
    }

}
