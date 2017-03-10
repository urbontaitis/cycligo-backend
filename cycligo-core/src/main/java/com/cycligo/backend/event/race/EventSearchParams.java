package com.cycligo.backend.event.race;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * Created by Mindaugas Urbontaitis on 10/03/2017.
 * cycligo-rest-api
 */
public class EventSearchParams {

    private String discipline;

    private String category;

    private String country;
    private String starts;

    private String ends;

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

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

    public Boolean isNotEmpty() {
        return StringUtils.isNotBlank(discipline)
            || StringUtils.isNotBlank(category)
            || StringUtils.isNotBlank(country)
            || StringUtils.isNotBlank(starts)
            || StringUtils.isNotBlank(ends);
    }
}
