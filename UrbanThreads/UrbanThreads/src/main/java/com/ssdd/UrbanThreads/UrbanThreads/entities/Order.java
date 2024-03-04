package com.ssdd.UrbanThreads.UrbanThreads.entities;

import java.util.List;

public class Order {
    private long oderId;
    private String orderStatus;
    private List<Product> orderProducts;


    public Order(){};

    public Order(long oderId, String orderStatus, List<Product> orderProducts) {
        this.oderId = oderId;
        this.orderStatus = orderStatus;
        this.orderProducts = orderProducts;
    }
}
