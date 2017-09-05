package com.cycligo.backend.event.race;

import static com.cycligo.backend.event.race.EventTestHelper.initEvent;
import static com.cycligo.backend.event.race.EventTestHelper.initEventDto;
import static com.cycligo.backend.event.race.EventTestHelper.initLookup;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.cycligo.backend.event.Event;
import com.cycligo.backend.event.EventDto;
import com.cycligo.backend.event.EventRepository;
import com.cycligo.backend.event.EventService;
import com.cycligo.backend.lookup.Lookup;
import com.cycligo.backend.lookup.LookupRepository;

/**
 * Created by Mindaugas Urbontaitis on 04/03/2017.
 * cycligo-rest-api
 */
@RunWith(SpringRunner.class)
public class EventServiceTests {

    private EventService testObject;

    private EventRepository eventRepository;
    private LookupRepository lookupRepository;

    @Before
    public void init() {
        eventRepository = mock(EventRepository.class);
        lookupRepository = mock(LookupRepository.class);

        testObject = new EventService(eventRepository, lookupRepository);
    }

    @Test
    public void should() {
        EventDto request = initEventDto();
        Lookup mockLookup = initLookup();
        Event savedEvent = initEvent();
        when(lookupRepository.findByName("MTB")).thenReturn(mockLookup);
        when(eventRepository.save(any(Event.class))).thenReturn(savedEvent);

        testObject.save(request);

        verify(lookupRepository).findByName("MTB");
        verify(eventRepository).save(any(Event.class));
    }
}
