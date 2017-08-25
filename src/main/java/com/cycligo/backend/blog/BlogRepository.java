package com.cycligo.backend.blog;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Mindaugas Urbontaitis on 12/04/2017.
 * cycligo-rest-api
 */
@Repository
public interface BlogRepository extends PagingAndSortingRepository<Blog, Long> {

    Optional<Blog> findById(Long id);
}
