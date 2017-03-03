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

import java.time.LocalDateTime;

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
                .andExpect(jsonPath("$.starts", is("2017-01-01T10:30:00.000Z")))
                .andExpect(jsonPath("$.ends", is("2017-01-10T00:00:00.000Z")))
                .andExpect(jsonPath("$.location.label", is(expected.getLocation().getLabel())))
                .andExpect(jsonPath("$.location.placeId", is(expected.getLocation().getPlaceId())))
                .andExpect(jsonPath("$.location.latitude", is(expected.getLocation().getLatitude())))
                .andExpect(jsonPath("$.location.longitude", is(expected.getLocation().getLongitude())))
                .andExpect(jsonPath("$.discipline", is(expected.getDiscipline())))
                .andExpect(jsonPath("$.category", is(expected.getCategory())))
                .andExpect(jsonPath("$.details[?(@.distance == \'100\' && @.elevation == \'2000\' && @.price == \'50.0\')]").exists())
                .andExpect(jsonPath("$.photo", is(expected.getPhoto())))
                .andExpect(jsonPath("$.linkToEvent", is(expected.getLinkToEvent())));
    }

    @Test
    public void createEvent() throws Exception {
        EventDto expected = initEventDto();
        expected.setId(null);
        expected.setStarts(LocalDateTime.now());
        expected.setEnds(null);
        String eventJson = json(expected);
        given(eventService.save(expected)).willReturn(1L);

        mvc.perform(post("/events/event")
            .contentType(getContentType())
            .content(eventJson))
            .andExpect(status().isCreated());
    }

}
