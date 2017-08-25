package com.cycligo.backend.user;

import java.io.Serializable;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
public class UserDto implements Serializable {
    private Long id;
    private Boolean authenticated;
    private String name;
    private String surname;
    private String photo;

    public UserDto() {}

    public UserDto(String name, Boolean authenticated) {
        this.name = name;
        this.authenticated = authenticated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", authenticated=" + authenticated +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
