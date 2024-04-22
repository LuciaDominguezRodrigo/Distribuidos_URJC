package com.ssdd.UrbanThreads.UrbanThreads.controllers;

import com.ssdd.UrbanThreads.UrbanThreads.DTO.ProductDTO;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Category;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.services.CategoryService;
import com.ssdd.UrbanThreads.UrbanThreads.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping ("/api/products")
public class ProductRESTController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> obtenerProducto(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findProduct(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        Product product = productOptional.get();
        ProductDTO productDTO = new ProductDTO(product);
        return ResponseEntity.status(200).body(productDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<ProductDTO>> getAll() {
        Collection<Product> products = productService.findAllProducts();
        Collection<ProductDTO> cDTO = new ArrayList<>();
        if (products == null) {
            return ResponseEntity.notFound().build();
        }
        for (Product c : products) {
            ProductDTO pDTO = new ProductDTO(c);
            assert false;
            cDTO.add(pDTO);

        }
        return ResponseEntity.status(200).body(cDTO);
    }

    @PostMapping("/new")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product requestDTO) throws SQLException, IOException {
        // Obtener la categoría
        Optional<Category> category = categoryService.findCategory(requestDTO.getCategory().getId());
        // Crear un nuevo producto
        Product product = new Product();
        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());
        product.setCategory(category.get());
        product.setDescription(requestDTO.getDescription());
        product.setAvailableSizes(requestDTO.getAvailableSizes());

        // Establecer la foto por defecto
        product.setPhoto();

        // Guardar el producto
        Product savedProduct = productService.saveProduct(product);

        // Construir la URL de la ubicación del nuevo producto
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();

        // Crear el DTO del producto
        ProductDTO productDTO = new ProductDTO(savedProduct);

        // Devolver una respuesta con el DTO del producto y la URL de la ubicación
        return ResponseEntity.created(location).body(productDTO);
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findProduct(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        productService.deleteProduct(id);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/img/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> showProductImage(@PathVariable Long id) {
        Optional<Product> userOptional = productService.findProduct(id);
        if (userOptional.isPresent()) {
            Product user = userOptional.get();

            try {
                Blob photoBlob = user.getPhoto();
                byte[] photoBytes = photoBlob.getBytes(1, (int) photoBlob.length());

                return ResponseEntity
                        .ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(photoBytes);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<ProductDTO> editP(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> existingProductOptional = productService.findProduct(id);
        if (existingProductOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Product existingProduct = existingProductOptional.get();

        Category category = product.getCategory();
        Category existingCategory = categoryService.findCategoryByName(category.getName());

        if (existingCategory == null) {
            return ResponseEntity.status(404).build(); // Category not found among available categories
        }

        existingProduct.setName(product.getName());
        existingProduct.setCategory(existingCategory);
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());

        if(product.getAvailableSizes() != null) {
            existingProduct.setAvailableSizes(product.getAvailableSizes());
        }


        Product updatedProduct = productService.saveProduct(existingProduct);
        ProductDTO productDTO = new ProductDTO(updatedProduct);
        return ResponseEntity.status(200).body(productDTO);
    }


    @PostMapping("/{productId}/photo")
    public ResponseEntity<ProductDTO> uploadEventPhoto(@PathVariable Long productId, @RequestPart("photo") MultipartFile photo) {
        Optional<Product> product = productService.findProduct(productId);
        if (!product.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Product product1 = product.get();


        try {
            product1.st(new javax.sql.rowset.serial.SerialBlob(photo.getBytes()));
            productService.saveProduct(product1);
            ProductDTO eventDTO =new ProductDTO(product1);

            // Build the URL created event
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/products/{id}")
                    .buildAndExpand(product1.getId())
                    .toUri();

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(location);

            return new ResponseEntity<>(eventDTO, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PatchMapping("editP/{id}")
    public ResponseEntity<ProductDTO> editP2(@PathVariable Long id, @RequestBody Product partialProduct) {
        Optional<Product> existingProductOptional = productService.findProduct(id);
        if (existingProductOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Product existingProduct = existingProductOptional.get();

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
            Category category = categoryService.findCategoryByName(partialProduct.getCategory().getName());

            if (category == null) {
                return ResponseEntity.status(404).build(); // Category not found among available categories
            }
            existingProduct.setCategory(category);
        }

        if(partialProduct.getAvailableSizes() != null) {
            existingProduct.setAvailableSizes(partialProduct.getAvailableSizes());
        }

        Product updatedProduct = productService.saveProduct(existingProduct);
        ProductDTO productDTO = new ProductDTO(updatedProduct);
        return ResponseEntity.status(200).body(productDTO);
    }
}
