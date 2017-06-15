package com.cycligo.backend.media;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Mindaugas Urbontaitis on 04/02/2017.
 * cycligo-rest-api
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class ImageRepositoryTests extends ImageHelper {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ImageRepository imageRepository;

    @Test
    public void shouldSaveImage() throws IOException {
        Image image = create();

        imageRepository.save(image);

        assertNotNull("Image was not saved", image.getId());
    }

    @Test
    public void shouldGetImage() throws IOException {
        Image image = create();
        entityManager.persist(image);

        Image actual = imageRepository.findById(image.getId());

        assertNotNull(actual.getValue());
    }
}
