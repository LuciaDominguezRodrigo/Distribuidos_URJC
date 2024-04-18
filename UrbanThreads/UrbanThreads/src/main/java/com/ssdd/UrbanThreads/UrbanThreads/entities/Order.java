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
    private Long id;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderedProduct> orderedProducts;


    public Order(){
        this.orderStatus = OrderStatus.PENDING;
    };

    public Order(Long id, OrderStatus orderStatus, List<OrderedProduct> orderProducts) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.orderedProducts = orderProducts;
    }

}
