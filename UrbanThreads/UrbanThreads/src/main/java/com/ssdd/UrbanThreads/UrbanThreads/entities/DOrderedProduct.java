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
    private int orderId;

    @Id
    private int productId;

    @Column(nullable = false)
    private String name;

    private String size;

    private int selectedSizeAvailableUnits;

    private String color;

    private int quantity;

    @Column (nullable = false)
    private double totalPrice;

}
