package com.cycligo.backend.base.handler.error;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mindaugas Urbontaitis on 08/02/2017.
 * cycligo-rest-api
 */
public class ValidationError {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> errors = new ArrayList<>();

    private final String message;

    public ValidationError(String message) {
        this.message = message;
    }

    public void addValidationError(String error) {
        errors.add(error);
    }

    public List<String> getErrors() {
        return errors;
    }

    public String getMessage() {
        return message;
    }
}
