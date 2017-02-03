package com.cycligo.backend.media;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Mindaugas Urbontaitis on 03/02/2017.
 * cycligo-rest-api
 */
@RestController
public class ImageController {

    @Value("#{'classpath:/images/event-1.png'}")
    private Resource resource;

    @RequestMapping(value = "/media/image/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> load(@PathVariable Long id) throws IOException {
        InputStream in = resource.getInputStream();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);

            return new ResponseEntity<>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
}
