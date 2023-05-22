package com.leanstacks.ws.web.api;

import com.leanstacks.ws.model.Route;
import com.leanstacks.ws.service.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    /**
     * The Logger for this Class.
     */
    private static final Logger logger = LoggerFactory.getLogger(RouteController.class);

    @Autowired
    private transient RouteService routeService;

    @GetMapping
    public List<Route> getRoutes() {
        logger.info("> getRoutes");

        final List<Route> routes = routeService.findAll();

        logger.info("< getRoutes");
        return routes;
    }

    @GetMapping("/{id}")
    public Route getRouteById(@PathVariable final Long id) {
        logger.info("> getRoutes");

        Route route = routeService.findById(id);

        logger.info("< getRoutes");
        return route;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Route createRoute(@RequestBody final Route route) {
        logger.info("> createRoute");

        final Route savedRoute = routeService.create(route);

        logger.info("< createRoute");
        return savedRoute;
    }
}
