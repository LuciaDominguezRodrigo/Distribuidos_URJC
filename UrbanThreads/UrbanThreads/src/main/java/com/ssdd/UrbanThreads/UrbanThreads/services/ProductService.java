package com.ssdd.UrbanThreads.UrbanThreads.services;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product findProduct (Long id){
        return productRepository.findProduct(id);
    }

    public Collection<Product> findAllProducts() {
        return productRepository.findAllProducts();
    }

    public Product saveProduct(@NotNull Product product){
        return productRepository.saveProduct(product);
    }

    public Product updateProduct (Long id, Product product){
        return productRepository.updateProduct(id,product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }

}


