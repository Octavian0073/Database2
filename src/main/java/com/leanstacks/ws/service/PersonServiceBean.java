package com.leanstacks.ws.service;

import com.leanstacks.ws.model.City;
import com.leanstacks.ws.model.Person;
import com.leanstacks.ws.model.Roles;
import com.leanstacks.ws.repository.CityRepository;
import com.leanstacks.ws.repository.PersonRepository;
import com.leanstacks.ws.repository.RolesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceBean implements PersonService{

    /**
     * The Logger for this Class.
     */
    private static final Logger logger = LoggerFactory.getLogger(PersonServiceBean.class);

    private final transient PersonRepository personRepository;
    private final transient CityRepository cityRepository;
    private final transient RolesRepository rolesRepository;

    public PersonServiceBean(PersonRepository personRepository, CityRepository cityRepository, RolesRepository rolesRepository) {
        this.personRepository = personRepository;
        this.cityRepository = cityRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id).get();
    }


    @Transactional
    @Override
    public Person create(final Person person) {
        logger.info("> create");

        // Ensure the entity object to be created does NOT exist in the
        // repository. Prevent the default behavior of save() which will update
        // an existing entity if the entity matching the supplied id exists.
        if (person.getId() != null) {
            logger.error("Attempted to create a Route, but id attribute was not null.");
            logger.info("< create");
            throw new IllegalArgumentException(
                    "Cannot create new Route with supplied id.  The id attribute must be null to create an entity.");
        }

        Optional<City> optionalCity = cityRepository.findById(person.getInCity().getId());
        City city = optionalCity.orElse(null);
        Optional<Roles> optionalRole = rolesRepository.findById(person.getRole().getId());
        Roles role = optionalRole.orElse(null);

        person.setInCity(city);
        person.setRole(role);

        final Person savedPerson = personRepository.save(person);

        logger.info("< create");
        return savedPerson;
    }
}
