package com.ssdd.UrbanThreads.UrbanThreads.services;


import com.ssdd.UrbanThreads.UrbanThreads.entities.Category;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Order;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Size;
import com.ssdd.UrbanThreads.UrbanThreads.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;

@Service
public class DatabaseInitializer {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;



    @PostConstruct
    public void init() throws IOException, SQLException {
        if (categoryRepository.count() == 0) {
            // Inicializar algunas categorías si no existen
            Category category1 = new Category("Hombre", "#D1F2EB", "Ropa urbana de confianza, para todas las edades");
            Category category2 = new Category("Mujer", "#FCF3CF", "Ropa urbana de confianza, para todas las edades");
            Category category3 = new Category("Sin Categoria", "#D2B4DE", "Ropa urbana de confianza, para todas las edades");

            categoryRepository.saveAll(Arrays.asList(category1, category2, category3));

            // Crear y cargar imágenes si no existen productos
            Map<Size, Integer> as = new HashMap<>();
            as.put(Size.XS, 50);
            as.put(Size.S, 40);
            as.put(Size.M, 30);
            as.put(Size.L, 20);
            as.put(Size.XL, 10);
            as.put(Size.XXL, 0);

            Blob photoBlob1 = loadImage("./static/img/camiseta.jpg");
            Blob photoBlob2 = loadImage("./static/img/pantalon.jpg");
            Blob photoBlob3 = loadImage("./static/img/calcetines.jpg");
            Blob photoBlob4 = loadImage("./static/img/abrigo.jpg");
            Blob photoBlob5 = loadImage("./static/img/chaqueta.jpg");
            Blob photoBlob6 = loadImage("./static/img/sudadera.jpg");

            List<Category> categories = categoryRepository.findAll();

            //Category category1 = categories.stream().filter(cat -> "Hombre".equals(cat.getName())).findFirst().orElse(null);
            //Category category2 = categories.stream().filter(cat -> "Mujer".equals(cat.getName())).findFirst().orElse(null);

            // Inicializar algunos productos asociados a las categorías
            Product product1 = new Product("Camiseta", category1, 10.0, photoBlob1, "Descripción 1", as);
            Product product2 = new Product("Pantalón ancho", category1, 20.0, photoBlob2, "Descripción 2", as);
            Product product3 = new Product("Calcetines", category2, 15.0, photoBlob3, "Descripción 3", as);
            Product product4 = new Product("Abrigo", category1, 25.0, photoBlob4, "Descripción 4", as);
            Product product5 = new Product("Chaqueta", category2, 12.0, photoBlob5, "Descripción 5", as);
            Product product6 = new Product("Sudadera", category2, 18.0, photoBlob6, "Descripción 6", as);

            productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5, product6));

            // Inicializar una orden si no existen órdenes
            Order order = new Order();
            orderRepository.save(order);
        }
    }
    private Blob loadImage(String path) throws IOException, SQLException {
        ClassPathResource imgFile = new ClassPathResource(path);
        byte[] photoBytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
        return new SerialBlob(photoBytes);
    }

}
