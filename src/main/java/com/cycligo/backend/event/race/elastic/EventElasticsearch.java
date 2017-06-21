package com.cycligo.backend.event.race.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by Mindaugas on 2017-06-21.
 */
public interface EventElasticsearch extends ElasticsearchRepository<Event, Long> {

}
