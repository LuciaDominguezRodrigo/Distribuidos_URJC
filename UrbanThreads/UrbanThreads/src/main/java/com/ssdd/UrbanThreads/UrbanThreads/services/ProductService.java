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

            String photo =  "/img/camiseta.jpg";
            String photo2 =  "/img/pantalon.jpg";
            String photo3 =  "/img/calcetines.jpg";
            String photo4 =  "/img/abrigo.jpg";
            String photo5 =  "/img/chaqueta.jpg";
            String photo6 =  "/img/sudadera.jpg";


            // Agregar productos con sus fotos al mapa
            createProduct(new Product(1L, "Camiseta", null, sizes, 10.0, photo, "Descripción del producto 1"));
            createProduct(new Product(2L, "Pantalon ancho", null, sizes, 20.0, photo2, "Descripción del producto 2"));
            createProduct(new Product(3L, "Calcetines", null, sizes, 15.0, photo3, "Descripción del producto 3"));
            createProduct(new Product(4L, "Abrigo", null, sizes, 25.0, photo4, "Descripción del producto 4"));
            createProduct(new Product(5L, "Chaqueta", null, sizes, 12.0, photo5, "Descripción del producto 5"));
            createProduct(new Product(6L, "Sudadera", null, sizes, 18.0, photo6, "Descripción del producto 6"));


    }
}


