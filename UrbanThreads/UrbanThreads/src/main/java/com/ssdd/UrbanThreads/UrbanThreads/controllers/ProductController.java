package com.ssdd.UrbanThreads.UrbanThreads.controllers;


import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/")
    public String index(Model model) {
        // Crear 6 productos predefinidos
        Set<String> sizes = new HashSet<>();
        sizes.add("S");
        sizes.add("M");
        sizes.add("L");

        // Agregar estos productos a la base de datos temporalmente
        productService.createProduct(new Product(1L, "Producto 1", null, sizes, 10.0, null, "Descripción del producto 1"));
        productService.createProduct(new Product(2L, "Producto 2", null, sizes, 20.0, null, "Descripción del producto 2"));
        productService.createProduct(new Product(3L, "Producto 3", null, sizes, 15.0, null, "Descripción del producto 3"));
        productService.createProduct(new Product(4L, "Producto 4", null, sizes, 25.0, null, "Descripción del producto 4"));
        productService.createProduct(new Product(5L, "Producto 5", null, sizes, 12.0, null, "Descripción del producto 5"));
        productService.createProduct(new Product(6L, "Producto 6", null, sizes, 18.0, null, "Descripción del producto 6"));

        // Obtener todos los productos de la base de datos
        model.addAttribute("productos", productService.getAllProducts());
        return "index";
    }

    @GetMapping("/product/image/{productId}")
    public byte[] showProductImage(@PathVariable long productId) throws SQLException, IOException {
        Optional<Product> productOptional = productService.findById(productId);
        if (productOptional.isPresent()) {
            Blob photoBlob = productOptional.get().getPhoto();
            int blobLength = (int) photoBlob.length();
            byte[] blobAsBytes = photoBlob.getBytes(1, blobLength);
            photoBlob.free();
            return blobAsBytes;
        } else {
            return new byte[0];
        }
    }
}
