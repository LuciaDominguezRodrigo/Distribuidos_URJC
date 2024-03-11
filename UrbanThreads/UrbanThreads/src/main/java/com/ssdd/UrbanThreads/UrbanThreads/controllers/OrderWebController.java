package com.ssdd.UrbanThreads.UrbanThreads.controllers;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderWebController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public String order(){
        return "orderPage";
    }

    @PostMapping("/newProductInOrder")
    public String addToOrder(@RequestParam("id") Long productId, Model model) {
        Product product = orderService.getProductById(productId);
        List<Product> lista = new ArrayList<>();
        lista.add(product);
        orderService.addProductToOrder(product);
        model.addAttribute("productos", lista);
        return "orderPage";
    }
}

