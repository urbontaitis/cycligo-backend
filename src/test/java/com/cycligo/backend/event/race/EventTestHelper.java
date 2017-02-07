package com.cycligo.backend.event.race;

import com.cycligo.backend.event.Event;
import com.cycligo.backend.event.EventDto;

import java.time.LocalDateTime;

/**
 * Created by Mindaugas Urbontaitis on 06/02/2017.
 * cycligo-rest-api
 */
public class EventTestHelper {

    protected static EventDto initEventDto() {
        return new EventDto(
                1L,
                "test-title",
                "test-description",
                LocalDateTime.of(2017,01,01,10,30),
                "test-location",
                "test-event-type",
                "test-distance",
                "test-elevation",
                "test-ticket-price");
    }

    protected static Event initEvent() {
        Event event = new Event();
        event.setTitle("test-title");
        event.setDescription("test-description");
        event.setDate(LocalDateTime.of(2017,01,01,10,30));
        event.setLocation("test-location");
        event.setEventType("test-event-type");
        event.setDistance("test-distance");
        event.setElevation("test-elevation");
        event.setPrice("test-ticket-price");
        return event;
    }


}
