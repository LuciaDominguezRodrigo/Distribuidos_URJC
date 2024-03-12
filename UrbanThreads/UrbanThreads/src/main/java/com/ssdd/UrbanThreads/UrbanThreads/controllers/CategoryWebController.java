package com.ssdd.UrbanThreads.UrbanThreads.controllers;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CategoryWebController{

    @Autowired
    ProductService productService;
@GetMapping("/productosPorCategoria")
public String productosPorCategoria(Model model, @RequestParam("categoria") String categoria) {
    List<Product> productsByCategory = productService.findProductsByCategory(categoria);
    model.addAttribute("products", productsByCategory);
    return "index";
   }
}
