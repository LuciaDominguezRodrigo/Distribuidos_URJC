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
    private OrderStatus orderStatus;
    private List<Product> orderProducts = new ArrayList<>();


    public Order(){
        this.orderStatus = OrderStatus.PENDING;
    };

    public Order(int orderId, OrderStatus orderStatus, List<Product> orderProducts) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderProducts = orderProducts;
    }


    public List<Product> getProducts() {
        return orderProducts;
    }

}
