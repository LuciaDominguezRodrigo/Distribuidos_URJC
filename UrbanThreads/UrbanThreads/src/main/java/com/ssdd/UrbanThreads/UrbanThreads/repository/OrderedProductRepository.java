package com.ssdd.UrbanThreads.UrbanThreads.repository;

import com.ssdd.UrbanThreads.UrbanThreads.entities.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Long> {

}
