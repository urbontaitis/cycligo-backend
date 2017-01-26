package com.cycligo.backend.filter.event;

import javax.persistence.*;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
@Entity
public class FilterChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filter_id", nullable = false)
    private FilterEvent filterEvent;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public FilterEvent getFilterEvent() {
        return filterEvent;
    }

    public void setFilterEvent(FilterEvent filterEvent) {
        this.filterEvent = filterEvent;
    }
}
