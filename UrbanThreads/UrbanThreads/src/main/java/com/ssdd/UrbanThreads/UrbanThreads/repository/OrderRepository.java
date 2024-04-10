package com.ssdd.UrbanThreads.UrbanThreads.repository;


import com.ssdd.UrbanThreads.UrbanThreads.entities.Order;
import com.ssdd.UrbanThreads.UrbanThreads.entities.OrderStatus;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderRepository /*extends JpaRepository<Order,Long>*/{
    private final Map<Integer, Order> orders = new HashMap<>();
    private final AtomicInteger nextId = new AtomicInteger();
    private int selectedOrder = 0;

    public int getSelectedOrderId(){ return this.selectedOrder; }

    public Order getOrderById(int id) { return orders.get(id); }

    public List<Order> getAllOrders(){
        return new ArrayList<>(orders.values());
    }

    public void setSelectedOrderId(int id){ this.selectedOrder = id; }

    public List<Integer> getAllPendingOrdersId(){
        List<Integer> pendingOrders = new ArrayList<>();
        for (int id : orders.keySet()) {
            Order order = getOrderById(id);
            if(order.getOrderStatus().equals(OrderStatus.PENDING)){
                pendingOrders.add(id);
            }
        }
        return pendingOrders;
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

    public Order saveOrder(int id, @NotNull Order order) {
        if(!orders.containsKey(id)){
            this.addNewOrder(order);
        } else {
            orders.put(id,order);
        }
        return order;
    }

    public Order deleteOrderById(int id){
        return orders.remove(id);
    }

    public void deleteCurrentOrder() { orders.remove(selectedOrder); }


}



