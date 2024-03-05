package com.ssdd.UrbanThreads.UrbanThreads.services;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Order;;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class OrderService {
    private final Map<Long, Order> orders = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong();


    public Order getOrder (Long id){
        return orders.get(id);
    }

    public Order createPorduct(@NotNull Order order){
        long id = nextId.incrementAndGet();
        order.setId(id);
        orders.put(id,order);
        return order;

    }

    public Collection<Order> getAllProducts() {
        return orders.values();
    }

    public Order updateProduct (Long id, Order order){
        if (!orders.containsKey(id)) {
            return null;
        }
        order.setId(id);
        orders.put(id,order);
        return order;
    }


    public void deleteOrder(Long id) {orders.remove(id);}




}
