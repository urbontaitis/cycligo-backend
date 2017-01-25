package com.cycligo.backend.event;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by panda on 07/11/2016.
 */
@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

//    Iterable<Event> getActiveRaces();
//
//    Event getById(Long id);

}
