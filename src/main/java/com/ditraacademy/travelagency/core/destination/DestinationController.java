package com.ditraacademy.travelagency.core.destination;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api(tags = "Destination")
public class DestinationController {
    @Autowired
    DestinationServices destinationServices;

    @PostMapping("/destination")
    public ResponseEntity<?> createDestination(@RequestBody Destination destination){
        return  destinationServices.createDestination(destination);
    }


    @GetMapping("/destination/{id}")
    @ApiOperation(value = "Destination", response = Destination.class)
    public ResponseEntity<?> getDestinationById(@PathVariable Integer id){
        return destinationServices.getDestinationById(id);
    }

    @GetMapping("/destinations")
    public List<Destination> getDestination(){
        return destinationServices.getDestination();
    }

    @PutMapping("/destination/{id}")
    public ResponseEntity<?> putDestination(@RequestBody Destination destination , @PathVariable Integer id){
        return  destinationServices.putDestination(destination,id);
    }

    @DeleteMapping("/Destination/{id}")
    public ResponseEntity<?> deleteDestination(@PathVariable Integer id ){
        return  destinationServices.deleteDestination(id);
    }



}
