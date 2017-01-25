package com.cycligo.backend.comment;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Mindaugas Urbontaitis on 24/01/2017.
 * cycligo-backend
 */
@RestController
public class CommentController {

    private CommentService commentService;

    @Autowired
    CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    Comments commentsByParentId(@RequestParam(value = "parentId") Long parentId) {
        return commentService.findByParentId(parentId);
    }

    @RequestMapping(value = "/comments/comment", method = RequestMethod.PUT)
    List<Object> postComment(@RequestParam(value = "parentId") Long parentId) {
        throw new NotYetImplementedException("TODO implement comments API");
    }



}
