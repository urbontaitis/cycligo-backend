package com.cycligo.backend.media;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Mindaugas Urbontaitis on 04/02/2017.
 * cycligo-rest-api
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ImageController.class)
public class ImageControllerTests extends ImageHelper {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ImageRepository imageRepository;

    @Test
    public void loadShouldReturnImage() throws Exception {
        Image expected = create();
        given(this.imageRepository.findOne(1L))
                .willReturn(expected);

        this.mvc.perform(get("/media/image/1")
                .accept(MediaType.IMAGE_PNG))
                .andExpect(status().isOk())
                .andExpect(content().bytes(expected.getBlob()));
    }
}
