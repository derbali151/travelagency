package com.ditraacademy.travelagency.core;

import com.ditraacademy.travelagency.utility.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public class UserServices {
    @Autowired
    UserResporitory userResporitory;

    public ResponseEntity<?> createUserService(@RequestBody User user) {

        if (user.getName() == null)
            return new ResponseEntity<>(new ErrorResponseModel("Name filed required"), HttpStatus.BAD_REQUEST);
        if (user.getName().length() < 3)
            return new ResponseEntity<>(new ErrorResponseModel("Invalide name"), HttpStatus.BAD_REQUEST);
        if (user.getAge() == 0)
            return new ResponseEntity<>(new ErrorResponseModel("age filed required"), HttpStatus.BAD_REQUEST);
        if (user.getAge() < 0)
            return new ResponseEntity<>(new ErrorResponseModel("Invalide age"), HttpStatus.BAD_REQUEST);


        user = userResporitory.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public User getUserById(@PathVariable int id){
        Optional<User> userOptional =userResporitory.findById(id);
        User user = userOptional.get();
        return user;
    }

    public List<User> getUser(){

        return  userResporitory.findAll();
    }

    public  ResponseEntity<?> deletUser(@PathVariable int id){

        Optional<User> userOptional =userResporitory.findById(id);
        if(userOptional.isPresent()){
            return new ResponseEntity<>(new ErrorResponseModel("Invalid id "),HttpStatus.BAD_REQUEST);
        } else {
            userResporitory.deleteById(id);
            return new ResponseEntity<>( HttpStatus.OK);
        }

    }

    public ResponseEntity<?> updateUser(@PathVariable int id , @RequestBody User updateUser){
        Optional<User> userOptional = userResporitory.findById(id);
        if(!userOptional.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("worng user id");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }
        User legacyUser = userOptional.get();
        if(updateUser.getName() != null)
            if(updateUser.getName().length() < 3)
                return new ResponseEntity<>(new ErrorResponseModel("Invalid name"),HttpStatus.BAD_REQUEST);
            else
                legacyUser.setName(updateUser.getName());

        if(updateUser.getAge() !=0)
            if(updateUser.getAge() < 0)
                return new ResponseEntity<>(new ErrorResponseModel("Invalid age"),HttpStatus.BAD_REQUEST);
            else
                legacyUser.setAge(updateUser.getAge());


        userResporitory.save(legacyUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
