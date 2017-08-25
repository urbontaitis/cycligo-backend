package com.cycligo.backend.event.race;

import com.cycligo.backend.event.EventDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Mindaugas Urbontaitis on 02/03/2017.
 * cycligo-rest-api
 */
public class EventValidator implements Validator {

    private final Clock clock;

    public EventValidator() {
        this.clock = Clock.systemDefaultZone();
    }

    protected EventValidator(Clock clock) {
        this.clock = clock;
    }

    public boolean supports(Class clazz) {
        return EventDto.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {
        EventDto event = (EventDto) obj;
        LocalDateTime ends = event.getEnds();
        LocalDateTime starts = event.getStarts();
        LocalDateTime today = LocalDateTime.now(clock).with(LocalTime.MIDNIGHT);
        if (starts != null && starts.isBefore(today)) {
            e.rejectValue("starts", "event.starts_date_cannot_be_before_today");
        }

        if (ends != null) {
            if (ends.isBefore(today)) {
                e.rejectValue("ends", "event.ends_date_cannot_be_before_today");
            }
            if(ends.isBefore(starts)) {
                e.rejectValue("ends", "event.ends_date_cannot_be_before_starts");
            }
        }
    }
}
