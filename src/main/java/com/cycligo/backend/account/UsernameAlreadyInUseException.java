package com.cycligo.backend.account;

/**
 * Created by Mindaugas Urbontaitis on 25/03/2017.
 * cycligo-rest-api
 */
public class UsernameAlreadyInUseException extends Exception {

    public UsernameAlreadyInUseException(String username) {
        super("The username '" + username + "' is already in use.");
    }

}
