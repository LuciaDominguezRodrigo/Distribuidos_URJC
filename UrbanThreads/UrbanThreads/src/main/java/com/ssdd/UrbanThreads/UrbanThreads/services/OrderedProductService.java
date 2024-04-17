package com.ssdd.UrbanThreads.UrbanThreads.services;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Order;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class OrderedProductService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;


    /*public void addProductToCurrentOrder(int id, Product product, String size, String color, int quantity) {
        Order currentOrder = orderRepository.findByOrderId(orderService.getSelectedOrder());

        if (currentOrder == null) {
            currentOrder = new Order();
        }

        Product newProduct = new Product();
        newProduct.setId(id);
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setSize(size);
        newProduct.setColor(color);
        newProduct.setQuantity(quantity);

        currentOrder.getOrderProducts().add(newProduct);

    }

    public void deleteOrderedProduct(int orderId, int productId) {
        Order currentOrder = orderRepository.findOrder(orderId);

        if (currentOrder != null) {
            boolean productoEncontrado = false;
            Iterator<Product> iterator = currentOrder.getOrderProducts().iterator();
            while (iterator.hasNext()) {
                Product product = iterator.next();
                if (product.getId() == productId) {
                    iterator.remove();
                    productoEncontrado = true;
                }
            }

            if (productoEncontrado) {
                orderRepository.saveCurrentOrder(currentOrder);
            }
        }
    }*/
}
