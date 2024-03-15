package com.ssdd.UrbanThreads.UrbanThreads.controllers;

import com.ssdd.UrbanThreads.UrbanThreads.DTO.CategoryDTO;
import com.ssdd.UrbanThreads.UrbanThreads.DTO.ProductDTO;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Category;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.services.CategoryService;
import com.ssdd.UrbanThreads.UrbanThreads.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

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

        ProductDTO productDTO = new ProductDTO(product);
        return ResponseEntity.status(200).body(productDTO);
    }
    @GetMapping("/all")
    public ResponseEntity<Collection<ProductDTO>> getAll(){
        Collection<Product> products = productService.findAllProducts();
        Collection<ProductDTO> cDTO = new ArrayList<>();
        if (products== null) {
            return ResponseEntity.notFound().build();
        }
        for (Product c: products){
            ProductDTO pDTO = new ProductDTO(c);
            assert false;
            cDTO.add(pDTO);

        }
        return ResponseEntity.status(200).body(cDTO);
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
        return ResponseEntity.status(201).location(location).body(productDTO);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        Product product = productService.findProduct(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productService.deleteProduct(id);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<ProductDTO> editP(@PathVariable int id, @RequestBody Product product) {
        Product existingProduct = productService.findProduct(id);
        if (existingProduct == null) {
            return ResponseEntity.notFound().build();
        }

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());

        Product updatedProduct = productService.saveProduct(existingProduct);
        ProductDTO productDTO = new ProductDTO(updatedProduct);
        return ResponseEntity.status(200).body(productDTO);
    }

    @PatchMapping("editP/{id}")
    public ResponseEntity<ProductDTO> editP2(@PathVariable int id, @RequestBody Product partialProduct) {
        Product existingProduct = productService.findProduct(id);
        if (existingProduct == null) {
            return ResponseEntity.notFound().build();
        }

        if (partialProduct.getName() != null) {
            existingProduct.setName(partialProduct.getName());
        }
        if (partialProduct.getPrice() != 0.0) {
            existingProduct.setPrice(partialProduct.getPrice());
        }
        if (partialProduct.getDescription() != null) {
            existingProduct.setDescription(partialProduct.getDescription());
        }

        Product updatedProduct = productService.saveProduct(existingProduct);
        ProductDTO productDTO = new ProductDTO(updatedProduct);
        return ResponseEntity.status(200).body(productDTO);
    }
}
