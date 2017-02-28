package com.cycligo.backend.media;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "Images")
@RestController
public class ImageController {

    private ImageRepository imageRepository;

    public ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @ApiOperation(value = "Gets a image based on image id and parent data (id and type)",
            notes = "Retrieves a image as byte array",
            response = byte.class)
    @RequestMapping(value = "/media/image/{imageId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> get(@PathVariable Long imageId) throws IOException {
        Image image = imageRepository.findById(imageId);
        if (null == image) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(image.getMediaType()));
        return new ResponseEntity<>(image.getValue(), headers, HttpStatus.OK);
    }

    @ApiOperation(value = "Post a image based on image id and parent data (id and type)",
            notes = "Post a image. When image is stored, returns image id",
            response = Long.class)
    @RequestMapping(value = "/media/image", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Long> upload(@RequestParam("photo") MultipartFile image) {
        Image upload = new Image();
        try {
            upload.setParentType("DELETE");
            upload.setParentId(1L);
            upload.setMediaType(MediaType.IMAGE_JPEG_VALUE);
            upload.setValue(image.getBytes());
            upload = imageRepository.save(upload);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(upload.getId(), HttpStatus.OK);
    }
}
