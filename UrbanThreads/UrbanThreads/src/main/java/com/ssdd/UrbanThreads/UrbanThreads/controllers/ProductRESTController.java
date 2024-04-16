package com.ssdd.UrbanThreads.UrbanThreads.controllers;

import com.ssdd.UrbanThreads.UrbanThreads.DTO.CategoryDTO;
import com.ssdd.UrbanThreads.UrbanThreads.DTO.ProductDTO;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Category;
import com.ssdd.UrbanThreads.UrbanThreads.entities.DCategory;
import com.ssdd.UrbanThreads.UrbanThreads.entities.DProduct;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.services.CategoryService;
import com.ssdd.UrbanThreads.UrbanThreads.services.DCategoryService;
import com.ssdd.UrbanThreads.UrbanThreads.services.DProductService;
import com.ssdd.UrbanThreads.UrbanThreads.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping ("/api/products")
public class ProductRESTController {
    @Autowired
    private DProductService productService;

    @Autowired
    private DCategoryService categoryService;



    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> obtenerProducto(@PathVariable int id) {
        Optional<DProduct> productOptional = productService.findProduct(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        DProduct product = productOptional.get();
        ProductDTO productDTO = new ProductDTO(product);
        return ResponseEntity.status(200).body(productDTO);
    }
    @GetMapping("/all")
    public ResponseEntity<Collection<ProductDTO>> getAll(){
        Collection<DProduct> products = productService.findAllProducts();
        Collection<ProductDTO> cDTO = new ArrayList<>();
        if (products== null) {
            return ResponseEntity.notFound().build();
        }
        for (DProduct c: products){
            ProductDTO pDTO = new ProductDTO(c);
            assert false;
            cDTO.add(pDTO);

        }
        return ResponseEntity.status(200).body(cDTO);
    }

    @PostMapping("/new")
    public ResponseEntity<ProductDTO> createProducto(@RequestBody DProduct product) {
        Optional<DProduct> existingProductOptional = productService.findProduct(product.getId());
        if (existingProductOptional.isPresent()) {
            return ResponseEntity.status(409).build(); // Conflict
        }

        DCategory category = product.getCategory();
        Collection<DCategory> categoriesAvailable = categoryService.getAllCategories();

        boolean categoryExists = false;
        for (DCategory c : categoriesAvailable) {
            if (c.getName().equals(category.getName()) && c.getColor().equals(category.getColor())) {
                categoryExists = true;
            }
        }

        if (!categoryExists) {
            return ResponseEntity.status(404).build(); // Category not found among available categories
        }

        DProduct newProduct = productService.saveProduct(product);
        if (newProduct == null || newProduct.getCategory() == null) {
            return ResponseEntity.status(410).build(); // Gone
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newProduct.getId())
                .toUri();

        ProductDTO productDTO = new ProductDTO(newProduct);

        return ResponseEntity.status(201).location(location).body(productDTO); // Created
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        Optional<DProduct> productOptional = productService.findProduct(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        productService.deleteProduct(id);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<ProductDTO> editP(@PathVariable int id, @RequestBody DProduct product) {
        Optional<DProduct> existingProductOptional = productService.findProduct(id);
        if (existingProductOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        DProduct existingProduct = existingProductOptional.get();

        DCategory category = product.getCategory();
        Collection<DCategory> categoriesAvailable = categoryService.getAllCategories();

        boolean categoryExists = false;
        for (DCategory c : categoriesAvailable) {
            if (c.getName().equals(category.getName()) && c.getColor().equals(category.getColor())) {
                categoryExists = true;

            }
        }

        if (!categoryExists) {
            return ResponseEntity.status(404).build(); // Category not found among available categories
        }

        existingProduct.setName(product.getName());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());

        DProduct updatedProduct = productService.saveProduct(existingProduct);
        ProductDTO productDTO = new ProductDTO(updatedProduct);
        return ResponseEntity.status(200).body(productDTO);
    }
    @PatchMapping("editP/{id}")
    public ResponseEntity<ProductDTO> editP2(@PathVariable int id, @RequestBody DProduct partialProduct) {
        Optional<DProduct> existingProductOptional = productService.findProduct(id);
        if (existingProductOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        DProduct existingProduct = existingProductOptional.get();

        if (partialProduct.getName() != null) {
            existingProduct.setName(partialProduct.getName());
        }
        if (partialProduct.getPrice() != 0.0) {
            existingProduct.setPrice(partialProduct.getPrice());
        }
        if (partialProduct.getDescription() != null) {
            existingProduct.setDescription(partialProduct.getDescription());
        }

        if (partialProduct.getCategory() != null) {
            existingProduct.setCategory(partialProduct.getCategory());
        }

        Collection<DCategory> categoriesAvailable = categoryService.getAllCategories();

        DCategory category = partialProduct.getCategory();
        boolean categoryExists = false;
        for (DCategory c : categoriesAvailable) {
            if (c.getName().equals(category.getName()) && c.getColor().equals(category.getColor()))  {
                categoryExists = true;
            }
        }

        if (!categoryExists) {
            return ResponseEntity.status(404).build(); // Category not found among available categories
        }

        DProduct updatedProduct = productService.saveProduct(existingProduct);
        ProductDTO productDTO = new ProductDTO(updatedProduct);
        return ResponseEntity.status(200).body(productDTO);
    }
}
