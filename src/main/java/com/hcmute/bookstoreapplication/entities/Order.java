package com.hcmute.bookstoreapplication.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Integer id;

    private Float total;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "date_shipped")
    private Date dateShipped;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "shipping_fee")
    private Float shippingFee;

    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "order")
    private Payment payment;

    @OneToOne(mappedBy = "order")
    private OrderDetail orderDetail;

}
