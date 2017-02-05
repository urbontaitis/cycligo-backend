package com.cycligo.backend.comment;

import com.cycligo.backend.user.UserDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
public class CommentDto {
    private Long id;
    private String comment;
    private LocalDateTime date;
    private UserDto userDto;
    private List<CommentDto> replies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public List<CommentDto> getReplies() {
        if (replies == null) {
            replies = new ArrayList<>();
        }
        return replies;
    }

}
