package com.leanstacks.ws.web.api;


import com.leanstacks.ws.model.Shipment;
import com.leanstacks.ws.model.ShipmentForUpdateDTO;
import com.leanstacks.ws.service.ShipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    /**
     * The Logger for this Class.
     */
    private static final Logger logger = LoggerFactory.getLogger(ShipmentController.class);

    @Autowired
    private transient ShipmentService shipmentService;

    @GetMapping
    public List<Shipment> getShipments() {
        logger.info("> getShipments");

        final List<Shipment> shipments = shipmentService.findAll();

        logger.info("< getShipments");
        return shipments;
    }

    @GetMapping("/{id}")
    public Shipment getShipmentById(@PathVariable final Long id) {
        logger.info("> getShipment");

        Shipment shipment = shipmentService.findById(id);

        logger.info("< getShipment");
        return shipment;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Shipment createShipment(@RequestBody final Shipment shipment) {
        logger.info("> createShipment");

        final Shipment savedShipment = shipmentService.create(shipment);

        logger.info("< createShipment");
        return savedShipment;
    }

    @PutMapping("/{id}")
    public Shipment updateShipment(@PathVariable("id") final Long shipmentId, @RequestBody final ShipmentForUpdateDTO shipmentForUpdateDTO) {

        return shipmentService.update(shipmentId, shipmentForUpdateDTO);
    }

}
