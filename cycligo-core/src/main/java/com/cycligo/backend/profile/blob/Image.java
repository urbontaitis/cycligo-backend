package com.cycligo.backend.profile.blob;

/**
 * Created by panda on 07/11/2016.
 */
public class Image {

    private Long id;
    private Byte[] data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte[] getData() {
        return data;
    }

    public void setData(Byte[] data) {
        this.data = data;
    }
}
