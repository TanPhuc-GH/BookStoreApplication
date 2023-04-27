package com.hcmute.bookstoreapplication.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String itemName;

    private Integer quantity;

    private Float price;

    @OneToOne
    @JoinColumn(name = "product_id")
    @MapsId
    private Product product;
}
