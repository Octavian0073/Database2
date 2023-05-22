package com.leanstacks.ws.web.api;

import com.leanstacks.ws.model.Person;
import com.leanstacks.ws.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    /**
     * The Logger for this Class.
     */
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private transient PersonService personService;

    @GetMapping
    public List<Person> getPersons() {
        logger.info("> getPersons");

        final List<Person> person = personService.findAll();

        logger.info("< getPersons");
        return person;
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable final Long id) {
        logger.info("> getPerson");

        Person person = personService.findById(id);

        logger.info("< getPerson");
        return person;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@RequestBody final Person person) {
        logger.info("> createPerson");

        final Person savedPerson = personService.create(person);

        logger.info("< createPerson");
        return savedPerson;
    }
}
