package com.cycligo.backend.media;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Mindaugas Urbontaitis on 04/02/2017.
 * cycligo-rest-api
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("select i from Image i where i.id = :id and i.parentId = :parentId and i.parentType = :parentType")
    Image findByIdAndParentData(@Param("id") Long id,
                                @Param("parentId") Long parentId,
                                @Param("parentType") String parentType);

//    Optional<Image> findById(Long id);
}
