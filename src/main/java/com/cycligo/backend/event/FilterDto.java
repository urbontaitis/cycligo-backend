package com.cycligo.backend.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
public class FilterDto {
    private Long id;
    private String title;
    private List<ChoiceDto> choices;

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

    public List<ChoiceDto> getChoices() {
        if (choices == null) {
            choices = new ArrayList<>();
        }
        return choices;
    }

    public void setChoices(List<ChoiceDto> choices) {
        this.choices = choices;
    }
}
