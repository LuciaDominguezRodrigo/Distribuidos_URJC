package com.ssdd.UrbanThreads.UrbanThreads.services;


import com.ssdd.UrbanThreads.UrbanThreads.entities.DCategory;
import com.ssdd.UrbanThreads.UrbanThreads.entities.DProduct;
import com.ssdd.UrbanThreads.UrbanThreads.entities.DOrder;
import com.ssdd.UrbanThreads.UrbanThreads.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void init() {
        // Inicializar algunas categorías
        DCategory category1 = new DCategory("Category 1");
        DCategory category2 = new DCategory("Category 2");
        categoryRepository.save(category1);
        categoryRepository.save(category2);

        // Inicializar algunos productos asociados a las categorías
        DProduct product1 = new DProduct("Product 1", category1);
        DProduct product2 = new DProduct("Product 2", category2);
        productRepository.save(product1);
        productRepository.save(product2);

        // Inicializar una orden con algunos elementos de pedido
        DOrder order = new DOrder(new Date());
        Set<DOrder> orderItems = new HashSet<>();
        order.setOrderItems(orderItems);
        orderRepository.save(order);
    }

    }
