package com.ditraacademy.travelagency.core.chamber.chamber;
import com.ditraacademy.travelagency.core.chamber.categorieChamber.CategorieChamber;
import com.ditraacademy.travelagency.core.chamber.categorieChamber.CategorieChamberRepository;
import com.ditraacademy.travelagency.core.chamber.typeChamber.TypeChamber;
import com.ditraacademy.travelagency.core.chamber.typeChamber.TypeChamberRepository;
import com.ditraacademy.travelagency.utility.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ChamberService {

    @Autowired
    ChamberRepository chamberRepository;
    @Autowired
    TypeChamberRepository typeChamberRepository;
    @Autowired
    CategorieChamberRepository categorieChamberRepository;



    public ResponseEntity<?> createDestination(Chamber chamber) {
        Optional<TypeChamber> optionalTypeChambertypeChamber = typeChamberRepository.findById(chamber.getTypeChamber().getId());


        if (!optionalTypeChambertypeChamber.isPresent())
            return new ResponseEntity<>(new ErrorResponseModel("Invalide Id"), HttpStatus.BAD_REQUEST);

        Optional<CategorieChamber> optionalCategorieChamber = categorieChamberRepository.findById(chamber.getCategorieChamber().getId());

        if (!optionalCategorieChamber.isPresent())
            return new ResponseEntity<>(new ErrorResponseModel("Invalide Id"), HttpStatus.BAD_REQUEST);

        Optional<Chamber> optionalChamber = chamberRepository.findByCategorieChamberAndTypeChamber(
                optionalCategorieChamber.get(), optionalTypeChambertypeChamber.get());
        if (optionalChamber.isPresent())
            return new ResponseEntity<>(new ErrorResponseModel("chamber existe"), HttpStatus.BAD_REQUEST);

        chamber = chamberRepository.save(chamber);
        return new ResponseEntity<>(chamber, HttpStatus.OK);

  //      try {
//            chambre  = chambreRepository.save(chambre);
//            return  new ResponseEntity<>(chambre,HttpStatus.OK);
//        } catch (Exception exception) {
//
//            if(exception.getMessage().contains("could not execute statement"))
//                return  new ResponseEntity<>(new ErrorResponseModel("chambre existante"),HttpStatus.BAD_REQUEST);
//            else
//                return  new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//        }


        }


    public ResponseEntity<?> getChambers() {

    List<Chamber> chambers = chamberRepository.findAll();
    return new ResponseEntity<>(chambers,HttpStatus.OK);
    }
}
