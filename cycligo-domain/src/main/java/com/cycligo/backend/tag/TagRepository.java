package com.cycligo.backend.tag;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mindaugas Urbontaitis on 12/04/2017.
 * cycligo-rest-api
 */
public interface TagRepository extends CrudRepository<Tag, Long> {

    Tag findByName(String name);
}
