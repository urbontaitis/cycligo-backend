package com.cycligo.backend.event.race.elastic;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mindaugas on 2017-06-21.
 */
@Document(indexName = "lookups", type = "lookup", shards = 1, replicas = 0, refreshInterval = "-1")
public class Lookup {

    @Id
    private Long id;

    private String name;

    private String value;

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
