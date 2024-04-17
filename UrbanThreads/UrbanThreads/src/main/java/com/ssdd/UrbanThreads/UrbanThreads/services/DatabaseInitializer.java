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
        // Inicializar algunas categorías
        Category category1 = new Category("Hombre", "#D1F2EB","Ropa urbana de confianza, para todas las edades");
        Category category2 = new Category("Mujer", "#FCF3CF", "Ropa urbana de confianza, para todas las edades");
        Category category3 = new Category("Sin Categoria",  "#D2B4DE","Ropa urbana de confianza, para todas las edades");

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);

        Map<Size, Integer> as = new HashMap<>();
        as.put(Size.XS, 50);
        as.put(Size.S, 40);
        as.put(Size.M, 30);
        as.put(Size.L, 20);
        as.put(Size.XL, 10);
        as.put(Size.XXL, 0);

        ClassPathResource imgFile = new ClassPathResource("./static/img/camiseta.jpg");
        byte[] photoBytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
        Blob photoBlob1 = new SerialBlob(photoBytes);

        ClassPathResource imgFile2 = new ClassPathResource("./static/img/pantalon.jpg");
        byte[] photoBytes2 = StreamUtils.copyToByteArray(imgFile2.getInputStream());
        Blob photoBlob2 = new SerialBlob(photoBytes2);

        ClassPathResource imgFile3 = new ClassPathResource("./static/img/calcetines.jpg");
        byte[] photoBytes3 = StreamUtils.copyToByteArray(imgFile3.getInputStream());
        Blob photoBlob3 = new SerialBlob(photoBytes3);


        ClassPathResource imgFile4 = new ClassPathResource("./static/img/abrigo.jpg");
        byte[] photoBytes4 = StreamUtils.copyToByteArray(imgFile4.getInputStream());
        Blob photoBlob4 = new SerialBlob(photoBytes4);

        ClassPathResource imgFile5 = new ClassPathResource("./static/img/chaqueta.jpg");
        byte[] photoBytes5 = StreamUtils.copyToByteArray(imgFile5.getInputStream());
        Blob photoBlob5 = new SerialBlob(photoBytes5);

        ClassPathResource imgFile6 = new ClassPathResource("./static/img/sudadera.jpg");
        byte[] photoBytes6 = StreamUtils.copyToByteArray(imgFile6.getInputStream());
        Blob photoBlob6 = new SerialBlob(photoBytes6);

        // Inicializar algunos productos asociados a las categorías
        Product product1 = new Product("Camiseta", category1, 10.0, photoBlob1,"Descripción 1",as);
        Product product2 = new Product("Pantalón ancho", category1, 20.0, photoBlob2,"Descripción 2",as);
        Product product3 = new Product("Calcetines", category2, 15.0, photoBlob3,"Descripción 3",as);
        Product product4 = new Product("Abrigo", category1, 25.0, photoBlob4,"Descripción 4",as);
        Product product5 = new Product("Chaqueta", category2, 12.0, photoBlob5,"Descripción 5",as);
        Product product6 = new Product("Sudadera", category2, 18.0, photoBlob6,"Descripción 6",as);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
        productRepository.save(product6);

        // Inicializar una orden con algunos elementos de pedido
        Order order = new Order();
        Set<Order> orderItems = new HashSet<>();
        orderRepository.save(order);
    }

}
