package com.cycligo.backend.lookup;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mindaugas Urbontaitis on 28/02/2017.
 * cycligo-rest-api
 */
@Entity
public class Lookup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String value;

    @OneToMany(mappedBy = "lookup", fetch = FetchType.LAZY)
    private Set<LookupValue> lookups = new HashSet<>(0);

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

    public Set<LookupValue> getLookups() {
        return lookups;
    }

    public void setLookups(Set<LookupValue> lookups) {
        this.lookups = lookups;
    }
}
