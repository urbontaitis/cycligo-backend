package com.cycligo.backend.lookup;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mindaugas Urbontaitis on 03/03/2017.
 * cycligo-rest-api
 */
@Repository
public interface LookupValueRepository extends CrudRepository<LookupValue, Long> {

    LookupValue findByName(String name);

}
