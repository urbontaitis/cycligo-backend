package com.cycligo.backend.event.race;

import com.cycligo.backend.event.Event;
import com.cycligo.backend.event.EventRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static com.cycligo.backend.event.race.EventTestHelper.initEvent;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Mindaugas Urbontaitis on 06/02/2017.
 * cycligo-rest-api
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class EventRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void shouldSaveEvent() {
        Event event = initEvent();

        eventRepository.save(event);

        assertNotNull("Event was not saved", event.getId());
    }

    @Test
    public void shouldFindEventWithLithuanianLetters() {
        Event event= initEvent();
        String LT_LETTERS = "ąčęėįšųūžĄČĘĖĮŠŲŪŽ";
        event.setTitle(LT_LETTERS);
        event.setDescription(LT_LETTERS);
        entityManager.persist(event);

        Event actual = eventRepository.findOne(event.getId());

        assertEquals("Title contains wrong letters.", LT_LETTERS, actual.getTitle());
        assertEquals("Description contains wrong letters.", LT_LETTERS, actual.getDescription());
    }

    @Test
    public void shouldStoreEventWithLithuanianLetters() {
        Event event= initEvent();
        String LT_LETTERS = "ąčęėįšųūžĄČĘĖĮŠŲŪŽ";
        event.setTitle(LT_LETTERS);
        event.setDescription(LT_LETTERS);
        eventRepository.save(event);

        Event actual = eventRepository.findOne(event.getId());

        assertEquals("Title contains wrong letters.", LT_LETTERS, actual.getTitle());
        assertEquals("Description contains wrong letters.", LT_LETTERS, actual.getDescription());
    }
}
