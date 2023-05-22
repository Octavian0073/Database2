package com.leanstacks.ws.service;

import com.leanstacks.ws.model.Path;
import com.leanstacks.ws.model.Route;

import java.util.List;

public interface PathService {

    /**
     * Find all Path entities.
     *
     * @return A List of Path objects.
     */
    List<Path> findAll();


    Path findById(Long id);


}
