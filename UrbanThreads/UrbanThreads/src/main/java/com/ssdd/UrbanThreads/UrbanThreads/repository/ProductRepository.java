package com.ssdd.UrbanThreads.UrbanThreads.repository;


import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.name = :#{#product.name}, p.description = :#{#product.description}, p.price = :#{#product.price}, p.category = :#{#product.category} WHERE p.id = :id")
    void updateProduct(@Param("id") long id, @Param("product") Product product);

    List<Product> findByCategoryName(String categoryName);

    List<Product> findByIdBetween(int startId, int endId);
}
