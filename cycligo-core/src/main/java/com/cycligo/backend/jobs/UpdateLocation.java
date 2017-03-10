package com.cycligo.backend.jobs;

import com.cycligo.backend.location.Location;
import com.cycligo.backend.location.LocationRepository;
import com.cycligo.backend.lookup.LookupValue;
import com.cycligo.backend.lookup.LookupValueRepository;
import com.cycligo.backend.thirdparty.api.maps.GoogleMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Mindaugas Urbontaitis on 09/03/2017.
 * cycligo-rest-api
 *
 * Job responsible for to find Location which has country_id = null.
 * After that calls Google API and fetch address components by latitude and longitude.
 *
 */
@Component
public class UpdateLocation {

    private static final Logger logger = LoggerFactory.getLogger(UpdateLocation.class);

    private LocationRepository locationRepository;

    private LookupValueRepository lookupValueRepository;

    public UpdateLocation(LocationRepository locationRepository, LookupValueRepository lookupValueRepository) {
        this.locationRepository = locationRepository;
        this.lookupValueRepository = lookupValueRepository;
    }

    @Scheduled(cron = "0 0 * * * ?") //the top of every hour of every day.
    public void findNotCompletedAndUpdate() {
        logger.info("UpdateLocation job started");
        Iterable<Location> notCompletedList = locationRepository.findAllWhereCountryIdIsNull();
        for(Location location : notCompletedList) {
            String countryName = GoogleMapService.getCountryShortName(
                    location.getLatitude().doubleValue(),
                    location.getLongitude().doubleValue());

            LookupValue country = lookupValueRepository.findByValue(countryName);
            if (null == country) {
                country = lookupValueRepository.findByValue("UNKNOWN");
            }
            location.setCountryId(country.getId());
            locationRepository.save(location);
        }
    }
}
