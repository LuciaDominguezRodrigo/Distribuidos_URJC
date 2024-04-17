package com.ssdd.UrbanThreads.UrbanThreads.services;

import com.ssdd.UrbanThreads.UrbanThreads.entities.OrderedProduct;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Size;
import com.ssdd.UrbanThreads.UrbanThreads.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRepository productRepository;


    public List<Product> findProductsByCategory(String categoryName) {
        return productRepository.findByCategoryName(categoryName);

    }

    public void updateProduct(long id, Product product) {
        productRepository.updateProduct(id,product);

    }

    public List<Product> updateProductsQuantity(List<OrderedProduct> soldProducts){
        for (OrderedProduct p : soldProducts) {
            int currentQuantity = p.getProduct().getAvailableSizes().get(Size.valueOf(p.getSize()))-p.getQuantity();
            //Acualiza las tallas del Product asociado al OrderedProduct
            p.getProduct().getAvailableSizes().put(Size.valueOf(p.getSize()), currentQuantity);
        }
        return productRepository.findAll();
    }

    public List<Product> findByCurrentCategoryAndIdRange(int startId, int endId) {
        return productRepository.findByIdBetween(startId, endId);
    }

    public Collection<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findProduct(long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
