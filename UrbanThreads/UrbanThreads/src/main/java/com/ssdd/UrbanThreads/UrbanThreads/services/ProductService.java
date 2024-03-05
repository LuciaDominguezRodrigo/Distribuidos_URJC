package com.ssdd.UrbanThreads.UrbanThreads.services;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {
    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong();


    public Product getProduct (Long id){
        return products.get(id);
    }

    public Product createPorduct(@NotNull Product product){
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
}


