package com.ssdd.UrbanThreads.UrbanThreads.controllers;

import com.ssdd.UrbanThreads.UrbanThreads.DTO.ProductDTO;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.services.CategoryService;
import com.ssdd.UrbanThreads.UrbanThreads.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping ("/api/products")
public class ProductRESTController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;




    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> obtenerProducto(@PathVariable int id) {
        Product product = productService.findProduct(id);
        if (product == null) {
            return ResponseEntity.status(404).build();
        }

        ProductDTO productoDTO = new ProductDTO(product);
        return ResponseEntity.ok(productoDTO);
    }

    @PostMapping("/new")
    public ResponseEntity<ProductDTO> crearProducto(@RequestBody Product product) {
        Product existingProduct = productService.findProductByName(product.getName());
        if (existingProduct != null) {
            return ResponseEntity.status(409).build();
        }

        Product nuevoProducto = productService.saveProduct(product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevoProducto.getId())
                .toUri();

        ProductDTO productDTO = new ProductDTO(nuevoProducto);

        return ResponseEntity.created(location).body(productDTO);
    }
}
