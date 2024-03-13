package com.ssdd.UrbanThreads.UrbanThreads.DTO;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class ProductDTO {
    private int id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String category;
    @Getter
    @Setter
    private String description;
    @Setter
    @Getter
    private double price;
    @Getter
    @Setter
    private String photo;
    @Setter
    @Getter
    private Map<Size, Integer> availableSizes;

    public ProductDTO(int id,String name, String category, String description, double price, String photo, Map<Size, Integer> availableSizes) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.photo = photo;
        this.availableSizes = availableSizes;

    }

    public  ProductDTO (Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory().getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.photo = product.getPhoto();
        this.availableSizes  = product.getAvailableSizes();

    }
}



