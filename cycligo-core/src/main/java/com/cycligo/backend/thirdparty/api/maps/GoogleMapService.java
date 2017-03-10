package com.cycligo.backend.thirdparty.api.maps;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Mindaugas Urbontaitis on 09/03/2017.
 * cycligo-rest-api
 */
public class GoogleMapService {

    private static final Logger logger = LoggerFactory.getLogger(GoogleMapService.class);

    private static GeoApiContext geoApiContext = null;

//    @Value("${thirdparty.api.googleApiKey}")
    private static String GOOGLE_API_KEY = "";

    public static GeoApiContext getInstance() {
        if (geoApiContext == null) {
            logger.info("Creating GoogleAPI instance with key {}", GOOGLE_API_KEY);
            geoApiContext = new GeoApiContext().setApiKey(GOOGLE_API_KEY);
        }

        return geoApiContext;
    }

    public static String getCountryShortName(double lat, double lng) {
        GeocodingResult[] results = null;
        LatLng latLng = new LatLng(lat, lng);
        try {
            results = GeocodingApi.newRequest(getInstance()).language("en").latlng(latLng).await();
            logger.info("Result of geocingApi: {}", results);
            for (GeocodingResult result : results) {
                for (AddressComponent addressComponent : result.addressComponents) {
                    if (AddressComponentType.COUNTRY.equals(addressComponent.types[0])) {
                        return addressComponent.shortName;
                    }
                }
            }

        } catch (Exception e) {
            logger.error("Google API failed to fetch data {}", e);
        }
        return "UNKNOWN";
    }
}
