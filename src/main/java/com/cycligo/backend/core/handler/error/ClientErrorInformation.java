package com.cycligo.backend.core.handler.error;

/**
 *
 * Created by panda on 13/11/2016.
 * cycligo-backend
 */
public class ClientErrorInformation {

    private String message;
    private StringBuffer requestURL;

    public ClientErrorInformation(String message, StringBuffer requestURL) {
        this.message = message;
        this.requestURL = requestURL;
    }

    public String getMessage() {
        return message;
    }

    public StringBuffer getRequestURL() {
        return requestURL;
    }
}
