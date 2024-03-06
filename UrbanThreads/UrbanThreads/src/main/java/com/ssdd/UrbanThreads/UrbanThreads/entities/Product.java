package com.ssdd.UrbanThreads.UrbanThreads.entities;

import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;
import java.util.Set;

@Getter
@Setter
public class Product {

    private Long id;
    private String name;
    private Category category;
    private double price;
    private Set<String> sizes;
    private String description;
    private String photo;

    public Product(){}

    public Product(Long id,String name, Category category, Set<String> sizes, double price, String photo, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.sizes = sizes;
        this.price = price;
        this.description = description;
        this.photo = photo;

    }

}
