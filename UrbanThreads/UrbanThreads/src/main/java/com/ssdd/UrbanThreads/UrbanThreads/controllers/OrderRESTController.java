package com.ssdd.UrbanThreads.UrbanThreads.controllers;

import com.ssdd.UrbanThreads.UrbanThreads.DTO.CategoryDTO;
import com.ssdd.UrbanThreads.UrbanThreads.DTO.OrderDTO;
import com.ssdd.UrbanThreads.UrbanThreads.DTO.OrderedProductDTO;
import com.ssdd.UrbanThreads.UrbanThreads.DTO.ProductDTO;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Category;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Order;
import com.ssdd.UrbanThreads.UrbanThreads.entities.OrderStatus;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderRESTController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> obtenerPedido(@PathVariable int id) {
        Order selectedOrder = orderService.getOrderById(id);
        if (selectedOrder == null) {
            return ResponseEntity.status(404).build();
        }

        OrderDTO OrderDTO = new OrderDTO(selectedOrder);
        return ResponseEntity.status(200).body(OrderDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<OrderDTO>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        List<OrderDTO> oDTO = new ArrayList<>();
        if (orders == null) {
            return ResponseEntity.notFound().build();
        }
        for (Order o: orders){
            OrderDTO orderDTO = new OrderDTO(o);
            oDTO.add(orderDTO);
        }
        return ResponseEntity.status(200).body(oDTO);
    }

    @PostMapping("/new")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order newOrder = new Order();
        newOrder.setOrderStatus(OrderStatus.PENDING);
        List<Product> orderProducts = new ArrayList<>();
        for (OrderedProductDTO o : orderDTO.getOrderedProductsDTO()) {
            Product p = new Product();
            p.setName(o.getName());
            p.setPrice(o.getPrice());
            p.setSize(o.getSize());
            p.setColor(o.getColor());
            p.setQuantity(o.getQuantity());
            orderProducts.add(p);
        }
        newOrder.setOrderProducts(orderProducts);

        int orderId = orderService.addNewOrder(newOrder);
        orderService.changeCurrentOrder(newOrder.getOrderId());

        orderDTO.setOrderId(orderId);
        return ResponseEntity.status(201).body(orderDTO);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable int id, @RequestBody OrderDTO orderDTO) {
        Order existingOrder = orderService.getOrderById(id);

        if (existingOrder == null) {
            return ResponseEntity.notFound().build();
        }

        if (orderDTO.getOrderStatus() != null) {
            existingOrder.setOrderStatus(orderDTO.getOrderStatus());
        }

        if (orderDTO.getOrderedProductsDTO() != null) {
            List<Product> orderProducts = new ArrayList<>();
            for (OrderedProductDTO o : orderDTO.getOrderedProductsDTO()) {
                Product p = new Product();
                p.setName(o.getName());
                p.setPrice(o.getPrice());
                p.setSize(o.getSize());
                p.setColor(o.getColor());
                p.setQuantity(o.getQuantity());
                orderProducts.add(p);
            }
            existingOrder.setOrderProducts(orderProducts);
        }

        orderService.saveOrder(id, existingOrder);
        return ResponseEntity.status(202).body(new OrderDTO(existingOrder));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        Order existingOrder = orderService.getOrderById(id);
        if (existingOrder == null) {
            return ResponseEntity.status(404).build();
        }

        Order removedOrder = orderService.deleteOrderById(id);
        if(removedOrder == null){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<OrderDTO> editOrderByPatching(@PathVariable int id, @RequestBody OrderDTO partialOrderDTO) {
        Order existingOrder = orderService.getOrderById(id);

        if (existingOrder == null) {
            return ResponseEntity.notFound().build();
        }

        if (partialOrderDTO.getOrderStatus() != null) {
            existingOrder.setOrderStatus(partialOrderDTO.getOrderStatus());
        }

        if (partialOrderDTO.getOrderedProductsDTO() != null) {
            List<Product> orderProducts = new ArrayList<>();
            for (OrderedProductDTO o : partialOrderDTO.getOrderedProductsDTO()) {
                Product p = new Product();
                p.setName(o.getName());
                p.setPrice(o.getPrice());
                p.setSize(o.getSize());
                p.setColor(o.getColor());
                p.setQuantity(o.getQuantity());
                orderProducts.add(p);
            }
            existingOrder.setOrderProducts(orderProducts);
        }

        orderService.saveOrder(id, existingOrder);
        return ResponseEntity.status(200).body(new OrderDTO(existingOrder));
    }
}
