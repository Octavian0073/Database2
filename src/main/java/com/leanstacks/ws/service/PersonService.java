package com.leanstacks.ws.service;

import com.leanstacks.ws.model.Person;

import java.util.List;

public interface PersonService {

    /**
     * Find all Person entities.
     *
     * @return A List of Person objects.
     */
    List<Person> findAll();


    Person findById(Long id);

    /**
     * Persists a Person entity in the data store.
     *
     * @param person A Person object to be persisted.
     * @return A persisted Person object or <code>null</code> if a problem occurred.
     */
    Person create(Person person);
}
