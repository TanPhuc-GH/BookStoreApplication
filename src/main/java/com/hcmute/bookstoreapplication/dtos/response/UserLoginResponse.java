package com.hcmute.bookstoreapplication.dtos.response;

import com.hcmute.bookstoreapplication.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class UserLoginResponse {
    private String email;
    private String password;
    private String status;
    private Integer id;
    public UserLoginResponse(){
        this.setStatus("");
        this.setPassword("");
        this.setEmail("");
        this.setId(0);
    }
}
