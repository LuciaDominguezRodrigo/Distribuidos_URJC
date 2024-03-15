package com.ssdd.UrbanThreads.UrbanThreads.controllers;

import com.ssdd.UrbanThreads.UrbanThreads.DTO.CategoryDTO;
import com.ssdd.UrbanThreads.UrbanThreads.DTO.OrderDTO;
import com.ssdd.UrbanThreads.UrbanThreads.DTO.OrderedProductDTO;
import com.ssdd.UrbanThreads.UrbanThreads.DTO.ProductDTO;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Category;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Order;
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
}
