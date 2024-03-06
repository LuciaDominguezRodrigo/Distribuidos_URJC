package com.ssdd.UrbanThreads.UrbanThreads.repository;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductRepository {

    private final Map<Integer, Product> products = new HashMap<>();
    private final AtomicInteger nextId = new AtomicInteger();

    public Product findProduct(int id){
        return products.get(id);
    }

    public Collection<Product> findAllProducts() {
        return products.values();
    }

    public Product saveProduct(@NotNull Product product){
        int id = nextId.getAndIncrement();
        products.put(id,product);
        return product;

    }

    public Product updateProduct(int id, Product product){
        if (!products.containsKey(id)) {
            return null;
        }
        products.put(id, product);
        return product;
    }

    public void deleteProduct(int id) {
        products.remove(id);
    }
}
