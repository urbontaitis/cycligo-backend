package com.cycligo.backend.location;

import com.cycligo.backend.lookup.LookupValue;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Mindaugas Urbontaitis on 28/02/2017.
 * cycligo-rest-api
 */
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    private String placeId;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private Long cityId;

    @OneToOne(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "country_id")
    private LookupValue country;

    private Long continentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public LookupValue getCountry() {
        return country;
    }

    public void setCountry(LookupValue country) {
        this.country = country;
    }

    public Long getContinentId() {
        return continentId;
    }

    public void setContinentId(Long continentId) {
        this.continentId = continentId;
    }
}
