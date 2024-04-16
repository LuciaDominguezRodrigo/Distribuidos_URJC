package com.ssdd.UrbanThreads.UrbanThreads.repository;


import com.ssdd.UrbanThreads.UrbanThreads.entities.DProduct;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DProductRepository extends JpaRepository<DProduct,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE DProduct p SET p.name = :#{#product.name}, p.description = :#{#product.description}, p.price = :#{#product.price}, p.category = :#{#product.category} WHERE p.id = :id")
    void updateProduct(@Param("id") long id, @Param("product") DProduct product);
    List<DProduct> findByCategoryName(String categoryName);

    List<DProduct> findByIdBetween(int startId, int endId);
}
