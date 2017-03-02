package com.cycligo.backend.base.handler.error;

/**
 * Created by Mindaugas Urbontaitis on 02/03/2017.
 * cycligo-rest-api
 */
public class Error {

    private String field;
    private String message;

    public Error(String field, String error) {
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

}
