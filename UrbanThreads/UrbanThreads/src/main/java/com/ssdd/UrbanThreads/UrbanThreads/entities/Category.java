package com.ssdd.UrbanThreads.UrbanThreads.entities;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column (name ="color")
    private String color;


    @Column (name = "description")
    private String description;

    // Relación uno a muchos con Product
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Product> products;

    public Category() {
    }

    public Category(String name, String color, String description) {
        this.name = name;
        this.color = color;
        this.description = description;
    }

}
