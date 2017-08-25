package com.cycligo.backend.event.race;

import java.util.List;

/**
 * Created by Mindaugas Urbontaitis on 10/03/2017.
 * cycligo-rest-api
 */
public class EventSearchParams {

    private String starts;
    private String ends;
    private List<String> mcat;
    private List<String> rcat;
    private List<String> country;
    private List<String> distance;

    public String getStarts() {
        return starts;
    }

    public void setStarts(String starts) {
        this.starts = starts;
    }

    public String getEnds() {
        return ends;
    }

    public void setEnds(String ends) {
        this.ends = ends;
    }

    public List<String> getMcat() {
        return mcat;
    }

    public void setMcat(List<String> mcat) {
        this.mcat = mcat;
    }

    public List<String> getRcat() {
        return rcat;
    }

    public void setRcat(List<String> rcat) {
        this.rcat = rcat;
    }

    public List<String> getCountry() {
        return country;
    }

    public void setCountry(List<String> country) {
        this.country = country;
    }

    public List<String> getDistance() {
        return distance;
    }

    public void setDistance(List<String> distance) {
        this.distance = distance;
    }

}
