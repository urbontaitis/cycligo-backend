package com.cycligo.backend.comment;

import com.cycligo.backend.base.MvcMockTest;
import com.cycligo.backend.base.handler.error.ValidationError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Mindaugas Urbontaitis on 08/02/2017.
 * cycligo-rest-api
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CommentController.class)
public class CommentCotrollerTests extends MvcMockTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CommentService commentService;

    @Autowired
    private MessageSource messageSource;

    @Test
    public void shouldThrowParentIdIsRequired() throws Exception {
        ValidationError expected = new ValidationError("Validation failed. 1 error(s)");
        expected.addValidationError(messageSource.getMessage("base.parent_id_is_required", null, Locale.getDefault()));
        String expectedJson = json(expected);

        CommentDto requestDto = initCommentDto();
        requestDto.setParentId(null);
        String requestJson = json(requestDto);

        mvc.perform(post("/comments/comment")
                .contentType(getContentType())
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void shouldThrowParentTypeIsRequired() throws Exception {
        ValidationError expected = new ValidationError("Validation failed. 1 error(s)");
        expected.addValidationError(messageSource.getMessage("base.parent_type_is_required", null, Locale.getDefault()));
        String expectedJson = json(expected);

        CommentDto requestDto = initCommentDto();
        requestDto.setParentType(null);
        String requestJson = json(requestDto);

        mvc.perform(post("/comments/comment")
                .contentType(getContentType())
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void shouldThrowCommentCannotBeEmpty() throws Exception {
        ValidationError expected = new ValidationError("Validation failed. 1 error(s)");
        expected.addValidationError(messageSource.getMessage("comments.comment_cannot_be_empty", null, Locale.getDefault()));
        String expectedJson = json(expected);

        CommentDto requestDto = initCommentDto();
        requestDto.setComment("");
        String requestJson = json(requestDto);

        mvc.perform(post("/comments/comment")
                .contentType(getContentType())
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedJson));
    }

    private CommentDto initCommentDto() {
        CommentDto dto = new CommentDto();
        dto.setId(1L);
        dto.setParentId(12L);
        dto.setParentType("test");
        dto.setComment("comment");
        dto.setDate(LocalDateTime.of(2017,01,01,10,30));
        return dto;
    }


}
