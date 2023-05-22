package com.leanstacks.ws.service;

import com.leanstacks.ws.model.Shipment;
import com.leanstacks.ws.model.ShipmentForUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface ShipmentService {

    /**
     * Find all Shipment entities.
     *
     * @return A List of Shipment objects.
     */
    List<Shipment> findAll();


    Shipment findById(Long id);


    /**
     * Find a single Shipment entity by primary key identifier. Returns an Optional wrapped Shipment.
     *
     * @param id A Long primary key identifier.
     * @return A Optional Shipment
     */
    Optional<Shipment> findOne(Long id);


    Shipment update(Long shipmentId, ShipmentForUpdateDTO shipmentForUpdateDTO);


    /**
     * Persists a Greeting entity in the data store.
     *
     * @param shipment A Shipment object to be persisted.
     * @return A persisted Shipment object or <code>null</code> if a problem occurred.
     */
    Shipment create(Shipment shipment);


}
