package com.cycligo.backend.event.race;

import com.cycligo.backend.core.handler.error.ClientErrorInformation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by panda on 26/10/16.
 */
@RestController
public class RaceRestController {

    private RaceRepository raceRepository;

    public RaceRestController(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @RequestMapping(value = "/event/race-profile/{id}", method = RequestMethod.GET)
    RaceProfile getRaceProfile(@PathVariable Long id) throws RaceEventNotFoundException {

        if (id == -1L) { // just for testing purpoise
            throw new RaceEventNotFoundException("This race event was not found");
        }

        RaceProfile raceProfile = raceRepository.getRaceProfile(id);

        return raceProfile;
    }

    @RequestMapping(value = "/event/active-races", method = RequestMethod.GET)
    List<RaceProfile> getActiveRaces() {

        return raceRepository.getActiveRaces();
    }

    @ExceptionHandler(RaceEventNotFoundException.class)
    ResponseEntity<ClientErrorInformation> rulesForRaceEventNotFound(HttpServletRequest req, Exception ex) {
        ClientErrorInformation error = new ClientErrorInformation(ex.toString(), req.getRequestURL());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }
}
