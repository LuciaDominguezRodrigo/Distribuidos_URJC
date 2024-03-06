package com.ssdd.UrbanThreads.UrbanThreads.services;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Order;;
import com.ssdd.UrbanThreads.UrbanThreads.repository.OrderRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order findOrder(Long id){
        return orderRepository.findOrder(id);
    }

    public Collection<Order> findAllOrders() {
        return orderRepository.findAllOrders();
    }

    public Order saveOrder(@NotNull Order order){
        return orderRepository.saveOrder(order);
    }

    public Order updateOrder(Long id, Order order){
        return orderRepository.updateOrder(id,order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteOrder(id);
    }

}
