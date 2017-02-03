package com.cycligo.backend.event.race;

import java.util.Date;

/**
 * Created by panda on 07/11/2016.
 */
public class Distance {

    private Long id;
    private String name;
    private Long length;
    private Date ageFrom;
    private Date ageTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Date getAgeFrom() {
        return ageFrom;
    }

    public void setAgeFrom(Date ageFrom) {
        this.ageFrom = ageFrom;
    }

    public Date getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(Date ageTo) {
        this.ageTo = ageTo;
    }
}
