package com.cycligo.backend.api.blog;

import com.cycligo.backend.blog.Blog;
import com.cycligo.backend.lookup.LookupValue;
import com.cycligo.backend.lookup.LookupValueRepository;
import com.cycligo.backend.tag.Tag;
import com.cycligo.backend.tag.TagRepository;

import java.time.LocalDateTime;

/**
 * Created by Mindaugas Urbontaitis on 12/04/2017.
 * cycligo-rest-api
 */
public class BlogMapper {

    private final LookupValueRepository lookupValueRepository;
    private final TagRepository tagRepository;

    public BlogMapper(LookupValueRepository lookupValueRepository, TagRepository tagRepository) {
        this.lookupValueRepository = lookupValueRepository;
        this.tagRepository = tagRepository;
    }

    public Blog mapDto2Entity(BlogPost input) {
        Blog blog = new Blog();
        blog.setId(input.getId());
        blog.setTitle(input.getTitle());
        blog.setContent(input.getContent());
        blog.setCategory(find(input.getCategory()));
        blog.setPhotoId(input.getPhoto());
        blog.setCreatedAt(LocalDateTime.now());
        for (BlogTag blogTags : input.getTags()) {
            blog.addTags(findOrCreate(blogTags.getName()));
        }

        return blog;
    }

    Tag findOrCreate(String name) {
        Tag tag = tagRepository.findByName(name);
        if (tag == null) {
            return new Tag(name);
        }

        return tag;
    }

    LookupValue find(String name) {
        LookupValue lookupValue = lookupValueRepository.findByValue(name);
        if (lookupValue == null) {
            throw new IllegalArgumentException("Trying to save with non existing property: " + name);
        }

        return lookupValue;
    }
}
