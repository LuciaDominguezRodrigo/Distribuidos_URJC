package com.ssdd.UrbanThreads.UrbanThreads.controllers;


import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.sql.Blob;


@Controller
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/")
    public String index(Model model) {
        // Obtener todos los productos de la base de datos
        model.addAttribute("productos", productService.getAllProducts());
        return "index";
    }

}
