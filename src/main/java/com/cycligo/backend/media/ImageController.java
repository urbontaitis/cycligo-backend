package com.cycligo.backend.media;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by Mindaugas Urbontaitis on 03/02/2017.
 * cycligo-rest-api
 */
@Controller
public class ImageController {

    private ImageRepository imageRepository;

    public ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @RequestMapping(value = "/media/image/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> load(@PathVariable Long id) throws IOException {
        Image image = imageRepository.findOne(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(image.getMediaType()));
        return new ResponseEntity<>(image.getBlob(), headers, HttpStatus.OK);
    }
}
