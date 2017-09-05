package com.cycligo.backend.core.handler.error;

/**
 * Created by Mindaugas Urbontaitis on 02/03/2017.
 * cycligo-rest-api
 */
public class Error {

    private String field;
    private String message;

    public Error(String field, String error) {
        this.field = field;
        this.message = error;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

}
