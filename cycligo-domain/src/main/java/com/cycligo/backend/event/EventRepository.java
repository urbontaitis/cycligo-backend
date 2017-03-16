package com.cycligo.backend.event;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by panda on 07/11/2016.
 */
@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    Optional<Event> findById(Long id);

    @Query("select e from Event e where e.discipline.value = :discipline or e.category.value = :category or e.location.country.value = :country or e.starts >= :starts or e.ends <= :ends")
    Iterable<Event> findByEventSearchParams(@Param("discipline") String discipline,
                                            @Param("category") String category,
                                            @Param("country") String country,
                                            @Param("starts") LocalDateTime starts,
                                            @Param("ends") LocalDateTime ends);

}
