package com.cycligo.backend.event.race;

import com.cycligo.backend.event.EventDto;
import com.cycligo.backend.event.EventNotFoundException;
import com.cycligo.backend.event.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by Mindaugas Urbontaitis on 04/02/2017.
 * cycligo-rest-api
 */
@RunWith(SpringRunner.class)
@WebMvcTest(EventController.class)
public class EventControllerTests {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));


    @Autowired
    private MockMvc mvc;

    @MockBean
    private EventService eventService;


    @Test
    public void eventNotFound() throws Exception {
        given(eventService.race(1L)).willThrow(new EventNotFoundException(1L));

        mvc.perform(get("/"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(contentType));
    }

    @Test
    public void readSingleEvent() throws Exception {
        EventDto expected = initEvent();
        given(eventService.race(1L)).willReturn(expected);

        mvc.perform(get("/events/event/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is(expected.getTitle())))
                .andExpect(jsonPath("$.description", is(expected.getDescription())))
                .andExpect(jsonPath("$.date", is("2017-01-01 10:30")))
                .andExpect(jsonPath("$.location", is(expected.getLocation())))
                .andExpect(jsonPath("$.eventType", is(expected.getEventType())))
                .andExpect(jsonPath("$.distance", is(expected.getDistance())))
                .andExpect(jsonPath("$.elevation", is(expected.getElevation())))
                .andExpect(jsonPath("$.ticketPrice", is(expected.getTicketPrice())));
    }

    @Test
    public void createEvent() {

    }

    private EventDto initEvent() {
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

}
