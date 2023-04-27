package com.hcmute.bookstoreapplication.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private Integer id;

    private String address;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
