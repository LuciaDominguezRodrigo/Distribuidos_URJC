package com.ssdd.UrbanThreads.UrbanThreads.entities;

import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;
import java.util.Set;

@Getter
@Setter
public class Product {

    private int id;
    private String name;
    private Category category;
    private double price;
    private Size size;
    private int availableUnits;
    private String description;
    private String photo;

    public Product(){}

    public Product(int id, String name, Category category, Size size, double price, String photo, String description, int stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.size = size;
        this.price = price;
        this.description = description;
        this.photo = photo;
        this.availableUnits = stock;
    }

}
