package com.cycligo.backend.media;

import com.cycligo.backend.base.ParentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.InputStream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
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
    public void shouldGetImage() throws Exception {
        Image expected = create();
        given(this.imageRepository.findById(1L))
                .willReturn(expected);

        this.mvc.perform(get("/media/image/1")
                .accept(MediaType.IMAGE_PNG))
                .andExpect(status().isOk())
                .andExpect(content().bytes(expected.getValue()));
    }

    @Test
    public void shouldUploadImage() throws Exception {
        InputStream img = getResource().getInputStream();
        MockMultipartFile file = new MockMultipartFile(
                "photo",
                "test.png",
                "multipart/form-data",
                img);
        Image expected = create();
        expected.setId(100L);
        given(this.imageRepository.save(any(Image.class))).willReturn(expected);


        this.mvc.perform(fileUpload("/media/image").file(file))
                .andExpect(status().isOk())
                .andExpect(content().string("100"));

//        then(this.imageRepository).should().save(expected);
    }
}
