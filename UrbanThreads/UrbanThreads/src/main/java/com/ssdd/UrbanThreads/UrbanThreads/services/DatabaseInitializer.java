package com.ssdd.UrbanThreads.UrbanThreads.services;


import com.ssdd.UrbanThreads.UrbanThreads.entities.DCategory;
import com.ssdd.UrbanThreads.UrbanThreads.entities.DProduct;
import com.ssdd.UrbanThreads.UrbanThreads.entities.DOrder;
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
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class DatabaseInitializer {
    @Autowired
    private DCategoryRepository categoryRepository;

    @Autowired
    private DOrderRepository orderRepository;

    @Autowired
    private DProductRepository productRepository;


    @PostConstruct
    public void init() throws IOException, SQLException {
        // Inicializar algunas categorías
        DCategory category1 = new DCategory("Hombre", "#D1F2EB","Ropa urbana de confianza, para todas las edades");
        DCategory category2 = new DCategory("Mujer", "#FCF3CF", "Ropa urbana de confianza, para todas las edades");
        DCategory category3 = new DCategory("Sin Categoria",  "#D2B4DE","Ropa urbana de confianza, para todas las edades");

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);

        ClassPathResource imgFile = new ClassPathResource("static/img/camiseta.jpg");
        byte[] photoBytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
        Blob photoBlob1 = new SerialBlob(photoBytes);

        ClassPathResource imgFile2 = new ClassPathResource("static/img/pantalon.jpg");
        byte[] photoBytes2 = StreamUtils.copyToByteArray(imgFile.getInputStream());
        Blob photoBlob2 = new SerialBlob(photoBytes);

        ClassPathResource imgFile3 = new ClassPathResource("static/img/calcetines.jpg");
        byte[] photoBytes3 = StreamUtils.copyToByteArray(imgFile.getInputStream());
        Blob photoBlob3 = new SerialBlob(photoBytes);

        ClassPathResource imgFile4 = new ClassPathResource("static/img/abrigo.jpg");
        byte[] photoBytes4 = StreamUtils.copyToByteArray(imgFile.getInputStream());
        Blob photoBlob4 = new SerialBlob(photoBytes);

        ClassPathResource imgFile5 = new ClassPathResource("static/img/chaqueta.jpg");
        byte[] photoBytes5 = StreamUtils.copyToByteArray(imgFile.getInputStream());
        Blob photoBlob5 = new SerialBlob(photoBytes);

        ClassPathResource imgFile6 = new ClassPathResource("static/img/sudadera.jpg");
        byte[] photoBytes6 = StreamUtils.copyToByteArray(imgFile.getInputStream());
        Blob photoBlob6 = new SerialBlob(photoBytes);

        // Inicializar algunos productos asociados a las categorías
        DProduct product1 = new DProduct("Camiseta", category1, 10.0, photoBlob1,"Descripción 1");
        DProduct product2 = new DProduct("Pantalón ancho", category1, 20.0, photoBlob2,"Descripción 2");
        DProduct product3 = new DProduct("Calcetines", category2, 15.0, photoBlob3,"Descripción 3");
        DProduct product4 = new DProduct("Abrigo", category1, 25.0, photoBlob4,"Descripción 4");
        DProduct product5 = new DProduct("Chaqueta", category2, 12.0, photoBlob5,"Descripción 5");
        DProduct product6 = new DProduct("Sudadera", category2, 18.0, photoBlob6,"Descripción 6");

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
        productRepository.save(product6);

        // Inicializar una orden con algunos elementos de pedido
        DOrder order = new DOrder(new Date());
        Set<DOrder> orderItems = new HashSet<>();
        order.setOrderItems(orderItems);
        orderRepository.save(order);
    }

}
