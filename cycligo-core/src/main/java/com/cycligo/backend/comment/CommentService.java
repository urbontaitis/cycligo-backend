package com.cycligo.backend.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public Comments findByParentId(Long parentId) {
        Comments result = new Comments();

        Iterable<Comment> comments = commentRepository.findAllByParentId(parentId);
        for(Comment comment : comments) {
            result.getComments().add(mapper.entity2Dto(comment));
        }

        return result;
    }

}
