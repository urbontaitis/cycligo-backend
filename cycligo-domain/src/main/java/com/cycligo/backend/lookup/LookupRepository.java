package com.cycligo.backend.lookup;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mindaugas Urbontaitis on 28/02/2017.
 * cycligo-rest-api
 */
@Repository
public interface LookupRepository extends CrudRepository<Lookup, Long> {

    @Query("select l from Lookup l where l.name = 'MTB' or l.name = 'ROAD' or l.name='COUNTRIES' or l.name = 'DISTANCE'")
    Iterable<Lookup> findAllFilters();
}
