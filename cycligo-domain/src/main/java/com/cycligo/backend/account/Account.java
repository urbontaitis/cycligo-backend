package com.cycligo.backend.account;

/**
 * Created by Mindaugas Urbontaitis on 25/03/2017.
 * cycligo-rest-api
 */
public class Account {

    private final String username;

    private final String password;

    private final String firstName;

    private final String lastName;

    public Account(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
