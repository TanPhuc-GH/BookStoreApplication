package com.hcmute.bookstoreapplication.dtos;

import com.hcmute.bookstoreapplication.entities.User;
import com.hcmute.bookstoreapplication.utils.EnumRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends User {
    private Integer id;
    private String address;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private EnumRole role;
    private Boolean isActive;
    private String codeActive;
    private String status;
    public UserDTO(User user){
        this.setId(user.getId());
        this.setAddress(user.getDefaultAddress());
        this.setEmail(user.getEmail());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setPassword(user.getPassword());
        this.setPhoneNumber(user.getPhoneNumber());
        this.setRole(user.getRoles());
        this.setIsActive(user.getIsActive());
        this.setCodeActive(user.getVerificationCode());
    }
}
