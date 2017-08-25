package com.cycligo.backend.comment;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {

    Iterable<Comment> findAllByParentIdAndParentType(Long parentId, String parentType);
}
