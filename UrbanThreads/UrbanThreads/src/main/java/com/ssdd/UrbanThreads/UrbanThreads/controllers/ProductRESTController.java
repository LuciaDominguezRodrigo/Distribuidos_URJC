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
import java.util.List;
import java.util.Objects;

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
    public ResponseEntity<ProductDTO> createProducto(@RequestBody Product product) {
        Product existingProduct = productService.findProductByName(product.getName());
        if (existingProduct != null) {
            return ResponseEntity.status(409).build();
        }


        Category category = product.getCategory();
        Collection<Category> categoriesAvailable = categoryService.getAllCategories();

        boolean categoryExists = false;
        for (Category c : categoriesAvailable) {
            if (c.getName().equals(category.getName()) & (c.getColor().equals(category.getColor())))  {
                categoryExists = true;
            }
        }

        if (!categoryExists) {
            return ResponseEntity.status(404).build(); // Category not found among available categories
        }
        Product newProduct = productService.saveProduct(product);
        if (newProduct.getCategory() == null) {
            return ResponseEntity.status(410).build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newProduct.getId())
                .toUri();

        ProductDTO productDTO = new ProductDTO(newProduct);

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
        Category category = product.getCategory();
        Collection<Category> categoriesAvailable = categoryService.getAllCategories();

        boolean categoryExists = false;
        for (Category c : categoriesAvailable) {
            if (c.getName().equals(category.getName()) & (c.getColor().equals(category.getColor())))  {
                categoryExists = true;
            }
        }

        if (!categoryExists) {
            return ResponseEntity.status(404).build(); // Category not found among available categories
        }
        Product newProduct = productService.saveProduct(product);
        if (newProduct.getCategory() == null) {
            return ResponseEntity.status(410).build();
        }

        existingProduct.setName(product.getName());
        existingProduct.setCategory(product.getCategory());
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

        if (partialProduct.getCategory() != null) {
            existingProduct.setCategory(partialProduct.getCategory());
        }

        Collection<Category> categoriesAvailable = categoryService.getAllCategories();

        Category category = partialProduct.getCategory();
        boolean categoryExists = false;
        for (Category c : categoriesAvailable) {
            if (c.getName().equals(category.getName()) & (c.getColor().equals(category.getColor())))  {
                categoryExists = true;
            }
        }

        if (!categoryExists) {
            return ResponseEntity.status(404).build(); // Category not found among available categories
        }
        Product newProduct = productService.saveProduct(partialProduct);
        if (newProduct.getCategory() == null) {
            return ResponseEntity.status(410).build();
        }

        Product updatedProduct = productService.saveProduct(existingProduct);
        ProductDTO productDTO = new ProductDTO(updatedProduct);
        return ResponseEntity.status(200).body(productDTO);
    }
}
