package com.cycligo.backend.core.handler.error;

import org.springframework.validation.BindingResult;

/**
 * Created by Mindaugas Urbontaitis on 02/03/2017.
 * cycligo-rest-api
 */
public class ValidationException extends Exception{

    private BindingResult bindingResult;

    public ValidationException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
