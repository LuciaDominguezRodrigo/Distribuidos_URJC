package com.ssdd.UrbanThreads.UrbanThreads.controllers;

import com.ssdd.UrbanThreads.UrbanThreads.DTO.OrderDTO;
import com.ssdd.UrbanThreads.UrbanThreads.DTO.OrderedProductDTO;
import com.ssdd.UrbanThreads.UrbanThreads.entities.*;
import com.ssdd.UrbanThreads.UrbanThreads.services.OrderService;
import com.ssdd.UrbanThreads.UrbanThreads.services.OrderedProductService;
import com.ssdd.UrbanThreads.UrbanThreads.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderRESTController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderedProductService orderedProductService;

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        Order selectedOrder = orderService.getOrderById(id);
        if (selectedOrder == null) {
            return ResponseEntity.status(404).build();
        }

        OrderDTO oDTO = new OrderDTO(selectedOrder);
        return ResponseEntity.status(200).body(oDTO);
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
        orderService.saveOrder(newOrder);
        for (OrderedProductDTO o : orderDTO.getOrderedProductsDTO()) {
            OrderedProduct p = new OrderedProduct();
            p.setOrder(newOrder);
            Optional<Product> productOptional = productService.findProduct(o.getProductId());
            if(!productOptional.isPresent()){
                return ResponseEntity.status(404).build();
            }
            p.setProduct(productOptional.get());
            p.setName(o.getName());
            p.setSize(Size.valueOf(o.getSize()));
            p.setColor(o.getColor());
            p.setQuantity(o.getQuantity());
            p.setTotalPrice(o.getTotalPrice());
            orderedProductService.addProductToOrder(p.getOrder(), p.getProduct(), p.getSize(), p.getColor(), p.getQuantity());
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
        productService.reduceProductsQuantity(existingOrder.getOrderedProducts());
        orderService.saveOrder(existingOrder);

        //When an order is removed, the current order changes to next created order or, if there´s no more orders created, a new one is created and marked as current order.
        List<Long> allOrdersId = orderService.getAllPendingOrdersId();
        if(!allOrdersId.isEmpty()){
            orderService.changeCurrentOrder(allOrdersId.get(0));
        } else{
            Long newOrderId = orderService.addNewOrder(new Order());
            orderService.changeCurrentOrder(newOrderId);
        }

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
        } else{
            existingOrder.setOrderStatus(OrderStatus.PENDING);
        }

        if (orderDTO.getOrderedProductsDTO() != null) {
            for (OrderedProductDTO o : orderDTO.getOrderedProductsDTO()) {
                OrderedProduct p = new OrderedProduct();
                p.setOrder(existingOrder);
                Optional<Product> productOptional = productService.findProduct(o.getProductId());
                if(!productOptional.isPresent()){
                    return ResponseEntity.status(404).build();
                }
                p.setProduct(productOptional.get());
                p.setName(o.getName());
                p.setSize(Size.valueOf(o.getSize()));
                p.setColor(o.getColor());
                p.setQuantity(o.getQuantity());
                p.setTotalPrice(o.getTotalPrice());
                orderedProductService.addProductToOrder(p.getOrder(), p.getProduct(), p.getSize(), p.getColor(), p.getQuantity());
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
        orderService.deleteOrderById(id);

        //When an order is removed, the current order changes to next created order or, if there´s no more orders created, a new one is created and marked as current order.
        List<Long> allOrdersId = orderService.getAllPendingOrdersId();
        if(!allOrdersId.isEmpty()){
            orderService.changeCurrentOrder(allOrdersId.get(0));
        } else{
            Long newOrderId = orderService.addNewOrder(new Order());
            orderService.changeCurrentOrder(newOrderId);
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
        } else{
            existingOrder.setOrderStatus(OrderStatus.PENDING);
        }

        if (partialOrderDTO.getOrderedProductsDTO() != null) {
            for (OrderedProductDTO o : partialOrderDTO.getOrderedProductsDTO()) {
                OrderedProduct p = new OrderedProduct();
                p.setOrder(existingOrder);
                Optional<Product> productOptional = productService.findProduct(o.getProductId());
                if(!productOptional.isPresent()){
                    return ResponseEntity.status(404).build();
                }
                p.setProduct(productOptional.get());
                p.setName(o.getName());
                p.setSize(Size.valueOf(o.getSize()));
                p.setColor(o.getColor());
                p.setQuantity(o.getQuantity());
                p.setTotalPrice(o.getTotalPrice());
                orderedProductService.addProductToOrder(p.getOrder(), p.getProduct(), p.getSize(), p.getColor(), p.getQuantity());
            }
        }
        orderService.saveOrder(existingOrder);
        return ResponseEntity.status(200).body(new OrderDTO(existingOrder));
    }
}
