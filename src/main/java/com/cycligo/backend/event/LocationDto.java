package com.cycligo.backend.event;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by Mindaugas Urbontaitis on 28/02/2017.
 * cycligo-rest-api
 */
public class LocationDto {

    @NotEmpty(message = "{event.location_is_required}")
    @Size(max=256)
    private String label;
    @Size(max=256)
    private String placeId;
    @NotEmpty(message = "{event.location_is_required}")
    private Double latitude;
    @NotEmpty(message = "{event.location_is_required}")
    private Double longitude;
    private String country;

    public LocationDto() {}

    public LocationDto(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
