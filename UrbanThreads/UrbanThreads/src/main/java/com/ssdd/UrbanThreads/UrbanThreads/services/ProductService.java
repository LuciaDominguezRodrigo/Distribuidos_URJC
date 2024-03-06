package com.ssdd.UrbanThreads.UrbanThreads.services;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import jakarta.annotation.PostConstruct;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;


import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {
    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong();


    public Product getProduct (Long id){
        return products.get(id);
    }

    public Product createProduct(@NotNull Product product){
        long id = nextId.incrementAndGet();
        product.setId(id);
        products.put(id,product);
        return product;

    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public Product updateProduct (Long id, Product product){
            if (!products.containsKey(id)) {
                return null;
            }
            product.setId(id);
            products.put(id, product);
            return product;
        }


    public void deleteProduct(Long id) {
        products.remove(id);
    }


   @PostConstruct
    public void init() {
        // Agregar fotos al mapa

            Set<String> sizes = new HashSet<>();
            sizes.add("S");
            sizes.add("M");
            sizes.add("L");

            String photo =  "/img/gato.jpg";

            // Agregar productos con sus fotos al mapa
            createProduct(new Product(1L, "Producto 1", null, sizes, 10.0, photo, "Descripción del producto 1"));
            createProduct(new Product(2L, "Producto 2", null, sizes, 20.0, photo, "Descripción del producto 2"));
            createProduct(new Product(3L, "Producto 3", null, sizes, 15.0, photo, "Descripción del producto 3"));
            createProduct(new Product(4L, "Producto 4", null, sizes, 25.0, photo, "Descripción del producto 4"));
            createProduct(new Product(5L, "Producto 5", null, sizes, 12.0, photo, "Descripción del producto 5"));
            createProduct(new Product(6L, "Producto 6", null, sizes, 18.0, photo, "Descripción del producto 6"));


    }
}


