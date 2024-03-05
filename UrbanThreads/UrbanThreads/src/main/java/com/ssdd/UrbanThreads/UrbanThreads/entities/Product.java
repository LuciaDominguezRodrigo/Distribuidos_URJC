package com.ssdd.UrbanThreads.UrbanThreads.entities;

import java.sql.Blob;
import java.util.Set;

public class Product {

    private Long id;
    private String name;
    private Category category;
    private double price;
    private Set<String> sizes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<String> getSizes() {
        return sizes;
    }

    public void setSizes(Set<String> sizes) {
        this.sizes = sizes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    private String description;

    private Blob photo;

    public Product(){}

    public Product(Long id,String name, Category category, Set<String> sizes, double price, Blob photo, String descripcion) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.sizes = sizes;
        this.price = price;
        this.photo = photo;
        this.description = descripcion;
    }

}
