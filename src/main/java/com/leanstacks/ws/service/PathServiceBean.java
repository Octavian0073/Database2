package com.leanstacks.ws.service;

import com.leanstacks.ws.model.Path;
import com.leanstacks.ws.repository.CityRepository;
import com.leanstacks.ws.repository.PathRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PathServiceBean implements PathService{

    /**
     * The Logger for this Class.
     */
    private static final Logger logger = LoggerFactory.getLogger(PathServiceBean.class);

    private final transient PathRepository pathRepository;
    private final transient CityRepository cityRepository;


    public PathServiceBean(PathRepository pathRepository, CityRepository cityRepository) {
        this.pathRepository = pathRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public List<Path> findAll() {
        return pathRepository.findAll();
    }

    @Override
    public Path findById(Long id) {
        return pathRepository.findById(id).get();
    }
}
