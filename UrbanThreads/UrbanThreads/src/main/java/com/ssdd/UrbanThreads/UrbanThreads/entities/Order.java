package com.ssdd.UrbanThreads.UrbanThreads.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Order {
    @Getter
    private int orderId;
    private Long orderNumber;
    private String orderStatus;
    private List<Product> orderProducts = new ArrayList<>();


    public Order(){};

    public Order(int orderId, Long orderNumber, String orderStatus, List<Product> orderProducts) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus;
        this.orderProducts = orderProducts;
    }


    public List<Product> getProducts() {
        return orderProducts;
    }

}
