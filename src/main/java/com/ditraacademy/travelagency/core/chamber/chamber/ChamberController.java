package com.ditraacademy.travelagency.core.chamber.chamber;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChamberController {
    @Autowired
    ChamberService chamberService;



    @PostMapping("/chamber")
    public ResponseEntity<?> createDestination(@RequestBody Chamber chamber){
        return chamberService.createDestination(chamber);
    }

    @GetMapping("/chambers")
    public ResponseEntity<?> getAllChambers(){
        return chamberService.getChambers();
    }

}
