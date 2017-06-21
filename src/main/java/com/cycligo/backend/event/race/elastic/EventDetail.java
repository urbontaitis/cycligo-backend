package com.cycligo.backend.event.race.elastic;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

/**
 * Created by Mindaugas on 2017-06-21.
 */
@Document(indexName = "events", type = "eventDetail", shards = 1, replicas = 0, refreshInterval = "-1")
public class EventDetail {

    @Id
    private Long id;

    private Double distance;

    private Double elevation;

    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
