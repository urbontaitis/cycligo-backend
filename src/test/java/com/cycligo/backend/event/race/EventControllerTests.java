package com.cycligo.backend.event.race;

import com.cycligo.backend.base.MvcMockTest;
import com.cycligo.backend.event.EventDto;
import com.cycligo.backend.event.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.cycligo.backend.event.race.EventTestHelper.initEventDto;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Mindaugas Urbontaitis on 04/02/2017.
 * cycligo-rest-api
 */
@RunWith(SpringRunner.class)
@WebMvcTest(EventController.class)
public class EventControllerTests extends MvcMockTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EventService eventService;

    @Test
    public void readSingleEvent() throws Exception {
        EventDto expected = initEventDto();
        given(eventService.race(1L)).willReturn(expected);

        mvc.perform(get("/events/event/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(getContentType()))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is(expected.getTitle())))
                .andExpect(jsonPath("$.description", is(expected.getDescription())))
                .andExpect(jsonPath("$.date", is("2017-01-01T10:30:00")))
                .andExpect(jsonPath("$.location", is(expected.getLocation())))
                .andExpect(jsonPath("$.eventType", is(expected.getEventType())))
                .andExpect(jsonPath("$.distance", is(expected.getDistance())))
                .andExpect(jsonPath("$.elevation", is(expected.getElevation())))
                .andExpect(jsonPath("$.ticketPrice", is(expected.getTicketPrice())));
    }

    @Test
    public void createEvent() throws Exception {
        EventDto expected = initEventDto();
        expected.setId(null);
        String eventJson = json(expected);
        given(eventService.save(expected)).willReturn(1L);

        mvc.perform(post("/events/event")
            .contentType(getContentType())
            .content(eventJson))
            .andExpect(status().isCreated());
    }
}
