package com.ditraacademy.travelagency.core.user;

import com.ditraacademy.travelagency.core.user.models.SignInRequest;
import com.ditraacademy.travelagency.core.user.models.SignInResponse;
import com.ditraacademy.travelagency.utility.EmailUtils;
import com.ditraacademy.travelagency.utility.ErrorResponseModel;
import com.ditraacademy.travelagency.utility.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.jws.soap.SOAPBinding;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class UserServices implements UserDetailsService {

    @Autowired
    UserRepository userResporitory;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtils jwtUtils ;
    @Autowired
    EmailUtils emailUtils;
    @Value("${SERVER_HOST}")
    private String serverHost;


    public  ResponseEntity<?> signIn(SignInRequest signInRequest) {

        authenticationManager.authenticate(
               new  UsernamePasswordAuthenticationToken( signInRequest.getEmail(),signInRequest.getPassword()));
        String token = jwtUtils.generateToken(signInRequest.getEmail());

        return new ResponseEntity<>( new SignInResponse(token), HttpStatus.OK);
    }


    public ResponseEntity<?> createUserService(@RequestBody User user) {


        if (user.getName() == null)
            return new ResponseEntity<>(new ErrorResponseModel("Name filed required"), HttpStatus.BAD_REQUEST);
        if (user.getName().length() < 3)
            return new ResponseEntity<>(new ErrorResponseModel("Invalide name"), HttpStatus.BAD_REQUEST);
        if (user.getAge() == 0)
            return new ResponseEntity<>(new ErrorResponseModel("age filed required"), HttpStatus.BAD_REQUEST);
        if (user.getAge() < 0)
            return new ResponseEntity<>(new ErrorResponseModel("Invalide age"), HttpStatus.BAD_REQUEST);


        String hachedPassword = passwordEncoder().encode(user.getPassword()) ;

        user.setPassword(hachedPassword);

        String activationToken = passwordEncoder().encode(new Date().toString());
        user.setActivationToken(activationToken);

        String activateLink =serverHost+"auth/activation?activationToken="+activationToken ;
        String destination = user.getEmail();
        String subject = "Account activation";
        String text ="Hello Mr"+ user.getName() + ",\n\n activation link : " +activateLink;
        emailUtils.sendEmail(destination,subject,text);


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
        if(!userOptional.isPresent()){
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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Optional<User> optionalUser= userResporitory.findByEmail(username);

        if(!optionalUser.isPresent())
           return  null;


        return  new org.springframework.security.core.userdetails.User(username,optionalUser.get().getPassword(), AuthorityUtils.NO_AUTHORITIES);
    }


    @Bean
     private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public String activationAccount(String activationToken) {

        Optional<User> optionalUser = userResporitory.findByActivationToken(activationToken);

        if (!optionalUser.isPresent())
            return "worng activatedtoken";

        User user =optionalUser.get();
        if (user.getActivated())
            return "already activated ";
        user.setActivated(true);
        userResporitory.save(user);
        return "jawik behi ";
    }
}
