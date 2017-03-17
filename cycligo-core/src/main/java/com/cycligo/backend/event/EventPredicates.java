package com.cycligo.backend.event;

import com.cycligo.backend.event.race.EventSearchParams;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mindaugas Urbontaitis on 16/03/2017.
 * cycligo-rest-api
 */
final class EventPredicates {

    private EventPredicates() {}

    static Predicate eventSearchParamsContainsIgnoreCase(EventSearchParams searchParams) {
        if (isNotEmpty(searchParams.getMcat())
                && isNotEmpty(searchParams.getRcat())
                && isNotEmpty(searchParams.getCountry())
                && isNotEmpty(searchParams.getDistance())) {

        }
        BooleanBuilder categories = new BooleanBuilder();
        BooleanBuilder country = new BooleanBuilder();
        BooleanBuilder date = new BooleanBuilder();
        if (isNotEmpty(searchParams.getMcat())) {
            for (String p : searchParams.getMcat()) {
                categories.or(categoryAndDisciplineContainsIgnoreCase(p, "MTB"));
            }
        }
        if (isNotEmpty(searchParams.getRcat())) {
            for (String p : searchParams.getRcat()) {
                categories.or(categoryAndDisciplineContainsIgnoreCase(p, "ROAD"));
            }
        }

        if (isNotEmpty(searchParams.getCountry())) {
            for (String p : searchParams.getCountry()) {
                country.or(QEvent.event.location.country.value.containsIgnoreCase(p));
            }
        }

//        boolean startsIsNotEmpty = StringUtils.isNotEmpty(searchParams.getStarts());
//        boolean endsIsNotEmpty = StringUtils.isNotEmpty(searchParams.getEnds());
//        if ( startsIsNotEmpty && endsIsNotEmpty) {
//
//        }

        BooleanBuilder result = new BooleanBuilder();
        result.and(categories).and(country);

        return result;
    }

    static Predicate categoryAndDisciplineContainsIgnoreCase(String category, String discipline) {
        return QEvent.event.category.value.containsIgnoreCase(category)
                .and(QEvent.event.category.lookup.value.containsIgnoreCase(discipline));
    }

    private static <T> boolean isNotEmpty(List<T> array) {
        return null != array && !array.isEmpty();
    }
}
