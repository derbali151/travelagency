package com.ditraacademy.travelagency.utility;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtils {
    private final  String JWT_SECRET = "DITRASECRET";

    public String generateToken(String username){
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.ES256,JWT_SECRET)
                .compact();
        return token;
    }


}
