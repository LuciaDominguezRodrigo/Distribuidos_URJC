package com.ssdd.UrbanThreads.UrbanThreads.repository;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Category;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Size;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProductRepository {

    private final Map<Integer, Product> products = new HashMap<>();
    private final AtomicInteger nextId = new AtomicInteger();

    public Product findProduct(int id){ return products.get(id); }

    public List<Product> findAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.addAll(products.values());
        return productList;
    }

    public List<Product> findByIdRange (int start, int end) {
        List<Product> foundProducts = new ArrayList<>();
        for (Integer i : products.keySet()) {
            if (i >= start && i <= end){
                foundProducts.add(products.get(i));
            }
        }
        return foundProducts;
    }

    public List<Product> updateProductsQuantity(List<Product> soldProducts){
        List<Product> updatedProducts = new ArrayList<>();
        for (Product p : soldProducts) {
            Map<Size, Integer> availableSizes = products.get(p.getId()).getAvailableSizes();
            availableSizes.put(Size.valueOf(p.getSize()), availableSizes.get(Size.valueOf(p.getSize()))-p.getQuantity());
            updatedProducts.add(products.get(p.getId()));
        }
        return updatedProducts;
    }

    public Product saveProduct(@NotNull Product product){
        int id = this.nextId.getAndIncrement();
        product.setId(id);
        products.put(id,product);
        return product;
    }

    public Product updateProduct(int id, Product product){
        if (!products.containsKey(id)) {
            return null;
        }
        products.put(id, product);
        return product;
    }

    public void deleteProduct(int id) {
        products.remove(id);
    }

    public List<Product> findByCategoryName(String categoria) {
        if(categoria.equals("")){ return findAllProducts(); }
        else{
            List<Product> matchingProducts = new ArrayList<>();
            for (Product product : products.values()) {
                Category category = product.getCategory();
                if (category != null && category.getName() != null && category.getName().equalsIgnoreCase(categoria)) {
                    matchingProducts.add(product);
                }
            }
            return matchingProducts;
        }
    }

    public Product findProductByName(String name) {
        for (Product product : products.values()) {
            if (product.getName() != null && product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }
}
