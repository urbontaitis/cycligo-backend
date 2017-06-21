package com.cycligo.backend.event.race.elastic;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mindaugas on 2017-06-21.
 */
@Document(indexName = "events", type = "event", shards = 1, replicas = 0, refreshInterval = "-1")
public class Event {

    @Id
    private Long id;

    private String title;

    private String description;

    private LocalDateTime starts;

    private LocalDateTime ends;

    private Location location;

    private Long photoId;

    private Lookup discipline;

    private LookupValue category;

    private Set<EventDetail> eventDetails = new HashSet<>(0);

    private String linkToEvent;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStarts() {
        return starts;
    }

    public void setStarts(LocalDateTime starts) {
        this.starts = starts;
    }

    public LocalDateTime getEnds() {
        return ends;
    }

    public void setEnds(LocalDateTime ends) {
        this.ends = ends;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public Lookup getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Lookup discipline) {
        this.discipline = discipline;
    }

    public LookupValue getCategory() {
        return category;
    }

    public void setCategory(LookupValue category) {
        this.category = category;
    }

    public Set<EventDetail> getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(Set<EventDetail> eventDetails) {
        this.eventDetails = eventDetails;
    }

    public String getLinkToEvent() {
        return linkToEvent;
    }

    public void setLinkToEvent(String linkToEvent) {
        this.linkToEvent = linkToEvent;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", starts=" + starts +
                ", ends=" + ends +
                ", location=" + location +
                ", photoId=" + photoId +
                ", discipline=" + discipline +
                ", category=" + category +
                ", eventDetails=" + eventDetails +
                ", linkToEvent='" + linkToEvent + '\'' +
                '}';
    }
}
