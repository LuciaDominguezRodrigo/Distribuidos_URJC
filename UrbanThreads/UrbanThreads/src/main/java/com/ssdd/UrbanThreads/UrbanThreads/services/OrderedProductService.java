package com.ssdd.UrbanThreads.UrbanThreads.services;


import com.ssdd.UrbanThreads.UrbanThreads.entities.Order;
import com.ssdd.UrbanThreads.UrbanThreads.entities.OrderedProduct;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.repository.OrderRepository;
import com.ssdd.UrbanThreads.UrbanThreads.repository.OrderedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderedProductService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderedProductRepository orderedProductRepository;

    public void saveOrderedProduct(OrderedProduct orderedProduct) {
        orderedProductRepository.save(orderedProduct);
    }

    //Currently implemented in OrderService
    public void addProductToCurrentOrder(int id, Product product, String size, String color, int quantity) {
        Order currentOrder = orderRepository.findByOrderId(orderService.getSelectedOrder());

        OrderedProduct newProduct = new OrderedProduct();
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
        Order currentOrder = orderRepository.findByOrderId(orderId);

        if (currentOrder != null) {
            currentOrder.getOrderedProducts().removeIf(product -> product.getId() == productId);
        }
    }
}
