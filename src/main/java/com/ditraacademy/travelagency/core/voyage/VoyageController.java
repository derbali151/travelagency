package com.ditraacademy.travelagency.core.voyage;

import com.ditraacademy.travelagency.core.destination.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class VoyageController {

    @Autowired
    VoyageServices voyageServices;

    @PostMapping("/voyage")
    public ResponseEntity<?> createVoyage(@RequestBody Voyage voyage){
        return  voyageServices.createVoyage(voyage);
    }


    @GetMapping("/voyage/{id}")
    public  ResponseEntity<?> getVoyageById(@PathVariable Integer id){
        return voyageServices.getVoyageById(id);
    }

    @GetMapping("/voyages")
    public List<Voyage> getVoyage(){
        return voyageServices.getVoyage();
    }


    @PutMapping("/voyage/{id}")
    public ResponseEntity<?> putVoyage(@RequestBody Voyage voyage , @PathVariable Integer id){
        return  voyageServices.putVoyage(voyage , id);
    }

    @DeleteMapping("/voyage/{id}")
    public ResponseEntity<?> deleteVoyage(@PathVariable Integer id ){
        return  voyageServices.deleteVoyage(id);

    }

    @GetMapping("/voyages/priceBetween")
    public ResponseEntity<?> findAllByPrixBetweenAndNbPlacesNot (@RequestParam double max , @RequestParam double min){
        return voyageServices.findAllByPrixBetweenAndNbPlacesNot(max, min);
    }

}
