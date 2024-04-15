package com.ssdd.UrbanThreads.UrbanThreads.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "products")
public class DProduct {
    @Getter
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;
    @Setter
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


    /*@Column (name ="availableSizes")
    private Map<Size,Integer> availableSizes;*/
    public DProduct() {
    }

    // Constructor con parámetros
    public DProduct(String name, DCategory category) {
        this.name = name;
        this.category = category;
    }

    @ElementCollection
    @CollectionTable(name = "product_sizes", joinColumns = @JoinColumn(name = "product_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "quantity")
    private Map<Size, Integer> availableSizes;

    public DProduct (String name, DCategory category, double price,Blob photo,String description, Map<Size, Integer>as) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.photo = photo;
        this.description = description;
        this.availableSizes = as;
    }


}
