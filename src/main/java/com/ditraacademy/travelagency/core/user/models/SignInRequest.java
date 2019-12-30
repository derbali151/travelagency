package com.ditraacademy.travelagency.core.user.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignInRequest {

    private String email;
    private String password;
}
