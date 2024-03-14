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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

        String photo =  "/img/camiseta.jpg";
        String photo2 =  "/img/pantalon.jpg";
        String photo3 =  "/img/calcetines.jpg";
        String photo4 =  "/img/abrigo.jpg";
        String photo5 =  "/img/chaqueta.jpg";
        String photo6 =  "/img/sudadera.jpg";


        Category hombre = new Category("Hombre", "#D1F2EB");
        Category mujer = new Category("Mujer", "#FCF3CF");
        Category sin = new Category("Sin Categoria",  "#D2B4DE" );

        categoryRepository.saveCategory(hombre);
        categoryRepository.saveCategory(mujer);
        categoryRepository.saveCategory(sin);




        Map<Size, Integer> availableSizes = new HashMap<>();
        availableSizes.put(Size.XS, 50);
        availableSizes.put(Size.S, 40);
        availableSizes.put(Size.M, 30);
        availableSizes.put(Size.L, 20);
        availableSizes.put(Size.XL, 10);
        availableSizes.put(Size.XXL, 0);

        // Agregar productos con sus fotos al mapa
        productRepository.saveProduct(new Product("Camiseta", hombre, 10.0, photo, "Descripción del producto 1", availableSizes));
        productRepository.saveProduct(new Product("Pantalón ancho", hombre, 20.0, photo2, "Descripción del producto 2", availableSizes));
        productRepository.saveProduct(new Product("Calcetines", mujer, 15.0, photo3, "Descripción del producto 3", availableSizes));
        productRepository.saveProduct(new Product("Abrigo", hombre, 25.0, photo4, "Descripción del producto 4", availableSizes));
        productRepository.saveProduct(new Product("Chaqueta", mujer, 12.0, photo5, "Descripción del producto 5", availableSizes));
        productRepository.saveProduct(new Product("Sudadera", mujer, 18.0, photo6, "Descripción del producto 6", availableSizes));


    }

}
