package com.ssdd.UrbanThreads.UrbanThreads.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class DOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(name = "order_status")
    private String orderStatus;

    @OneToMany(mappedBy = "order")
    private List<DOrderedProduct> orderedProducts;


    public DOrder(){
        this.orderStatus = "PENDING";
    };

    public DOrder(int orderId, String orderStatus, List<DOrderedProduct> orderProducts) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderedProducts = orderProducts;
    }

}
