package com.ditraacademy.travelagency.core.hotel;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@Api(tags = "Hotel")
public class HotelController {


    @Autowired
    HotelServices hotelServices;


    @PostMapping("/hotel")
    public ResponseEntity<?> createHotel(@RequestBody Hotel hotel){
        return hotelServices.createHotel(hotel);
    }

    @GetMapping("/hotels")
    public ResponseEntity<?> getAllHotels(){
        return hotelServices.getAllHotesls();
    }

    @PutMapping("/hotel/{id}/ajouterChamber")
    public ResponseEntity<?> updateHotel(@RequestBody HotelUpdateModel hotelUpdateModel, @PathVariable int id){
        return  hotelServices.updateHotel(id,hotelUpdateModel);
    }

}
