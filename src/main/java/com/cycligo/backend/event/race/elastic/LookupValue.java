package com.cycligo.backend.event.race.elastic;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by Mindaugas on 2017-06-21.
 */
@Document(indexName = "lookups", type = "lookupValue", shards = 1, replicas = 0, refreshInterval = "-1")
public class LookupValue {

    @Id
    private Long id;

    private String name;

    private String value;

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
}
