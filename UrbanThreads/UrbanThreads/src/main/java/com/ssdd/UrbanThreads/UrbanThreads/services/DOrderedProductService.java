package com.ssdd.UrbanThreads.UrbanThreads.services;


import com.ssdd.UrbanThreads.UrbanThreads.entities.DOrder;
import com.ssdd.UrbanThreads.UrbanThreads.entities.DOrderedProduct;
import com.ssdd.UrbanThreads.UrbanThreads.entities.DProduct;
import com.ssdd.UrbanThreads.UrbanThreads.repository.DOrderRepository;
import com.ssdd.UrbanThreads.UrbanThreads.repository.DOrderedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class DOrderedProductService {

    @Autowired
    private DOrderService orderService;

    @Autowired
    private DOrderRepository orderRepository;

    @Autowired
    private DOrderedProductRepository orderedProductRepository;

    public void saveOrderedProduct(DOrderedProduct orderedProduct) {
        orderedProductRepository.save(orderedProduct);
    }

    //Currently implemented in OrderService
    public void addProductToCurrentOrder(int id, DProduct product, String size, String color, int quantity) {
        DOrder currentOrder = orderRepository.findByOrderId(orderService.getSelectedOrder());

        DOrderedProduct newProduct = new DOrderedProduct();
        newProduct.setId(id);
        newProduct.setName(product.getName());
        newProduct.setTotalPrice(product.getPrice() * quantity);
        newProduct.setSize(size);
        newProduct.setColor(color);
        newProduct.setQuantity(quantity);

        orderedProductRepository.save(newProduct);
    }

    //Currently implemented in OrderService
    public void deleteOrderedProduct(int orderId, int productId) {
        DOrder currentOrder = orderRepository.findByOrderId(orderId);

        if (currentOrder != null) {
            currentOrder.getOrderedProducts().removeIf(product -> product.getId() == productId);
        }
    }
}
