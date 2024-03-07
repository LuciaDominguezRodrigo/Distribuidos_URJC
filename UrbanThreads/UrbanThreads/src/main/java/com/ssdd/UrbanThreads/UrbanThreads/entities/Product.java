package com.ssdd.UrbanThreads.UrbanThreads.entities;

import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class Product {

    private int id;
    private String name;
    private Category category;
    private double price;
    private Map<Size, Integer> availableSizes;
    private String description;
    private String photo;

    public Product(){}

    public Product(String name, Category category, double price, String photo, String description, Map<Size, Integer> sizeUnits) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.photo = photo;
        this.availableSizes = sizeUnits;
    }

}
