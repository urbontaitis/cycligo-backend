package com.cycligo.backend.event;

import org.springframework.format.annotation.NumberFormat;

/**
 * Created by Mindaugas Urbontaitis on 28/02/2017.
 * cycligo-rest-api
 */
public class EventDetailDto {

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Double distance;
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Double elevation;
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private Double price;

    public EventDetailDto() {}

    public EventDetailDto(Double distance, Double elevation, Double price) {
        this.distance = distance;
        this.elevation = elevation;
        this.price = price;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
