package com.ssdd.UrbanThreads.UrbanThreads.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
public class DOrder {
    // Getters y setters
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(name = "order_date")
    private Date orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<DOrder> orderItems;

    // Constructor vacío (necesario para JPA)
    public DOrder() {
    }

    // Constructor con parámetros
    public DOrder(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Set<DOrder> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<DOrder> orderItems) {
        this.orderItems = orderItems;
    }
}
