package com.cycligo.backend.media;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Mindaugas Urbontaitis on 04/02/2017.
 * cycligo-rest-api
 */
abstract class ImageHelper {

    @Value("#{'classpath:/images/test.png'}")
    private Resource resource;

     Image create() throws IOException {
        InputStream in = resource.getInputStream();
        byte[] blob = IOUtils.toByteArray(in);

        Image image = new Image();
        image.setBlob(blob);
        image.setMediaType(MediaType.IMAGE_PNG.toString());

        return image;
    }
}
