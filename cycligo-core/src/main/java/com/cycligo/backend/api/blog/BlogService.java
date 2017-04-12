package com.cycligo.backend.api.blog;

import com.cycligo.backend.blog.Blog;
import com.cycligo.backend.blog.BlogRepository;
import com.cycligo.backend.lookup.LookupValueRepository;
import com.cycligo.backend.tag.TagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Created by Mindaugas Urbontaitis on 12/04/2017.
 * cycligo-rest-api
 */
@Service
public class BlogService {

    private BlogRepository blogRepository;
    private LookupValueRepository lookupValueRepository;
    private TagRepository tagRepository;

    public BlogService(BlogRepository blogRepository,
                       TagRepository tagRepository,
                       LookupValueRepository lookupValueRepository) {
        this.blogRepository = blogRepository;
        this.tagRepository = tagRepository;
        this.lookupValueRepository = lookupValueRepository;
    }

    public Page<BlogPost> active(Pageable pageable) {
        Page<Blog> searhResultPage = blogRepository.findAll(pageable);

        return BlogStaticMapper.mapEntity2Dto(pageable, searhResultPage);
    }

    public BlogPost find(Long id) throws BlogNotFoundException {
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new BlogNotFoundException(id));

        return BlogStaticMapper.entity2Dto(blog);
    }

    public Long save(BlogPost input) {
        Blog blog = (new BlogMapper(lookupValueRepository, tagRepository)).mapDto2Entity(input);
        blog = blogRepository.save(blog);

        return blog.getId();
    }

}
