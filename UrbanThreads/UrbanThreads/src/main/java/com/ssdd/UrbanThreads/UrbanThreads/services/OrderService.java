package com.ssdd.UrbanThreads.UrbanThreads.services;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Order;;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.repository.OrderRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order findOrder(Long id) {
        return orderRepository.findOrder(id);
    }

    public Collection<Order> findAllOrders() {
        return orderRepository.findAllOrders();
    }

    public Order saveOrder(@NotNull Order order) {
        return orderRepository.saveOrder(order);
    }

    public Order updateOrder(Long id, Order order) {
        return orderRepository.updateOrder(id, order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteOrder(id);
    }

    public void addProductToOrder(Product product) {
        long latestOrderId = orderRepository.getNextId(); // Get the latest order ID
        Order order = orderRepository.findOrder(latestOrderId);
        if (order != null) {
            order.getOrderProducts().add(product);
            orderRepository.updateOrder(latestOrderId, order);
        } else {
            order = new Order();
            orderRepository.saveOrder(order);
        }
    }

    public List<Product> getAllProductsInOrder() {
        List<Product> allProductsInOrder = new ArrayList<>();
        Collection<Order> allOrders = orderRepository.findAllOrders();

        for (Order order : allOrders) {
            List<Product> orderProducts = order.getOrderProducts();
            allProductsInOrder.addAll(orderProducts);
        }

        return allProductsInOrder;
    }
    public Product getProductById(Long productId) {
        Collection<Order> allOrders = orderRepository.findAllOrders();
        for (Order order : allOrders) {
            for (Product product : order.getOrderProducts()) {
                if (product.getId() == productId) {
                    return product;
                }
            }
        }
        return null; // Si no se encuentra el producto con el ID especificado
    }

}

