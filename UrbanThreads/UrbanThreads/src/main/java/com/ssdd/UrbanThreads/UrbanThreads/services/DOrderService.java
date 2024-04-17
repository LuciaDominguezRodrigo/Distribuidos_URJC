package com.ssdd.UrbanThreads.UrbanThreads.services;

import com.ssdd.UrbanThreads.UrbanThreads.entities.DOrder;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Order;

import com.ssdd.UrbanThreads.UrbanThreads.entities.OrderStatus;
import com.ssdd.UrbanThreads.UrbanThreads.repository.DOrderRepository;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DOrderService {

    @Autowired
    private DOrderRepository orderRepository;

    @Getter
    private int selectedOrder = 0;

    public DOrder getCurrentOrder() {
        return orderRepository.findByOrderId(selectedOrder);
    }

    public DOrder getOrderById(int id) { return orderRepository.findByOrderId(id); }

    public List<DOrder> getAllOrders(){
        return orderRepository.findAll();
    }
    public void changeCurrentOrder(int orderId){
        this.selectedOrder = orderId;
    }

    public List<Integer> getAllPendingOrdersId() {
        return orderRepository.findOrderIdsByOrderStatus("PENDING");
    }

    public int addNewOrder (DOrder o){
        return orderRepository.save(o).getOrderId();
    }

    public void saveOrder(DOrder currentOrder){
        orderRepository.save(currentOrder);
    }

    public void deleteCurrentOrder(){
        orderRepository.deleteOrderByOrderId(selectedOrder);
    }


    public DOrder deleteOrderById(int id){
        return orderRepository.deleteOrderByOrderId(id);
    }

}

