package com.ssdd.UrbanThreads.UrbanThreads.controllers;


import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/index")
    public String getAllProducts(Model model) {
        Collection<Product> products = productService.getAllProducts();

        model.addAttribute("productos", products);

        return "index";
    }
}
