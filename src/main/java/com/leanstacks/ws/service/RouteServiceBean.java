package com.leanstacks.ws.service;

import com.leanstacks.ws.Application;
import com.leanstacks.ws.model.City;
import com.leanstacks.ws.model.Path;
import com.leanstacks.ws.model.Route;
import com.leanstacks.ws.repository.CityRepository;
import com.leanstacks.ws.repository.RouteRepository;
import com.leanstacks.ws.repository.PathRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceBean implements RouteService{

    /**
     * The Logger for this Class.
     */
    private static final Logger logger = LoggerFactory.getLogger(RouteServiceBean.class);

    private final transient RouteRepository routeRepository;
    private final transient CityRepository cityRepository;


    public RouteServiceBean(RouteRepository routeRepository, CityRepository cityRepository) {
        this.routeRepository = routeRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    @Override
    public Route findById(Long id) {
        return routeRepository.findById(id).get();
    }


    @Transactional
    @Override
    public Route create(final Route route) {
        logger.info("> create");

        // Ensure the entity object to be created does NOT exist in the
        // repository. Prevent the default behavior of save() which will update
        // an existing entity if the entity matching the supplied id exists.
        if (route.getId() != null) {
            logger.error("Attempted to create a Route, but id attribute was not null.");
            logger.info("< create");
            throw new IllegalArgumentException(
                    "Cannot create new Route with supplied id.  The id attribute must be null to create an entity.");
        }

        Optional<City> optionalCityFrom = cityRepository.findById(route.getFromCity().getId());
        City from = optionalCityFrom.orElse(null);
        Optional<City> optionalCityTo = cityRepository.findById(route.getToCity().getId());
        City to = optionalCityTo.orElse(null);

        route.setFromCity(from);
        route.setToCity(to);

        final Route savedRoute = routeRepository.save(route);

        logger.info("< create");
        return savedRoute;
    }
}
