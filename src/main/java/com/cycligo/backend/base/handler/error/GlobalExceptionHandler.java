package com.cycligo.backend.base.handler.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Created by panda on 14/12/2016.
 * cycligo-backend
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ClientErrorInformation> handleException(Exception e){
        ClientErrorInformation error = new ClientErrorInformation(e.getMessage(), null);
        return new ResponseEntity<ClientErrorInformation>(error, HttpStatus.NOT_FOUND);
    }
}
