package com.hcmute.bookstoreapplication.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;


@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Integer id;

    @Column(name = "payment_price")
    private Float paymentPrice;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "payment_description")
    private String paymentDescription;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "order_id")
    @MapsId
    private Order order;
}
