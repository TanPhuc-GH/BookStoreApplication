package com.hcmute.bookstoreapplication.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id", nullable = false)
    private Integer id;

    private Integer quantity;

    private Float price;

    @OneToOne
    @JoinColumn(name = "order_id")
    @MapsId
    private Order order;

    @OneToMany(mappedBy = "orderDetail")
    private List<Item> items;


}
