package com.leanstacks.ws.service;

import com.leanstacks.ws.model.*;
import com.leanstacks.ws.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ShipmentServiceBean implements ShipmentService{

    /**
     * The Logger for this Class.
     */
    private static final Logger logger = LoggerFactory.getLogger(ShipmentServiceBean.class);

    private final transient ShipmentRepository shipmentRepository;
    private final transient CityRepository cityRepository;
    private final transient PersonRepository personRepository;
    private final transient RouteRepository routeRepository;

    public ShipmentServiceBean(ShipmentRepository shipmentRepository, RouteRepository routeRepository, CityRepository cityRepository, PersonRepository personRepository) {
        this.shipmentRepository = shipmentRepository;
        this.routeRepository = routeRepository;
        this.cityRepository = cityRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<Shipment> findAll() {
        return shipmentRepository.findAll();
    }

    @Override
    public Shipment findById(Long id) {
        return shipmentRepository.findById(id).get();
    }

    @Override
    public Optional<Shipment> findOne(final Long id) {
        logger.info("> findOne {}", id);

        final Optional<Shipment> shipmentOptional = shipmentRepository.findById(id);

        logger.info("< findOne {}", id);
        return shipmentOptional;
    }



    @Transactional
    @Override
    public Shipment create(final Shipment shipment) {
        logger.info("> create");

        // Ensure the entity object to be created does NOT exist in the
        // repository. Prevent the default behavior of save() which will update
        // an existing entity if the entity matching the supplied id exists.
        if (shipment.getId() != null) {
            logger.error("Attempted to create a Route, but id attribute was not null.");
            logger.info("< create");
            throw new IllegalArgumentException(
                    "Cannot create new Route with supplied id.  The id attribute must be null to create an entity.");
        }


        Optional<Person> optionalSender = personRepository.findById(shipment.getSender().getId());
        Person sender = optionalSender.orElse(null);
        Optional<Person> optionalReceiver = personRepository.findById(shipment.getReceiver().getId());
        Person receiver = optionalReceiver.orElse(null);
        Optional<Route> optionalRoute = routeRepository.findById(shipment.getRoute().getId());
        Route route = optionalRoute.orElse(null);


        shipment.setSender(sender);
        shipment.setReceiver(receiver);
        shipment.setRoute(route);

        final Shipment savedShipment = shipmentRepository.save(shipment);

        logger.info("< create");
        return savedShipment;
    }


    @Transactional
    @Override
    public Shipment update(final Long shipmentId, final ShipmentForUpdateDTO shipmentForUpdateDTO) {
        logger.info("> update {}", shipmentId);


        // findOne returns an Optional which will throw NoSuchElementException when null.
        // This will prevent the default behavior of save() which will persist a new
        // entity if the entity matching the id does not exist
        final Shipment shipmentToUpdate = findOne(shipmentId).orElseThrow(() -> new NoSuchElementException("Shipment not found"));

        if (shipmentForUpdateDTO.getCurrentCity() != null) {
            shipmentToUpdate.setCurrentCity(shipmentForUpdateDTO.getCurrentCity());
        }
        if (shipmentForUpdateDTO.getPackageStatus() != null) {
            shipmentToUpdate.setPackageStatus(shipmentForUpdateDTO.getPackageStatus());
        }
        if (shipmentForUpdateDTO.getReturnStarted() != null) {
            shipmentToUpdate.setReturnStarted(shipmentForUpdateDTO.getReturnStarted());
        }
        if (shipmentForUpdateDTO.getPackageReturned() != null) {
            shipmentToUpdate.setPackageReturned(shipmentForUpdateDTO.getPackageReturned());
        }
        final Shipment updatedShipment = shipmentRepository.save(shipmentToUpdate);

        logger.info("< update {}", shipmentId);
        return updatedShipment;
    }
}
