package com.cycligo.backend.filter.event;

import com.cycligo.backend.config.Constants;
import com.cycligo.backend.event.ChoiceDto;
import com.cycligo.backend.event.FilterDto;
import com.cycligo.backend.event.FilterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
@Api(value = "Lookups")
@RestController
@RequestMapping(Constants.ROOT_API_PATH)
public class FilterResource {

    private FilterService filterService;

    FilterResource(FilterService filterService){
        this.filterService = filterService;
    }

    @RequestMapping(value = "/filter/events", method = RequestMethod.GET)
    List<FilterDto> fetchAll() {
        return filterService.fetchAllEvents();
    }

    @ApiOperation(value = "Gets a categories which belongs to Blog page.",
            notes = "Retrieve all available Blog categories",
            response = ChoiceDto.class)
    @RequestMapping(value = "/filter/blog", method = RequestMethod.GET)
    List<ChoiceDto> blogChoices() {
        return filterService.fetchBlogChoices();
    }
}
