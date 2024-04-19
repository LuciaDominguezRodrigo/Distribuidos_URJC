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

    //Calcula la suma de las cantidades de un producto de esa talla que están actualmente seleccionadas en cualquier pedido, pero quitando la cantidad del producto del color que se está seleccionando.
}
