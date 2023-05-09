package com.hcmute.bookstoreapplication.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForgetPasswordResponse {
    private String email;
    private String status;
    public UserForgetPasswordResponse(){
        this.setEmail("");
        this.setStatus("")  ;
    }
}
