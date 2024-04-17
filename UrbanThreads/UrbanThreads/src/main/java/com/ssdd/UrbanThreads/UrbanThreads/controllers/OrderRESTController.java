package com.ssdd.UrbanThreads.UrbanThreads.controllers;

import com.ssdd.UrbanThreads.UrbanThreads.DTO.OrderDTO;
import com.ssdd.UrbanThreads.UrbanThreads.DTO.OrderedProductDTO;
import com.ssdd.UrbanThreads.UrbanThreads.entities.*;
import com.ssdd.UrbanThreads.UrbanThreads.services.OrderService;
import com.ssdd.UrbanThreads.UrbanThreads.services.OrderedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderRESTController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderedProductService orderedProductService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
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
        for (OrderedProductDTO o : orderDTO.getOrderedProductsDTO()) {
            OrderedProduct p = new OrderedProduct();
            p.setName(o.getName());
            p.setTotalPrice(o.getPrice());
            p.setSize(Size.valueOf(o.getSize()));
            p.setColor(o.getColor());
            p.setQuantity(o.getQuantity());
            orderedProductService.saveOrderedProduct(p);
        }

        long orderId = orderService.addNewOrder(newOrder);
        orderService.changeCurrentOrder(newOrder.getId());
        orderDTO.setOrderId(orderId);
        return ResponseEntity.status(201).body(orderDTO);
    }

    @PostMapping("/complete/{id}")
    public ResponseEntity<OrderDTO> completeOrder(@PathVariable Long id) {
        Order existingOrder = orderService.getOrderById(id);

        if (existingOrder == null) {
            return ResponseEntity.notFound().build();
        }

        existingOrder.setOrderStatus(OrderStatus.COMPLETED);
        orderService.saveOrder(existingOrder);

        return ResponseEntity.status(200).body(new OrderDTO(existingOrder));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        Order existingOrder = orderService.getOrderById(id);

        if (existingOrder == null) {
            return ResponseEntity.notFound().build();
        }

        if (orderDTO.getOrderStatus() != null) {
            existingOrder.setOrderStatus(orderDTO.getOrderStatus());
        }

        if (orderDTO.getOrderedProductsDTO() != null) {
            for (OrderedProductDTO o : orderDTO.getOrderedProductsDTO()) {
                OrderedProduct p = new OrderedProduct();
                p.setName(o.getName());
                p.setTotalPrice(o.getPrice());
                p.setSize(Size.valueOf(o.getSize()));
                p.setColor(o.getColor());
                p.setQuantity(o.getQuantity());
                orderedProductService.saveOrderedProduct(p);
            }
        }
        orderService.saveOrder(existingOrder);
        return ResponseEntity.status(202).body(new OrderDTO(existingOrder));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id) {
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
    public ResponseEntity<OrderDTO> editOrderByPatching(@PathVariable Long id, @RequestBody OrderDTO partialOrderDTO) {
        Order existingOrder = orderService.getOrderById(id);

        if (existingOrder == null) {
            return ResponseEntity.notFound().build();
        }

        if (partialOrderDTO.getOrderStatus() != null) {
            existingOrder.setOrderStatus(partialOrderDTO.getOrderStatus());
        }

        if (partialOrderDTO.getOrderedProductsDTO() != null) {
            for (OrderedProductDTO o : partialOrderDTO.getOrderedProductsDTO()) {
                OrderedProduct p = new OrderedProduct();
                p.setName(o.getName());
                p.setTotalPrice(o.getPrice());
                p.setSize(Size.valueOf(o.getSize()));
                p.setColor(o.getColor());
                p.setQuantity(o.getQuantity());
                orderedProductService.saveOrderedProduct(p);
            }
        }
        orderService.saveOrder(existingOrder);
        return ResponseEntity.status(200).body(new OrderDTO(existingOrder));
    }
}
