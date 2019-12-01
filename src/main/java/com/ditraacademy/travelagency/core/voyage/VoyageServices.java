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
//        Optional<Voyage> optionalVoyage =voyageRepository.findById(voyage.getDestination().getId());
//        if(!optionalVoyage.isPresent()){
//            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Wrong voyage id");
//            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
//        }
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


    public ResponseEntity<?> getAllVoyage(){

        List<Voyage> voyages = voyageRepository.findAll();
        return  new  ResponseEntity<>(voyages ,HttpStatus.OK) ;

    }



    public ResponseEntity<?> putVoyage( Voyage voyage , Integer id){

        Optional<Voyage> optionalVoyage = voyageRepository.findById(id);


        if (!optionalVoyage.isPresent())
            return new ResponseEntity<>(new ErrorResponseModel(" Invalid voyage"), HttpStatus.BAD_REQUEST);

        Voyage voyageLegacy = optionalVoyage.get();

        if ( voyage.getDate()!=null ||   voyageLegacy.getDate() != voyage.getDate())
            voyageLegacy.setDate(voyage.getDate());

        if (   voyage.getDescription()!=null || !(voyageLegacy.getDescription().equals(voyage.getDescription())))
            voyageLegacy.setDescription(voyage.getDescription());

        if (  voyage.getNbPlaces()!=voyageLegacy.getNbPlaces())
            voyageLegacy.setNbPlaces(voyage.getNbPlaces());

        if ( voyage.getPrix()!=voyageLegacy.getPrix())
            voyageLegacy.setPrix(voyage.getPrix());

        if ( voyage.getTitre()!= null || !(voyage.getTitre().equals(voyageLegacy.getTitre())))
            voyageLegacy.setTitre(voyage.getTitre());

        voyageRepository.save(voyageLegacy);

        return  new ResponseEntity<>(HttpStatus.OK);

    }


    public ResponseEntity<?> deleteVoyage( Integer id ){
        Optional<Voyage> optionalVoyage = voyageRepository.findById(id);
        if(!optionalVoyage.isPresent())
            return new ResponseEntity<>(new ErrorResponseModel("Invalid id "),HttpStatus.BAD_REQUEST);

        voyageRepository.deleteById(id);
        return  new ResponseEntity<>(HttpStatus.OK);
        }



    public ResponseEntity<?> findAllByPrixBetweenAndNbPlacesNot( double max ,  double min){

       List<Voyage> voyages = voyageRepository.findAllByPrixBetweenAndNbPlacesNot(min, max,0);


        return new ResponseEntity<>(voyages,HttpStatus.OK);

    }




}
