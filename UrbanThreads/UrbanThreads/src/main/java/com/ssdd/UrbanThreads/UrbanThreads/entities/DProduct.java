package com.ssdd.UrbanThreads.UrbanThreads.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Blob;

@Entity
@Table(name = "products")
public class DProduct {
    @Getter
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private DCategory category;

    @Column (name = "price")
    private double price;

    @Lob
    @Column (name = "photo")
    private Blob photo;

    @Column (name = "description")
    private String description;


    public DProduct() {
    }

    // Constructor con parámetros
    public DProduct(String name, DCategory category) {
        this.name = name;
        this.category = category;
    }

    public DProduct (String name, DCategory category, double price,Blob photo,String description) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.photo = photo;
        this.description = description;
    }


}
