package com.ssdd.UrbanThreads.UrbanThreads.entities;

import java.util.List;

public class Order {
    private Long id;
    private Long oderNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOderNumber() {
        return oderNumber;
    }

    public void setOderNumber(Long oderNumber) {
        this.oderNumber = oderNumber;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Product> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<Product> orderProducts) {
        this.orderProducts = orderProducts;
    }

    private String orderStatus;
    private List<Product> orderProducts;


    public Order(){};

    public Order(Long id, Long oderNumber, String orderStatus, List<Product> orderProducts) {
       this.id = id;
        this.oderNumber = oderNumber;
        this.orderStatus = orderStatus;
        this.orderProducts = orderProducts;
    }
}
