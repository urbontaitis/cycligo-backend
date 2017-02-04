package com.cycligo.backend.media;

import javax.persistence.*;

/**
 * Created by Mindaugas Urbontaitis on 04/02/2017.
 * cycligo-rest-api
 */
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] blob;

    private String mediaType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getBlob() {
        return blob;
    }

    public void setBlob(byte[] blob) {
        this.blob = blob;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
