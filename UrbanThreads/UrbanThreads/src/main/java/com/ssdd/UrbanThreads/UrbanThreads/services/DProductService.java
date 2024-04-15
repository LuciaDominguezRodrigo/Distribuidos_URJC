package com.ssdd.UrbanThreads.UrbanThreads.services;

import com.ssdd.UrbanThreads.UrbanThreads.entities.DProduct;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.repository.DProductRepository;
import com.ssdd.UrbanThreads.UrbanThreads.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DProductService {
    @Autowired
    private DCategoryService categoryService;

    @Autowired
    private DProductRepository productRepository;
    public List<DProduct> findProductsByCategory(String categoryName) {
        return productRepository.findByCategoryName(categoryName);

    }

    public void updateProduct(long id, DProduct product) {
        productRepository.updateProduct(id,product);

    }
}
