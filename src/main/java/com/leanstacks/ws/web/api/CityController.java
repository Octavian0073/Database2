package com.leanstacks.ws.web.api;

import com.leanstacks.ws.model.City;
import com.leanstacks.ws.model.Path;
import com.leanstacks.ws.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    /**
     * The Logger for this Class.
     */
    private static final Logger logger = LoggerFactory.getLogger(CityController.class);

    @Autowired
    private transient CityService cityService;

    @GetMapping
    public List<City> getPaths() {
        logger.info("> getCities");

        final List<City> cities = cityService.findAll();

        logger.info("< getCities");
        return cities;
    }

    @GetMapping("/{id}")
    public City getCitiesById(@PathVariable final Long id) {
        logger.info("> getCities");

        City city = cityService.findById(id);

        logger.info("< getCities");
        return city;
    }


}
