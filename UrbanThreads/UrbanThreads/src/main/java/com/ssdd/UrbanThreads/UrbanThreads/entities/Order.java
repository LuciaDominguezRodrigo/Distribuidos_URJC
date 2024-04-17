package com.ssdd.UrbanThreads.UrbanThreads.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order")
    private List<OrderedProduct> orderedProducts;


    public Order(){
        this.orderStatus = OrderStatus.PENDING;
    };

    public Order(int orderId, OrderStatus orderStatus, List<OrderedProduct> orderProducts) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderedProducts = orderProducts;
    }

}
