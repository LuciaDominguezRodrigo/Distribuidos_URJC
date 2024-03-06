package com.ssdd.UrbanThreads.UrbanThreads.services;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Category;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Size;
import com.ssdd.UrbanThreads.UrbanThreads.repository.CategoryRepository;
import com.ssdd.UrbanThreads.UrbanThreads.repository.OrderRepository;
import com.ssdd.UrbanThreads.UrbanThreads.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MapDataInitializer {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void init() {
        // Agregar fotos al mapa

        String photo =  "/img/camiseta.jpg";
        String photo2 =  "/img/pantalon.jpg";
        String photo3 =  "/img/calcetines.jpg";
        String photo4 =  "/img/abrigo.jpg";
        String photo5 =  "/img/chaqueta.jpg";
        String photo6 =  "/img/sudadera.jpg";

        categoryRepository.saveCategory(new Category(11L,"Hombre"));
        categoryRepository.saveCategory(new Category(22L,"Mujer"));
        categoryRepository.saveCategory(new Category(33L,"Niño/a"));

        // Agregar productos con sus fotos al mapa
        productRepository.saveProduct(new Product(1L, "Camiseta", categoryRepository.findCategory(11L), Size.XS, 10.0, photo, "Descripción del producto 1"));
        productRepository.saveProduct(new Product(2L, "Pantalon ancho", categoryRepository.findCategory(11L), Size.S, 20.0, photo2, "Descripción del producto 2"));
        productRepository.saveProduct(new Product(3L, "Calcetines", categoryRepository.findCategory(22L), Size.M, 15.0, photo3, "Descripción del producto 3"));
        productRepository.saveProduct(new Product(4L, "Abrigo", categoryRepository.findCategory(22L), Size.L, 25.0, photo4, "Descripción del producto 4"));
        productRepository.saveProduct(new Product(5L, "Chaqueta", categoryRepository.findCategory(33L), Size.XL, 12.0, photo5, "Descripción del producto 5"));
        productRepository.saveProduct(new Product(6L, "Sudadera", categoryRepository.findCategory(33L), Size.XXL, 18.0, photo6, "Descripción del producto 6"));


    }

}
