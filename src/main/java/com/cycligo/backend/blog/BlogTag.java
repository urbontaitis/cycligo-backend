package com.cycligo.backend.blog;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Mindaugas Urbontaitis on 12/04/2017.
 * cycligo-rest-api
 */
public class BlogTag implements Serializable {

    private Long id;

    @Size(max = 250)
    private String name;

    public BlogTag() {}

    public BlogTag(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
