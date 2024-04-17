package com.ssdd.UrbanThreads.UrbanThreads.DTO;

import com.ssdd.UrbanThreads.UrbanThreads.entities.OrderedProduct;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderedProductDTO {

    private int orderId; //This product belongs to this id number order

    private String name;

    private double price;

    private String size;

    private String color;

    private int quantity;

    public OrderedProductDTO(int id,String name, double price, String size, String color, int quantity) {
        this.orderId = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
    }

    public  OrderedProductDTO (OrderedProduct product) { //Product object, only filled with specific order information
        this.orderId = product.getId();
        this.name = product.getName();
        this.price = product.getTotalPrice();
        this.size = product.getSize();
        this.color = product.getColor();
        this.quantity = product.getQuantity();
    }
}
