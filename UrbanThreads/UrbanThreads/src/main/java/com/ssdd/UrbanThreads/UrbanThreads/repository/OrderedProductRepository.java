package com.ssdd.UrbanThreads.UrbanThreads.repository;

import com.ssdd.UrbanThreads.UrbanThreads.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Long> {

    public List<OrderedProduct> findByOrder(Order o);

    //Calculates the sum of the quantities of a product of that size that are currently selected in any order.
    @Query("SELECT SUM(op.quantity) FROM OrderedProduct op WHERE op.product = :p AND op.size = :s AND op.order.orderStatus = :os")
    public Integer findByProductAndSize(Product p, Size s, OrderStatus os);

    public OrderedProduct findByIdAndOrder(long id, Order o);
    
    @Query("SELECT ordP.id FROM OrderedProduct ordP WHERE ordP.product = :p AND ordP.order = :o AND ordP.quantity = :quantity AND ordP.totalPrice = :totalPrice AND ordP.color = :color AND ordP.name = :n AND ordP.size = :s")
    public Long findOrderedProductId(Product p, Order o, int quantity, double totalPrice, String color, String n, Size s);

}
