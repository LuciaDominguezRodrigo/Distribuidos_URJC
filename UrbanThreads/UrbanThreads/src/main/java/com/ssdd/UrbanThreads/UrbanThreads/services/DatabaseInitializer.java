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
    private OrderService orderService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductService productService;


    @PostConstruct
    public void init() throws IOException, SQLException {
        if (categoryRepository.count() == 0) {
            // Inicializar algunas categorías si no existen
            categoryService.addNewCategory(new Category("Hombre", "#D1F2EB", "Ropa urbana de confianza, para todas las edades"));
            categoryService.addNewCategory(new Category("Mujer", "#FCF3CF", "Ropa urbana de confianza, para todas las edades"));
            categoryService.addNewCategory(new Category("Sin Categoria", "#D2B4DE", "Ropa urbana de confianza, para todas las edades"));

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

            //List<Category> categories = categoryRepository.findAll();

            //Category category1 = categories.stream().filter(cat -> "Hombre".equals(cat.getName())).findFirst().orElse(null);
            //Category category2 = categories.stream().filter(cat -> "Mujer".equals(cat.getName())).findFirst().orElse(null);

            // Inicializar algunos productos asociados a las categorías
            productService.saveProduct(new Product("Camiseta", categoryService.findCategoryByName("Hombre"), 10.0, photoBlob1, "Descripción 1", as));
            productService.saveProduct(new Product("Pantalón ancho", categoryService.findCategoryByName("Hombre"), 20.0, photoBlob2, "Descripción 2", as));
            productService.saveProduct(new Product("Calcetines", categoryService.findCategoryByName("Mujer"), 15.0, photoBlob3, "Descripción 3", as));
            productService.saveProduct(new Product("Abrigo", categoryService.findCategoryByName("Hombre"), 25.0, photoBlob4, "Descripción 4", as));
            productService.saveProduct(new Product("Chaqueta", categoryService.findCategoryByName("Mujer"), 12.0, photoBlob5, "Descripción 5", as));
            productService.saveProduct(new Product("Sudadera", categoryService.findCategoryByName("Mujer"), 18.0, photoBlob6, "Descripción 6", as));
        }
    }
    private Blob loadImage(String path) throws IOException, SQLException {
        ClassPathResource imgFile = new ClassPathResource(path);
        byte[] photoBytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
        return new SerialBlob(photoBytes);
    }

}
