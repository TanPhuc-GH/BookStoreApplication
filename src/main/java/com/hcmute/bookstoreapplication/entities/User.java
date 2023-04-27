package com.hcmute.bookstoreapplication.entities;

import com.hcmute.bookstoreapplication.utils.EnumRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "first_name")
    @NotBlank(message = "")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "")
    private String lastName;

    @NotBlank(message = "")
    private String email;

    @NotBlank(message = "")
    private String password;

    @Column(name = "phone_number")
    @Size(max = 10)
    private String phoneNumber;

    @Column(name="default_address")
    @NotBlank(message = "")
    private String defaultAddress;

    @Column(name = "verification_code")
    private String verificationCode;

    private EnumRole  roles;

    @Column(name = "is_active")
    private Boolean isActive = false;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

    @OneToMany(mappedBy = "user")
    private List<Payment> payments;

    @OneToMany(mappedBy = "user")
    private  List<Order> orders;

}
