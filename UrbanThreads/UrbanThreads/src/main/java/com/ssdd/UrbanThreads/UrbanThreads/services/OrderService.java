package com.ssdd.UrbanThreads.UrbanThreads.services;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Order;

import com.ssdd.UrbanThreads.UrbanThreads.entities.OrderStatus;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.repository.OrderRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Getter
    private long selectedOrder = 1;

    public Order getCurrentOrder() {
        Optional<Order> orderOptional = orderRepository.findById(selectedOrder);
        if(orderOptional.isPresent()){
            return orderOptional.get();
        }else{
            return null;
        }
    }

    public Order getOrderById(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if(orderOptional.isPresent()){
            return orderOptional.get();
        }else{
            return null;
        }
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public void changeCurrentOrder(Long orderId){
        this.selectedOrder = orderId;
    }

    public List<Long> getAllPendingOrdersId() {
        return orderRepository.findIdsByOrderStatus(OrderStatus.PENDING);
    }

    public Long addNewOrder (Order o){
        return orderRepository.save(o).getId();
    }

    public void saveOrder(Order currentOrder){
        orderRepository.save(currentOrder);
    }

    public void deleteCurrentOrder(){
        orderRepository.deleteById(selectedOrder);
    }


    public void deleteOrderById(Long id){
        orderRepository.deleteById(selectedOrder);
    }
}

