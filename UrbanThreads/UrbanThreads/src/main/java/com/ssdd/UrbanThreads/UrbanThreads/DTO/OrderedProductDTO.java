package com.ssdd.UrbanThreads.UrbanThreads.DTO;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class OrderedProductDTO {
    private int id;
    @Getter
    @Setter
    private String name;
    @Setter
    @Getter
    private double price;
    @Getter
    @Setter
    private String size;
    @Getter
    @Setter
    private String color;
    @Setter
    @Getter
    private int quantity;

    public OrderedProductDTO(int id,String name, double price, String size, String color, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
    }

    public  OrderedProductDTO (Product product) { //Product object, only filled with specific order information
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.size = product.getSize();
        this.color = product.getColor();
        this.quantity = product.getQuantity();
    }
}
