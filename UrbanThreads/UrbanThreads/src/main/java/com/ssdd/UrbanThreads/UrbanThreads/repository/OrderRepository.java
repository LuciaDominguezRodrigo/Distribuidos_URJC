package com.ssdd.UrbanThreads.UrbanThreads.repository;


import com.ssdd.UrbanThreads.UrbanThreads.entities.Order;
import com.ssdd.UrbanThreads.UrbanThreads.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    public Order findById(int id);

    @Query("SELECT o.id FROM Order o WHERE o.orderStatus = :status")
    List<Integer> findIdsByOrderStatus(OrderStatus status);

    public Order deleteOrderById(int id);
}
