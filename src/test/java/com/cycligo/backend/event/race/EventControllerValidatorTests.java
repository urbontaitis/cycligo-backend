package com.cycligo.backend.event.race;

import com.cycligo.backend.base.MvcMockTest;
import com.cycligo.backend.base.handler.error.ValidationError;
import com.cycligo.backend.event.EventDto;
import com.cycligo.backend.event.EventService;
import com.cycligo.backend.event.LocationDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.cycligo.backend.event.race.EventTestHelper.initEventDto;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Mindaugas Urbontaitis on 02/03/2017.
 * cycligo-rest-api
 */
@RunWith(SpringRunner.class)
@WebMvcTest(EventController.class)
public class EventControllerValidatorTests  extends MvcMockTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EventService eventService;

    @Test
    public void shouldThrowTitleIsRequired() throws Exception {
        ValidationError expected = createValidationError("title", "event.title_is_required");
        String expectedJson = json(expected);

        EventDto requestDto = initEventDto();
        requestDto.setTitle("");
        String requestJson = json(requestDto);

        mvc.perform(post("/events/event")
                .contentType(getContentType())
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void shouldThrowStartsIsRequired() throws Exception {
        ValidationError expected = createValidationError("starts", "event.starts_date_is_required");
        String expectedJson = json(expected);

        EventDto requestDto = initEventDto();
        requestDto.setStarts(null);
        String requestJson = json(requestDto);

        mvc.perform(post("/events/event")
                .contentType(getContentType())
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void shouldThrowLocationIsRequired() throws Exception {
        ValidationError expected = createValidationError("location", "event.location_is_required");
        String expectedJson = json(expected);

        EventDto requestDto = initEventDto();
        requestDto.setLocation(null);
        String requestJson = json(requestDto);

        mvc.perform(post("/events/event")
                .contentType(getContentType())
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void shouldThrowLocationLabelIsRequired() throws Exception {
        ValidationError expected = createValidationError("location", "event.location_is_required");
        String expectedJson = json(expected);

        EventDto requestDto = initEventDto();
        LocationDto locationDto = requestDto.getLocation();
        locationDto.setLabel("");
        requestDto.setLocation(locationDto);
        String requestJson = json(requestDto);

        mvc.perform(post("/events/event")
                .contentType(getContentType())
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void shouldThrowDisciplineIsRequired() throws Exception {
        ValidationError expected = createValidationError("discipline", "event.discipline_is_required");
        String expectedJson = json(expected);

        EventDto requestDto = initEventDto();
        requestDto.setDiscipline("");
        String requestJson = json(requestDto);

        mvc.perform(post("/events/event")
                .contentType(getContentType())
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void shouldThrowCategoryIsRequired() throws Exception {
        ValidationError expected = createValidationError("category", "event.category_is_required");
        String expectedJson = json(expected);

        EventDto requestDto = initEventDto();
        requestDto.setCategory("");
        String requestJson = json(requestDto);

        mvc.perform(post("/events/event")
                .contentType(getContentType())
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void shouldThrowLinkToEventIsRequired() throws Exception {
        ValidationError expected = createValidationError("linkToEvent", "event.link_to_event_is_required");
        String expectedJson = json(expected);

        EventDto requestDto = initEventDto();
        requestDto.setLinkToEvent("");
        String requestJson = json(requestDto);

        mvc.perform(post("/events/event")
                .contentType(getContentType())
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedJson));
    }

}
