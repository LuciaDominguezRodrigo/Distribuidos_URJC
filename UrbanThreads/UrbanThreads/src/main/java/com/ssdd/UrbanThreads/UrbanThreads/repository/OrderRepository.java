package com.ssdd.UrbanThreads.UrbanThreads.repository;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Order;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderRepository {
    private final Map<Long, Order> orders = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong();


    public Order findOrder(Long id){
        return orders.get(id);
    }

    public Collection<Order> findAllOrders() {
        return orders.values();
    }

    public Order saveOrder(@NotNull Order order){
        long id = nextId.incrementAndGet();
        order.setId(id);
        orders.put(id,order);
        return order;
    }

    public Order updateOrder(Long id, Order order){
        if (!orders.containsKey(id)) {
            return null;
        }
        order.setId(id);
        orders.put(id,order);
        return order;
    }

    public void deleteOrder(Long id) {orders.remove(id);}
}



