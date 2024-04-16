package com.ssdd.UrbanThreads.UrbanThreads.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ordered_products")
@Getter
@Setter
public class DOrderedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private DOrder order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private DProduct product;

    @Column(nullable = false)
    private String name;

    private String size;

    private String color;

    private int quantity;

    @Column (nullable = false)
    private double totalPrice;

}
