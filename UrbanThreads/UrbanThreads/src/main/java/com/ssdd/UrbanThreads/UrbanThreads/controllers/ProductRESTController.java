package com.ssdd.UrbanThreads.UrbanThreads.controllers;

import com.ssdd.UrbanThreads.UrbanThreads.DTO.ProductDTO;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.services.CategoryService;
import com.ssdd.UrbanThreads.UrbanThreads.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api")
public class ProductRESTController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;




    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> obtenerProducto(@PathVariable int id) {
        Product producto = productService.findProduct(id);
        if (producto == null) {
            return ResponseEntity.status(404).build();
        }

        ProductDTO productoDTO = new ProductDTO(producto);
        return ResponseEntity.ok(productoDTO);
    }


}
