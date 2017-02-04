package com.cycligo.backend.media;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mindaugas Urbontaitis on 04/02/2017.
 * cycligo-rest-api
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

//    Optional<Image> findById(Long id);
}
