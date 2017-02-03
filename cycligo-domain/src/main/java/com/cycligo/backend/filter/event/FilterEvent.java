package com.cycligo.backend.filter.event;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
@Entity
public class FilterEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "filterEvent", fetch = FetchType.LAZY)
    private Set<FilterChoice> filterChoices = new HashSet<>(0);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<FilterChoice> getFilterChoices() {
        return filterChoices;
    }

    public void setFilterChoices(Set<FilterChoice> filterChoices) {
        this.filterChoices = filterChoices;
    }
}
