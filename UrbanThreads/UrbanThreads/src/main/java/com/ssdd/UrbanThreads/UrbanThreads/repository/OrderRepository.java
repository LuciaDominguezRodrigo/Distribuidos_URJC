package com.ssdd.UrbanThreads.UrbanThreads.repository;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Order;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderRepository {
    private final Map<Integer, Order> orders = new HashMap<>();
    private final AtomicInteger nextId = new AtomicInteger();
    private int selectedOrder = 0;

    public int getSelectedOrderId(){ return this.selectedOrder; }

    public Order getOrderById(int id) { return orders.get(id); }

    public List<Order> getAllOrders(){
        return new ArrayList<>(orders.values());
    }

    public void setSelectedOrderId(int id){ this.selectedOrder = id; }

    public Set<Integer> getAllOrdersId (){
        return orders.keySet();
    }

    public int addNewOrder(Order o){
        o.setOrderId(nextId.get());
        this.orders.put(nextId.getAndIncrement(), o);
        return o.getOrderId();
    }

    public Order getCurrentOrder(){ return orders.get(selectedOrder); }

    public Order findOrder(int id){
        return orders.get(id);
    }

    public void saveCurrentOrder(@NotNull Order order){
        orders.put(selectedOrder,order);
    }

    public void deleteCurrentOrder() { orders.remove(selectedOrder); }


}



