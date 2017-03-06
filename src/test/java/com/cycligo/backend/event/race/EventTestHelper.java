package com.cycligo.backend.event.race;

import com.cycligo.backend.event.*;
import com.cycligo.backend.location.Location;
import com.cycligo.backend.lookup.Lookup;
import com.cycligo.backend.lookup.LookupValue;

import java.time.LocalDateTime;
import java.util.Set;

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
        event.setLocation(new Location());
        event.setDiscipline(new Lookup());
        event.setCategory(new LookupValue());
        Set<EventDetail> details = event.getEventDetails();
        details.add(new EventDetail());
        event.setEventDetails(details);

        event.setApproved(false);
        event.setPhotoId(100L);
        event.setCreatedAt(null);
        event.setCreatedBy(null);
        event.setUpdatedAt(null);
        event.setUpdatedAt(null);
        return event;
    }

    public static Location initLoaction() {
//        Location
        return null;
    }

    public static Lookup initLockup(){
        Lookup lookup = new Lookup();
        lookup.setId(-1L);
        lookup.setName("MTB");
        lookup.setValue("test");
        lookup.getLookups().add(initLockupValue());

        return lookup;
    }

    public static LookupValue initLockupValue() {
        LookupValue value = new LookupValue();
        value.setId(-2L);
        value.setName("test-value");
        value.setValue("test-value");
        return value;
    }


}
