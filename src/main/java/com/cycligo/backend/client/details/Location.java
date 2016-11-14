package com.cycligo.backend.client.details;

import com.cycligo.backend.client.details.Address;

/**
 * Created by panda on 07/11/2016.
 */
public class Location {

    private Long id;
    private Address address;
    private String coordinates;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}
