package com.cycligo.backend.comment;

import com.cycligo.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
@Service
public class CommentService {

    private CommentRepository commentRepository;
    private CommentMapper mapper = new CommentMapper();

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Comments findByParentId(Long parentId, String parentType) {
        Comments result = new Comments();

        Iterable<Comment> comments = commentRepository.findAllByParentIdAndParentType(parentId, parentType);
        for(Comment comment : comments) {
            comment.setUser(fake()); // FIXME use a real user
            result.getComments().add(mapper.entity2Dto(comment));
        }

        return result;
    }

    public CommentDto save(CommentDto dto) {
        if (dto.getDate() == null) {
            dto.setDate(LocalDateTime.now());
        }

        Comment entity = mapper.dto2Entity(dto);
        // FIXME temporary workaround to set user
        entity.setUser(fake());
        entity = commentRepository.save(entity);

        return mapper.entity2Dto(entity);
    }

    private User fake() {
        User user = new User();
        user.setId(-1L);
        user.setName("Ted");
        user.setSurname("Test");
        user.setEmail("ted@test.email");
        user.setPhoto("1");

        return user;
    }
}
