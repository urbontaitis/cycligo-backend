package com.cycligo.backend.base.handler.error;

/**
 *
 * Created by panda on 13/11/2016.
 * cycligo-backend
 */
public class ClientErrorInformation {

    private String errorMessage;
    private StringBuffer requestURL;

    public ClientErrorInformation(String errorMessage, StringBuffer requestURL) {
        this.errorMessage = errorMessage;
        this.requestURL = requestURL;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public StringBuffer getRequestURL() {
        return requestURL;
    }
}
