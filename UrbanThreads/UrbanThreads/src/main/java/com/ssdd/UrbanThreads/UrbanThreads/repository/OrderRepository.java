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

    public int getSelectedOrderId(){ return this.selectedOrder; }

    public Order getCurrentOrder(){ return orders.get(selectedOrder); }

    public int getNextId() {
        return nextId.get();
    }

    public Order findOrder(int id){
        return orders.get(id);
    }

    public Collection<Order> findAllOrders() {
        return orders.values();
    }

    public Order saveCurrentOrder(@NotNull Order order){
        orders.put(selectedOrder,order);
        return order;
    }

    public void deleteCurrentOrder() { orders.remove(selectedOrder); }


}



