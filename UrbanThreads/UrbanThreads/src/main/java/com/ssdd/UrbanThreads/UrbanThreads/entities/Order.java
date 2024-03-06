package com.ssdd.UrbanThreads.UrbanThreads.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {
    private Long id;
    private Long orderNumber;
    private String orderStatus;
    private List<Product> orderProducts;


    public Order(){};

    public Order(Long id, Long orderNumber, String orderStatus, List<Product> orderProducts) {
       this.id = id;
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus;
        this.orderProducts = orderProducts;
    }
}
