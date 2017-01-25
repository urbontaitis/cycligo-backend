package com.cycligo.backend.comment;

import org.hibernate.cfg.NotYetImplementedException;
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
public class CommentRestController {

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    List<Object> commentsByParentId(@RequestParam(value = "parentId") Long parentId) {
        throw new NotYetImplementedException("TODO implement comments API");
    }

    @RequestMapping(value = "/comments/comment", method = RequestMethod.PUT)
    List<Object> postComment(@RequestParam(value = "parentId") Long parentId) {
        throw new NotYetImplementedException("TODO implement comments API");
    }



}
