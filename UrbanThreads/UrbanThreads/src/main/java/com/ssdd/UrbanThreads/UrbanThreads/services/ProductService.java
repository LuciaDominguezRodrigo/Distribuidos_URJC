package com.ssdd.UrbanThreads.UrbanThreads.services;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Category;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class ProductService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRepository productRepository;

    public Product findProduct (int id){
        return productRepository.findProduct(id);
    }

    public Collection<Product> findAllProducts() {
        return productRepository.findAllProducts();
    }

    public List<Product> findByCurrentCategoryAndIdRange(int start, int end) {
        List<Product> productsByCategory = this.findProductsByCategory(categoryService.getCurrentCategoryFilter());
        List<Product> selectedProducts = new ArrayList<>();
        if(start > productsByCategory.size()-1){
            return selectedProducts;
        }
        else if(end > productsByCategory.size()-1){
            return productsByCategory.subList(start,productsByCategory.size());
        }
        else {
            return productsByCategory.subList(start,end+1);
        }
    }

    public Product saveProduct(@NotNull Product product){
        return productRepository.saveProduct(product);
    }

    public Product updateProduct (int id, Product product){
        return productRepository.updateProduct(id,product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteProduct(id);
    }

    public List<Product> findProductsByCategory(String categoria) {
        return productRepository.findByCategoryName(categoria);

    }

    public String findProductName(int id) {
       return  productRepository.findProduct(id).getName();


    }

    public Product findProductByName(String name) {
        return productRepository.findProductByName(name);
    }
}


