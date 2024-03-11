package com.ssdd.UrbanThreads.UrbanThreads.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {
    private int id;
    private Long orderNumber;
    private String orderStatus;
    private List<Product> orderProducts;


    public Order(){};

    public Order(int id, Long orderNumber, String orderStatus, List<Product> orderProducts) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus;
        this.orderProducts = orderProducts;
    }

    public List<Product> getOrderProducts() {
        return orderProducts;
    }

}
