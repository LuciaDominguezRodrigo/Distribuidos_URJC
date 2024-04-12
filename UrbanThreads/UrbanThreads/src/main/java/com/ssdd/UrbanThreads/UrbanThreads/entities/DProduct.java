package com.ssdd.UrbanThreads.UrbanThreads.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Getter;

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

    // Otros atributos de producto como precio, descripción, etc.

    // Constructor vacío (necesario para JPA)
    public DProduct() {
    }

    // Constructor con parámetros
    public DProduct(String name, DCategory category) {
        this.name = name;
        this.category = category;
    }

}
