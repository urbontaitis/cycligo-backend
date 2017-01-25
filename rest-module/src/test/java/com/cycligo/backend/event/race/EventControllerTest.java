package com.cycligo.backend.event.race;

import com.cycligo.backend.event.EventDto;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class EventControllerTest {

    @Autowired
    private EventController raceRestController;

//    @Test(expected = RaceEventNotFoundException.class)
    @Ignore
    public void shouldThrowRaceEventNotFoundException() throws RaceEventNotFoundException {
        this.raceRestController.getRaceProfile(-1L);
    }

    @Test//dump test need to create a real tests
    public void test() throws RaceEventNotFoundException {
        Long raceId = 1L;
        EventDto raceProfile = this.raceRestController.getRaceProfile(raceId);

        Assert.assertEquals(raceId, raceProfile.getId());
    }
}
