package com.cycligo.backend.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Mindaugas Urbontaitis on 09/03/2017.
 * cycligo-rest-api
 */
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("select l from Location l where l.country IS NULL")
    Iterable<Location> findAllWhereCountryIsNull();
}
