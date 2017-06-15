package com.cycligo.backend.event;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
public class EventNotFoundException extends Exception {

    public EventNotFoundException(Long id) {
        super("Could not found event '" + id + "'.");
    }
}
