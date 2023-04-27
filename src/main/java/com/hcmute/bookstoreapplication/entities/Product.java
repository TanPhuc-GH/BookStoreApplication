package com.hcmute.bookstoreapplication.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String productName;

    private Integer quantity;

    private String description;

    private Float price;

    private String publisher;

    private Date publicationDate;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImages;

    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Categories categories;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToOne(mappedBy = "product")
    private Item item;

}
