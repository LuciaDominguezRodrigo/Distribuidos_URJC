package com.ssdd.UrbanThreads.UrbanThreads.services;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Order;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.repository.OrderRepository;
import com.ssdd.UrbanThreads.UrbanThreads.repository.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    public Order getCurrentOrder() {
        return orderRepository.getCurrentOrder();
    }

    public Order getOrderById(int id) { return orderRepository.getOrderById(id); }

    public List<Order> getAllOrders(){
        return orderRepository.getAllOrders();
    }
    public void changeCurrentOrder(int orderId){
        orderRepository.setSelectedOrderId(orderId);
    }

    public List<Integer> getAllOrdersId() {
        Set<Integer> aux = orderRepository.getAllOrdersId();
        List<Integer> allOrdersId = new ArrayList<>();
        allOrdersId.addAll(aux);
        return allOrdersId;
    }

    public int addNewOrder (Order o){
        return orderRepository.addNewOrder(o);
    }

    public void addProductToCurrentOrder(int id, Product product, String size, String color, int quantity) {
        Order currentOrder = orderRepository.findOrder(orderRepository.getSelectedOrderId());

        if (currentOrder == null) {
            currentOrder = new Order();
            currentOrder.setOrderProducts(new ArrayList<>());
            orderRepository.saveCurrentOrder(currentOrder);
        }

        Product newProduct = new Product();
        newProduct.setId(id);
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setSize(size);
        newProduct.setColor(color);
        newProduct.setQuantity(quantity);

        currentOrder.getOrderProducts().add(newProduct);

        orderRepository.saveCurrentOrder(currentOrder);
    }

    public void saveCurrentOrder(Order currentOrder){
        orderRepository.saveCurrentOrder(currentOrder);
    }

    public Order saveOrder(int id, @NotNull Order order) {
        return orderRepository.saveOrder(id, order);
    }

    public void deleteCurrentOrder(){
        orderRepository.deleteCurrentOrder();
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
    }

    public Order deleteOrderById(int id){
        return orderRepository.deleteOrderById(id);
    }

    public Product getDeletedProduct(int productId) {
        Product optionalProduct = productRepository.findProduct(productId);
        return optionalProduct;
    }

    /*
    public Order findOrder(int id) {
        return orderRepository.findOrder(id);
    }

    public Collection<Order> findAllOrders() {
        return orderRepository.findAllOrders();
    }

    public Order updateOrder(int id, Order order) {
        return orderRepository.updateOrder(id, order);
    }

    public void deleteOrderT( ) {
        Order order = orderRepository.findOrder(orderRepository.getSelectedOrder());
        orderRepository.deleteOrder(order.getOrderId());
    }

    public void addProductToCurrentOrder(Product product) {
        Order order = orderRepository.findOrder(orderRepository.getSelectedOrder());
        if (order != null) {
            order.getOrderProducts().add(product);
            orderRepository.updateOrder(orderRepository.getSelectedOrder(), order);
        } else {
            order = new Order();
            order.getOrderProducts().add(product);
            orderRepository.saveOrder(order, orderRepository.getSelectedOrder());
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

    public Product findProductInOrder(Order order, int productId) {
        return order.getOrderProducts().stream()
                .filter(product -> product.getId() == productId)
                .findFirst()
                .orElse(null);
    }


    public int deleteProductFromCurrentOrder(int productId) {
        Order order = orderRepository.findOrder(orderRepository.getSelectedOrder());
        if (order != null) {

            for (Product orderProduct : order.getOrderProducts()) {
                if (orderProduct.getId() == productId) {
                    order.getOrderProducts().remove(orderProduct);
                }
            }
            orderRepository.updateOrder(orderRepository.getSelectedOrder(), order);
        }
        return productId;
    }
    */
}

