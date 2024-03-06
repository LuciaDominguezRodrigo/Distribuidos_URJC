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
            saveProduct(new Product(1L, "Camiseta", null, sizes, 10.0, photo, "Descripción del producto 1"));
            saveProduct(new Product(2L, "Pantalon ancho", null, sizes, 20.0, photo2, "Descripción del producto 2"));
            saveProduct(new Product(3L, "Calcetines", null, sizes, 15.0, photo3, "Descripción del producto 3"));
            saveProduct(new Product(4L, "Abrigo", null, sizes, 25.0, photo4, "Descripción del producto 4"));
            saveProduct(new Product(5L, "Chaqueta", null, sizes, 12.0, photo5, "Descripción del producto 5"));
            saveProduct(new Product(6L, "Sudadera", null, sizes, 18.0, photo6, "Descripción del producto 6"));


    }
}


