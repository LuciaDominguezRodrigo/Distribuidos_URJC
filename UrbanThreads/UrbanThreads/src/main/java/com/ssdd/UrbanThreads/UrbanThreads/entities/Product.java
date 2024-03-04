package com.ssdd.UrbanThreads.UrbanThreads.entities;

import java.sql.Blob;
import java.util.Set;

public class Product {

    private String name;
    private Category category;
    private double price;
    private Set<String> sizes;
    private String description;

    private Blob photo;

    public Product(){}

    public Product(String name, Category category, Set<String> sizes, double price, Blob photo, String descripcion) {
        this.name = name;
        this.category = category;
        this.sizes = sizes;
        this.price = price;
        this.photo = photo;
        this.description = descripcion;
    }

}
