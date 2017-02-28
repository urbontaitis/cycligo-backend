package com.cycligo.backend.event.race;

import com.cycligo.backend.event.*;
import com.cycligo.backend.location.Location;
import com.cycligo.backend.lookup.Lookup;

import java.time.LocalDateTime;

/**
 * Created by Mindaugas Urbontaitis on 06/02/2017.
 * cycligo-rest-api
 */
public class EventTestHelper {

    protected static EventDto initEventDto() {
        EventDto dto = new EventDto();
        dto.setId(1L);
        dto.setTitle("test-title");
        dto.setDescription("test-description");
        dto.setStarts(LocalDateTime.of(2017,01,01,10,30));
        dto.setEnds(LocalDateTime.of(2017,01,10,00,00));
        dto.setLocation(new LocationDto("test-location"));
        dto.setDiscipline("MTB");
        dto.setCategory("marathon");
        dto.getDetails().add(new EventDetailDto(100, 2000, (double) 50));
        dto.setLinkToEvent("http://cycligo.com");

        return dto;
    }

    protected static Event initEvent() {
        Event event = new Event();
        event.setTitle("test-title");
        event.setDescription("test-description");
        event.setStarts(LocalDateTime.of(2017,01,01,10,30));
//        event.setLocation(new Location());
//        event.setDiscipline(new Lookup());
//        event.setCategory(new Lookup());
//        event.setEventDetails();
        event.setApproved(false);
        event.setPhotoId(100L);
//        event.setCreatedAt();
//        event.setCreatedBy();
//        event.setUpdatedAt();
//        event.setUpdatedAt();
        return event;
    }


}
