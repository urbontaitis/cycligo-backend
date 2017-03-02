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
    private List<Error> errors = new ArrayList<>();

    private final String globalMessage;

    public ValidationError(String globalMessage) {
        this.globalMessage = globalMessage;
    }

    public void addValidationError(String field, String error) {
        errors.add(new Error(field, error));
    }

    public List<Error> getErrors() {
        return errors;
    }

    public String getGlobalMessage() {
        return globalMessage;
    }
}
