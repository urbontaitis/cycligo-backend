package com.cycligo.backend.api.blog;

import com.cycligo.backend.base.handler.error.ValidationException;
import com.cycligo.backend.blog.BlogNotFoundException;
import com.cycligo.backend.blog.BlogPost;
import com.cycligo.backend.blog.BlogService;
import com.cycligo.backend.config.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


/**
 * Created by Mindaugas Urbontaitis on 12/04/2017.
 * cycligo-rest-api
 */
@Api(value = "Blog posts")
@RestController
@RequestMapping(Constants.ROOT_API_PATH)
public class BlogController {

    private static final Logger log = LoggerFactory.getLogger(BlogController.class);

    private BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @ApiOperation(value = "Gets a blog posts which are approved",
            notes = "Retrieves a blog posts",
            response = Page.class)
    @RequestMapping(value = "/blogpost", method = RequestMethod.GET)
    Page<BlogPost> getActive(Pageable pageable) {
        return blogService.active(pageable);
    }

    @ApiOperation(value = "Gets a blog post based by id",
            notes = "Retrievea a blog post",
            response = BlogPost.class)
    @RequestMapping(value = "/blogpost/{id}", method = RequestMethod.GET)
    BlogPost find(@PathVariable Long id) throws BlogNotFoundException {
        return blogService.find(id);
    }

    @ApiOperation(value = "Post a blog post",
            notes = "Create a blog post",
            response = Long.class)
    @RequestMapping(value = "/blogpost/create", method = RequestMethod.POST)
    ResponseEntity<?> create(@Valid @RequestBody BlogPost input, BindingResult result) throws ValidationException {
        if (result.hasFieldErrors()) {
            throw new ValidationException(result);
        }

        Long blogPostId = blogService.save(input);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(blogPostId).toUri();
        return ResponseEntity.created(location).build();
    }


}
