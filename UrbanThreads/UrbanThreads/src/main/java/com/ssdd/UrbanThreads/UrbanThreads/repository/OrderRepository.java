package com.ssdd.UrbanThreads.UrbanThreads.repository;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Order;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderRepository {
    private final Map<Integer, Order> orders = new HashMap<>();
    private final AtomicInteger nextId = new AtomicInteger();
    private final int selectedOrder = 0;

    public int getSelectedOrder(){ return this.selectedOrder; }

    public int getNextId() {
        return nextId.get();
    }

    public Order findOrder(int id){
        return orders.get(id);
    }

    public Collection<Order> findAllOrders() {
        return orders.values();
    }

    public Order saveOrder(@NotNull Order order, int numOrder){
        orders.put(numOrder,order);
        return order;
    }

    public Order updateOrder(int id, Order order){
        if (!orders.containsKey(id)) {
            return null;
        }
        order.setId(id);
        orders.put(id,order);
        return order;
    }

    public void deleteOrder(int id) {orders.remove(id);}



}



