package com.cycligo.backend.comment;

import com.cycligo.backend.base.ParentType;
import com.cycligo.backend.config.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Mindaugas Urbontaitis on 24/01/2017.
 * cycligo-backend
 */
@Api(value = "customers")
@RestController
@RequestMapping(Constants.ROOT_API_PATH)
public class CommentController {

    private CommentService commentService;

    @Autowired
    CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "Gets a comments based on parent id",
            notes = "Retrieves a comments",
            response = Comments.class)
    @RequestMapping(value = "/comments/{parentId}/{parentType}", method = RequestMethod.GET)
    Comments commentsByParentId(@PathVariable Long parentId, @PathVariable String parentType) {
        return commentService.findByParentId(parentId, parentType);
    }

    @ApiOperation(value = "Post a comment based on parent id",
            notes = "Post a comment",
            response = CommentDto.class)
    @RequestMapping(value = "/comments/comment", method = RequestMethod.POST)
    ResponseEntity<CommentDto> add(@Valid @RequestBody CommentDto input) {

        input.setParentType(ParentType.EVENT.name());// FIXME temporary workaround
        CommentDto result = commentService.save(input);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
