package com.cycligo.backend.comment;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "customers")
@RestController
public class CommentController {

    private CommentService commentService;

    @Autowired
    CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "Gets a comments based on parent id",
            notes = "Retrieves a comments",
            response = Comments.class)
    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    Comments commentsByParentId(@RequestParam(value = "parentId") Long parentId) {
        return commentService.findByParentId(parentId);
    }

    @ApiOperation(value = "Post a comment based on parent id",
            notes = "Post a comment",
            response = Object.class)
    @RequestMapping(value = "/comments/comment", method = RequestMethod.PUT)
    List<Object> postComment(@RequestParam(value = "parentId") Long parentId) {
        throw new NotYetImplementedException("TODO implement comments API");
    }



}
