package com.hcmute.bookstoreapplication.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product_image")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String path;

    private Boolean thumbnail;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
