package com.leanstacks.ws.service;

import com.leanstacks.ws.model.Greeting;
import com.leanstacks.ws.model.Route;

import java.util.List;

public interface RouteService {

    /**
     * Find all Route entities.
     *
     * @return A List of Route objects.
     */
    List<Route> findAll();


    Route findById(Long id);

    /**
     * Persists a Greeting entity in the data store.
     *
     * @param route A Route object to be persisted.
     * @return A persisted Route object or <code>null</code> if a problem occurred.
     */
    Route create(Route route);
}
