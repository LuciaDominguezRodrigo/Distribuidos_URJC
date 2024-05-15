package com.ssdd.UrbanThreads.UrbanThreads.services;

import com.ssdd.UrbanThreads.UrbanThreads.entities.OrderStatus;
import com.ssdd.UrbanThreads.UrbanThreads.entities.OrderedProduct;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Size;
import com.ssdd.UrbanThreads.UrbanThreads.repository.OrderedProductRepository;
import com.ssdd.UrbanThreads.UrbanThreads.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderedProductRepository orderedProductRepository;


    public List<Product> findProductsByCategory(String categoryName) {
        if(categoryName.equals("")){
            return productRepository.findAll();
        }
        return productRepository.findByCategoryName(categoryName);

    }

    public void updateProduct(long id, Product product) {
        productRepository.updateProduct(id,product);

    }

    public boolean reduceProductsQuantity(List<OrderedProduct> soldProducts){
        boolean enoughProducts = true;
        for (OrderedProduct p : soldProducts) {
            int currentQuantity = 0;
            if(p.getQuantity() > p.getProduct().getAvailableSizes().get(p.getSize())){
                enoughProducts = false;
                //Update the ordered product quantity, at maximum available quantity
                p.setQuantity(p.getProduct().getAvailableSizes().get(p.getSize()));
            } else{
                currentQuantity = p.getProduct().getAvailableSizes().get(p.getSize())-p.getQuantity();
            }
            //Update product sizes of the ordered product
            p.getProduct().getAvailableSizes().put(p.getSize(), currentQuantity);
            productRepository.save(p.getProduct());
        }
        return enoughProducts;
    }

    public List<Product> findByCurrentCategoryAndIdRange(int startId, int endId) {
        return productRepository.findByIdBetween(startId, endId);
    }

    //Returns the number of products of given size that are actually in an order
    public int getSelectedProducts(Product product, Size size){
        Integer orders = orderedProductRepository.findByProductAndSize(product, size, OrderStatus.PENDING);
        if(orders == null){
            return 0;
        }
        else return orders;
    }

    public Collection<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
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
