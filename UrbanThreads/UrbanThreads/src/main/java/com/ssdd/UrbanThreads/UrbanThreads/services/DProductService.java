package com.ssdd.UrbanThreads.UrbanThreads.services;

import com.ssdd.UrbanThreads.UrbanThreads.entities.DProduct;
import com.ssdd.UrbanThreads.UrbanThreads.repository.DProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

    public List<DProduct> findByCurrentCategoryAndIdRange(int startId, int endId) {
        return productRepository.findByIdBetween(startId, endId);
    }

    public Collection<DProduct> findAllProducts() {
        return productRepository.findAll();
    }

    public Optional<DProduct> findProduct(long id) {
        return productRepository.findById(id);
    }

    public void saveProduct(DProduct newProduct) {
        productRepository.save(newProduct);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
