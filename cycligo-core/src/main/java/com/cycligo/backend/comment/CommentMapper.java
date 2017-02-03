package com.cycligo.backend.comment;

import com.cycligo.backend.user.User;
import com.cycligo.backend.user.UserDto;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
public class CommentMapper {

    public CommentDto entity2Dto(Comment entity) {
        CommentDto dto = new CommentDto();
        dto.setId(entity.getId());
        dto.setComment(entity.getComment());
        dto.setDate(entity.getDate());
        dto.setUserDto(entity2Dto(entity.getUser()));
        if (!entity.getReplies().isEmpty()) {
            for (Comment reply: entity.getReplies()) {
                dto.getReplies().add(entity2Dto(reply));
            }
        }
        return dto;
    }

    public UserDto entity2Dto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setPhoto(entity.getPhoto());
        return dto;
    }
}
