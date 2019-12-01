package com.ditraacademy.travelagency.core.hotel;


import com.ditraacademy.travelagency.core.chamber.chamber.Chamber;
import com.ditraacademy.travelagency.core.chamber.chamber.ChamberRepository;
import com.ditraacademy.travelagency.utility.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServices {


    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    ChamberRepository chamberRepository;

    public  ResponseEntity<?> getAllHotesls() {

        List<Hotel> hotels= hotelRepository.findAll();
        return  new ResponseEntity<>(hotels,HttpStatus.OK);
    }


    public ResponseEntity<?> createHotel(Hotel hotel) {

        for(Chamber chamber : hotel.getChambers()){
            Optional<Chamber> optionalChamber= chamberRepository.
                    findById(chamber.getId());
            if(!optionalChamber.isPresent())
                return new ResponseEntity<>(new ErrorResponseModel("wrong room id:" +chamber.getId()), HttpStatus.BAD_REQUEST.BAD_REQUEST);
        }
        hotel =hotelRepository.save(hotel);
        return  new ResponseEntity<>(hotel,HttpStatus.OK);

    }


    public ResponseEntity<?> updateHotel(int id, HotelUpdateModel hotelUpdateModel) {

        Optional<Hotel> hotelOptional=hotelRepository.findById(id);

        Hotel hotel = hotelOptional.get();

        for(int chamberId : hotelUpdateModel.getChamberId()){
            Optional<Chamber> optionalChamber = chamberRepository.findById(chamberId);

            if(!optionalChamber.isPresent())
                return new ResponseEntity<>(new ErrorResponseModel("wrong room id"+chamberId),HttpStatus.BAD_REQUEST);

            hotel.addChamber(optionalChamber.get());
        }

        hotelRepository.save(hotel);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
