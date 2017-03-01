package com.cycligo.backend.event;

/**
 * Created by Mindaugas Urbontaitis on 28/02/2017.
 * cycligo-rest-api
 */
public class EventDetailDto {

    private Integer distance;
    private Integer elevation;
    private Double price;

    public EventDetailDto() {}

    public EventDetailDto(Integer distance, Integer elevation, Double price) {
        this.distance = distance;
        this.elevation = elevation;
        this.price = price;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getElevation() {
        return elevation;
    }

    public void setElevation(Integer elevation) {
        this.elevation = elevation;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
