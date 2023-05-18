package com.hcmute.bookstoreapplication.dtos;

import com.hcmute.bookstoreapplication.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String role;
    private String address;

    public UserInfoDTO(User user){
        this.setId(user.getId());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setPhoneNumber(user.getPhoneNumber());
        this.setRole(user.getRoles().name());
        this.setAddress(user.getDefaultAddress());
    }
}
