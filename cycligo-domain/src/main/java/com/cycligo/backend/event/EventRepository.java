package com.cycligo.backend.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by panda on 07/11/2016.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findById(Long id);

//    @Query("select ")
//    Iterable<Event> findRecentEvents();

}
