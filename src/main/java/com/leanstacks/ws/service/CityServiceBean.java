package com.leanstacks.ws.service;

import com.leanstacks.ws.model.City;
import com.leanstacks.ws.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceBean implements CityService{

    /**
     * The Logger for this Class.
     */
    private static final Logger logger = LoggerFactory.getLogger(CityServiceBean.class);

    private final transient CityRepository cityRepository;


    public CityServiceBean(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City findById(Long id) {
        return cityRepository.findById(id).get();
    }
}
