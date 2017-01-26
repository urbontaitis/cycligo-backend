package com.cycligo.backend.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
public class ActiveEvent {
    private List<EventDto> events;

    public List<EventDto> getEvents() {
        if (events == null) {
            events = new ArrayList<>();
        }
        return events;
    }

    public void setEvents(List<EventDto> events) {
        this.events = events;
    }
}
