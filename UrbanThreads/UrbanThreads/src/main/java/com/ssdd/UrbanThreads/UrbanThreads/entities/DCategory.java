package com.ssdd.UrbanThreads.UrbanThreads.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Entity
@Table(name = "categories")
public class DCategory {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    // Relación uno a muchos con Product
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<DProduct> products;

    // Constructor vacío (necesario para JPA)
    public DCategory() {
    }

    // Constructor con parámetros
    public DCategory(String name) {
        this.name = name;
    }

}
