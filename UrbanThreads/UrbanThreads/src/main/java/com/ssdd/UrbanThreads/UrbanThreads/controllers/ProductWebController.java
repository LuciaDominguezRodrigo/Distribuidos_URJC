package com.ssdd.UrbanThreads.UrbanThreads.controllers;


import com.ssdd.UrbanThreads.UrbanThreads.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ProductWebController {

    @Autowired
    private ProductService productService;


    @GetMapping("/")
    public String index(Model model) {
        // Obtener todos los productos de la base de datos
        model.addAttribute("products", productService.findAllProducts());
        return "index";
    }

}
