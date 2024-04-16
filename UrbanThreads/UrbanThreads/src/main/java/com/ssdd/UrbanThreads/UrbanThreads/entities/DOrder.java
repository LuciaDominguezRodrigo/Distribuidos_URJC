package com.ssdd.UrbanThreads.UrbanThreads.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class DOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "order_status")
    private OrderStatus orderStatus;

    /*@OneToMany(mappedBy = "id")
    private List<Product> orderProducts = new ArrayList<>();*/

    public DOrder(){
        this.orderStatus = OrderStatus.PENDING;
    };

    public DOrder(Long orderId, OrderStatus orderStatus, List<Product> orderProducts) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        //this.orderProducts = orderProducts;
    }

    /*
    public List<Product> getProducts() {
        return orderProducts;
    }*/

}
