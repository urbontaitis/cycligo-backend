package com.cycligo.backend.event.race;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by panda on 14/11/2016.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class RaceRestControllerTest {

    @Autowired
    private RaceRestController raceRestController;

    @Test(expected = RaceEventNotFoundException.class)
    public void shouldThrowRaceEventNotFoundException() throws RaceEventNotFoundException {
        this.raceRestController.getRaceProfile(-1L);
    }

    @Test//dump test need to create a real tests
    public void test() throws RaceEventNotFoundException {
        Long raceId = 1L;
        RaceProfile raceProfile = this.raceRestController.getRaceProfile(raceId);

        Assert.assertEquals(raceId, raceProfile.getId());
    }
}
