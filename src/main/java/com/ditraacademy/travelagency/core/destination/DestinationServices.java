package com.ditraacademy.travelagency.core.destination;


import com.ditraacademy.travelagency.core.user.User;
import com.ditraacademy.travelagency.utility.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationServices {
    @Autowired
    DestinationRepository destinationRepository;


    public ResponseEntity<?> createDestination( Destination destination){

      destination = destinationRepository.save(destination);
      return  new ResponseEntity<>(destination,HttpStatus.OK);
    }



    public ResponseEntity<?> getDestinationById( Integer id){

        Optional<Destination> optionalDestination= destinationRepository.findById(id);
        if(optionalDestination.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("wrong destination id ");
            return  new  ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST);
        }
        Destination destination = optionalDestination.get();
        return new ResponseEntity<>(destination , HttpStatus.OK);
    }

    public List<Destination> getDestination(){

        return destinationRepository.findAll();
    }

    public ResponseEntity<?> putDestination( Destination destination ,  Integer id){

        Optional<Destination> optionalDestination = destinationRepository.findById(id);
        if(!optionalDestination.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("worng destination id");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }
        Destination legacyDestination = optionalDestination.get();
        if(destination.getNom() != null)
           legacyDestination.setNom(destination.getNom());

        if(destination.getDescription() != null)
           legacyDestination.setDescription(destination.getDescription());
        destinationRepository.save(legacyDestination);
        return new ResponseEntity<>(HttpStatus.OK);

    }


    public ResponseEntity<?> deleteDestination( Integer id ){


        Optional<Destination> optionalDestination = destinationRepository.findById(id);
        if(!optionalDestination.isPresent()){
            return new ResponseEntity<>(new ErrorResponseModel("Invalid id "),HttpStatus.BAD_REQUEST);
        } else {
            destinationRepository.deleteById(id);
            return new ResponseEntity<>( HttpStatus.OK);
        }

    }




}
