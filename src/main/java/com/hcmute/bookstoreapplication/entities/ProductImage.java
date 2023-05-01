package com.hcmute.bookstoreapplication.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_image")
public class ProductImage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_image_id", nullable = false)
    private Integer id;

    private String path;

    private Boolean thumbnail;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
