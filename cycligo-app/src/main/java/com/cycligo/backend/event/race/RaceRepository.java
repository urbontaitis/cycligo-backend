package com.cycligo.backend.event.race;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panda on 07/11/2016.
 */
@Repository
public class RaceRepository {

    List<RaceProfile> getActiveRaces() {
        List<RaceProfile> raceProfiles = new ArrayList<>();
        raceProfiles.add(getRaceProfile(1L));
        raceProfiles.add(getRaceProfile(2L));

        return raceProfiles;
    }


    RaceProfile getRaceProfile(Long id) {
        RaceProfile profile = new RaceProfile();
        profile.setId(id);
        profile.setCategory(Category.ROAD);
        profile.setDescription("race event description");
        profile.setName("Test race event");

        return profile;
    }




}
