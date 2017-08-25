package com.cycligo.backend.blog;

import com.cycligo.backend.tag.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Mindaugas Urbontaitis on 12/04/2017.
 * cycligo-rest-api
 */
class BlogStaticMapper {

    static BlogPost entity2Dto(Blog entity) {
        BlogPost dto = new BlogPost();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setCategory(entity.getCategory().getName());
        dto.setDate(entity.getCreatedAt());
        dto.setPhoto(entity.getPhotoId());
        List<BlogTag> tags = entity.getTags().stream().map(BlogStaticMapper::entity2Dto).collect(toList());
        dto.setTags(tags);
        dto.setCommentsCount(0L); //Not yet implemented

        return dto;
    }

    static BlogTag entity2Dto(Tag entity) {
        return new BlogTag(entity.getId(), entity.getName());
    }

    static Page<BlogPost> mapEntity2Dto(Pageable pageable, Page<Blog> searhResultPage) {
        List<BlogPost> dtos = mapEntities2Dtos(searhResultPage.getContent());
        return new PageImpl<BlogPost>(dtos, pageable, searhResultPage.getTotalElements());
    }

    private static List<BlogPost> mapEntities2Dtos(List<Blog> content) {
        return content.stream()
                .map(BlogStaticMapper::entity2Dto)
                .collect(toList());
    }
}
