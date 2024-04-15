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

    @Setter
    @Column(name = "name")
    private String name;

    @Setter
    @Column (name ="color")
    private String color;

    @Setter
    @Column (name = "description")
    private String description;

    // Relación uno a muchos con Product
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<DProduct> products;

    public DCategory() {
    }

    public DCategory (String name, String color, String description) {
        this.name = name;
        this.color = color;
        this.description = description;
    }

}
