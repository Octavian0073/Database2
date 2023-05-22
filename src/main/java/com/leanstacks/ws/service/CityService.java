package com.leanstacks.ws.service;

import com.leanstacks.ws.model.City;

import java.util.List;

public interface CityService {

    /**
     * Find all City entities.
     *
     * @return A List of City objects.
     */
    List<City> findAll();


    City findById(Long id);
}
