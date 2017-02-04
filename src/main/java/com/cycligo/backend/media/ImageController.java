package com.cycligo.backend.media;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Mindaugas Urbontaitis on 03/02/2017.
 * cycligo-rest-api
 */
@RestController
public class ImageController {

    private ImageRepository imageRepository;

    public ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @RequestMapping(value = "/media/image/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> get(@PathVariable Long id) throws IOException {
        Image image = imageRepository.findOne(id);
        if (null == image) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(image.getMediaType()));
        return new ResponseEntity<>(image.getValue(), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/media/image", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Long> upload(@RequestParam("image") MultipartFile image) {
        Image upload = new Image();
        try {
            upload.setMediaType(MediaType.IMAGE_PNG_VALUE);
            upload.setValue(image.getBytes());
            upload = imageRepository.save(upload);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(upload.getId(), HttpStatus.OK);
    }
}
