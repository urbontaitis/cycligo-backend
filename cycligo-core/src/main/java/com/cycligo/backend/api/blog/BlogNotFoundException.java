package com.cycligo.backend.api.blog;

/**
 * Created by Mindaugas Urbontaitis on 12/04/2017.
 * cycligo-rest-api
 */
public class BlogNotFoundException extends Exception {

    public BlogNotFoundException(Long id) {
        super("Could not found blog '" + id + "'.");
    }
}
