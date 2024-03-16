package com.ssdd.UrbanThreads.UrbanThreads.DTO;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Order;
import com.ssdd.UrbanThreads.UrbanThreads.entities.OrderStatus;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
    @Getter
    @Setter
    private int orderId;
    @Getter
    @Setter
    private OrderStatus orderStatus;
    @Getter
    @Setter
    private List<OrderedProductDTO> orderedProductsDTO = new ArrayList<>();

    public OrderDTO(int orderId, OrderStatus orderStatus, List<OrderedProductDTO> orderedProductsDTO) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderedProductsDTO = orderedProductsDTO;
    }

    public  OrderDTO (Order order) {
        this.orderId = order.getOrderId();
        this.orderStatus = order.getOrderStatus();
        for (Product p : order.getOrderProducts()) {
            this.orderedProductsDTO.add(new OrderedProductDTO(p));
        }
    }
}
