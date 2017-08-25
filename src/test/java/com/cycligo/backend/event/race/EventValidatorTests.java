package com.cycligo.backend.event.race;

import com.cycligo.backend.event.Event;
import com.cycligo.backend.event.EventDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.cycligo.backend.event.race.EventTestHelper.initEventDto;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Mindaugas Urbontaitis on 02/03/2017.
 * cycligo-rest-api
 */
@RunWith(SpringRunner.class)
public class EventValidatorTests {

    private final String TODAY = "2017-03-03T12:12:12.00Z";

    private EventValidator testObject;
    private Clock clock;

    @Before
    public void init() {
        clock = mock(Clock.class);
        testObject = new EventValidator(clock);

        Instant instant = Instant.parse(TODAY);
        when(clock.instant()).thenReturn(instant);
        ZoneId zoneId = ZoneId.systemDefault();
        when(clock.getZone()).thenReturn(zoneId);
    }

    @Test
    public void shouldReturnNotSupported() {
        assertFalse(testObject.supports(Event.class));
    }

    @Test
    public void shouldReturnSupported() {
        assertTrue(testObject.supports(EventDto.class));
    }

    @Test
    public void startsDateCannotBeBeforeToday() {
        EventDto event = initEventDto();
        event.setStarts(LocalDateTime.of(2016,03,01,12,00));
        event.setEnds(LocalDateTime.of(2017,03,04,12,00));
        Errors errors = new BeanPropertyBindingResult(event, "invalidEvent");
        testObject.validate(event, errors);

        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("starts"));
        assertEquals("event.starts_date_cannot_be_before_today", errors.getFieldError("starts").getCode());
    }

    @Test
    public void endsDateCannotBeBeforeToday() {
        EventDto event = initEventDto();
        event.setStarts(LocalDateTime.of(2017,03,03,12,00));
        event.setEnds(LocalDateTime.of(2017,03,01,12,00));
        Errors errors = new BeanPropertyBindingResult(event, "invalidEvent");
        testObject.validate(event, errors);

        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("ends"));
        assertEquals("event.ends_date_cannot_be_before_today", errors.getFieldError("ends").getCode());
    }

    @Test
    public void endsDateCannotBeBeforeStartsDate() {
        EventDto event = initEventDto();
        event.setStarts(LocalDateTime.of(2017,03,06,12,00));
        event.setEnds(LocalDateTime.of(2017,03,05,12,00));
        Errors errors = new BeanPropertyBindingResult(event, "invalidEvent");
        testObject.validate(event, errors);

        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("ends"));
        assertEquals("event.ends_date_cannot_be_before_starts", errors.getFieldError("ends").getCode());
    }
}
