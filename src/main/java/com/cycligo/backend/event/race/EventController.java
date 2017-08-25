package com.cycligo.backend.event.race;

import com.cycligo.backend.base.handler.error.ClientErrorInformation;
import com.cycligo.backend.base.handler.error.ValidationException;
import com.cycligo.backend.config.Constants;
import com.cycligo.backend.event.*;
import com.cycligo.backend.event.race.elastic.*;
import com.cycligo.backend.event.race.elastic.Event;
import com.cycligo.backend.event.race.elastic.EventDetail;
import com.cycligo.backend.event.race.elastic.Location;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.hibernate.cfg.NotYetImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping(Constants.ROOT_API_PATH)
public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    private EventService eventService;

    private EventElasticsearch eventElasticsearch;

    private ElasticsearchTemplate elasticsearchTemplate;

    private EventRepository eventRepository;

    public EventController(EventService eventService, EventElasticsearch eventElasticsearch, ElasticsearchTemplate elasticsearchTemplate, EventRepository eventRepository) {
        this.eventService = eventService;
        this.eventElasticsearch = eventElasticsearch;
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.eventRepository = eventRepository;
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    Page<EventDto> getActiveEvents(EventSearchParams searchParams, Pageable pageRequest) {
        logger.debug("search criteria: {}", searchParams);
        return eventService.activeRaces(searchParams, pageRequest);
    }

    @RequestMapping(value = "/events/test", method = RequestMethod.GET)
    public String elasticTest() {

       Iterable<com.cycligo.backend.event.Event> dtos = eventRepository.findAll();

        List<IndexQuery> indexQueries = new ArrayList<>();
        for (com.cycligo.backend.event.Event dto : dtos) {
            eventElasticsearch.save(dto2Entity(dto));
        }

        Iterable<Event> events = eventElasticsearch.findAll();
        for(Event e : events) {
            logger.debug("Adding event to indexes {}", e);
            indexQueries.add(new IndexQueryBuilder().withId(e.getId().toString()).withObject(e).build());
        }

        elasticsearchTemplate.bulkIndex(indexQueries);

        return "done";
    }

    @RequestMapping(value = "/events/elastic", method = RequestMethod.GET)
    public List<Event> getAll() {

        Iterable<Event> events = eventElasticsearch.findAll();
        List<Event> list = new ArrayList<>();

        for(Event e : events) {
            logger.debug("Adding event {}", e);
            list.add(e);
        }

        logger.debug("final array {}", list);
        return list;
    }

    static Event dto2Entity(com.cycligo.backend.event.Event d) {
        Event e = new Event();
        e.setDescription(d.getDescription());
        e.setLinkToEvent(d.getLinkToEvent());
        e.setCategory(entity2Dto(d.getCategory()));
        e.setPhotoId(d.getPhotoId());
        e.setId(d.getId());
        e.setTitle(d.getTitle());
        e.setDiscipline(entity2Dto(d.getDiscipline()));
        e.setEnds(d.getEnds());
        e.setStarts(d.getStarts());
        Set<EventDetail> details = new HashSet<>();
        for(com.cycligo.backend.event.EventDetail ed : d.getEventDetails()){
            details.add(entity2Dto(ed));
        }
        e.setEventDetails(details);
        e.setLocation(entity2Dto(d.getLocation()));

        return e;
    }

    private static Location entity2Dto(com.cycligo.backend.location.Location location) {
        Location l = new Location();
        l.setId(location.getId());
        l.setLatitude(location.getLatitude());
        l.setLongitude(location.getLongitude());
        l.setCountry(entity2Dto(location.getCountry()));
        l.setLabel(location.getLabel());
        l.setPlaceId(location.getPlaceId());

        return l;
    }

    static EventDetail entity2Dto(com.cycligo.backend.event.EventDetail ed) {
        EventDetail e = new EventDetail();
        e.setDistance(ed.getDistance());
        e.setElevation(ed.getElevation());
        e.setPrice(ed.getPrice());
        e.setId(ed.getId());
        return e;
    }

    static Lookup entity2Dto(com.cycligo.backend.lookup.Lookup d) {
        Lookup l = new Lookup();
        l.setId(d.getId());
        l.setName(d.getName());
        l.setValue(d.getValue());
//        Set<LookupValue> values = new HashSet<>();
//        for(LookupValue v : d.getLookups()){
//            values.add(entity2Dto(v));
//        }
//        l.setLookups(values);
        return l;
    }

    static LookupValue entity2Dto(com.cycligo.backend.lookup.LookupValue value) {
        LookupValue v = new LookupValue();
        v.setId(value.getId());
        v.setName(value.getName());
        v.setValue(value.getValue());
        return v;
    }


    @RequestMapping(value = "/events/recent", method = RequestMethod.GET)
    RecentEvents getRecentEvents() {
        return eventService.recentRaces();
    }

    @RequestMapping(value = "/events/filter", method = RequestMethod.GET)
    List<EventDto> filterEvents() {
        throw new NotYetImplementedException("TODO implement events filter");
    }

    @RequestMapping(value = "/events/event/{id}", method = RequestMethod.GET)
    EventDto getRaceProfile(@PathVariable Long id) throws EventNotFoundException {

        return eventService.race(id);
    }

    @RequestMapping(value="/events/event", method = RequestMethod.POST)
    ResponseEntity<?> add(@Valid @RequestBody EventDto input, BindingResult result) throws ValidationException {
        //TODO add alternative ResponseEntity.noContent().build()
        (new EventValidator()).validate(input, result);
        if (result.hasFieldErrors()) {
            throw new ValidationException(result);
        }

        Long eventId = eventService.save(input);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(eventId).toUri();
        return ResponseEntity.created(location).build();
    }

    @ExceptionHandler(EventNotFoundException.class)
    ResponseEntity<ClientErrorInformation> rulesForRaceEventNotFound(HttpServletRequest req, Exception ex) {
        ClientErrorInformation error = new ClientErrorInformation(ex.toString(), req.getRequestURL());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

}
