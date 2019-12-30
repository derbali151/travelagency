package com.ditraacademy.travelagency.core.user;
import com.ditraacademy.travelagency.core.user.models.SignInRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    UserServices userServices;



    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user){
         return userServices.createUserService(user);
    }

    @GetMapping("/users")
    public List<User> getUser(){

        return  userServices.getUser();

    }
    @GetMapping("/auth/activation")
    public String activationAccount(@RequestParam String activationToken){
        return userServices.activationAccount(activationToken);
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id){
    return userServices.getUserById(id);
    }

    @DeleteMapping("/user/{id}")
   public  ResponseEntity<?> deletUser(@PathVariable int id){
     return  userServices.deletUser(id);
    }


    @PutMapping("/user/{id}")
   public ResponseEntity<?> updateUser(@PathVariable int id , @RequestBody User updateUser){
      return  userServices.updateUser(id,updateUser);
     }

     @PostMapping("/auth/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest){
        return userServices.signIn(signInRequest);

     }



}
