package com.ditraacademy.travelagency.core.voyage;


import com.ditraacademy.travelagency.utility.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class VoyageServices {

    @Autowired
    VoyageRepository  voyageRepository;


    public ResponseEntity<?> createVoyage( Voyage voyage){
        Optional<Voyage> optionalVoyage =voyageRepository.findById(voyage.getDestination().getId());
        if(!optionalVoyage.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Wrong voyage id");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }
        voyage = voyageRepository.save(voyage);
       return  new ResponseEntity<>(voyage, HttpStatus.OK);
    }



    public ResponseEntity<?>  getVoyageById( Integer id){
        Optional<Voyage> voyageOptional = voyageRepository.findById(id);
        if(!voyageOptional.isPresent()){
            ErrorResponseModel errorResponseModel =  new ErrorResponseModel(" wrong voyage id ");
            return  new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }
        Voyage voyage = voyageOptional.get();

        return new ResponseEntity<>(voyage,HttpStatus.OK);
    }


    public List<Voyage> getVoyage(){

        return voyageRepository.findAll();

    }



    public ResponseEntity<?> putVoyage( Voyage voyage , Integer id){

        Optional<Voyage> optionalVoyage = voyageRepository.findById(id);
        if(!optionalVoyage.isPresent()){
          ErrorResponseModel errorResponseModel =  new ErrorResponseModel(" wrong voyage id ");
          return  new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }
        Voyage legcyVoyage = optionalVoyage.get();
        if(voyage.getTitre() != null)
           legcyVoyage.setTitre(voyage.getTitre());

        if(voyage.getDescription() != null)
            legcyVoyage.setDescription(voyage.getDescription());

        if(voyage.getDate() != null)
           legcyVoyage.setDate(voyage.getDate());

        if(voyage.getNbPlaces() != null)
            legcyVoyage.setNbPlaces(voyage.getNbPlaces());

        if(voyage.getPrix() != null)
           legcyVoyage.setPrix(voyage.getPrix());
        return  new ResponseEntity<>(legcyVoyage,HttpStatus.OK);

    }


    public ResponseEntity<?> deleteVoyage( Integer id ){
        Optional<Voyage> optionalVoyage = voyageRepository.findById(id);
        if(!optionalVoyage.isPresent()){
            return new ResponseEntity<>(new ErrorResponseModel("Invalid id "),HttpStatus.BAD_REQUEST);
        } else {
            voyageRepository.deleteById(id);
            return  new ResponseEntity<>(HttpStatus.OK);
        }

    }




}
