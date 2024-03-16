package com.ssdd.UrbanThreads.UrbanThreads.DTO;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import lombok.Getter;
import lombok.Setter;

public class OrderedProductDTO {
    @Getter
    @Setter
    private int orderId; //This product belongs to this id number order
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
        this.orderId = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
    }

    public  OrderedProductDTO (Product product) { //Product object, only filled with specific order information
        this.orderId = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.size = product.getSize();
        this.color = product.getColor();
        this.quantity = product.getQuantity();
    }
}
