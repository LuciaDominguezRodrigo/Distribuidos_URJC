package com.ssdd.UrbanThreads.UrbanThreads.repository;


import com.ssdd.UrbanThreads.UrbanThreads.entities.DOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DOrderRepository extends JpaRepository<DOrder,Long> {

    public DOrder findByOrderId(int id);

    public List<Integer> findOrderIdsByOrderStatus(String status);

    public DOrder deleteOrderByOrderId(int id);
}
