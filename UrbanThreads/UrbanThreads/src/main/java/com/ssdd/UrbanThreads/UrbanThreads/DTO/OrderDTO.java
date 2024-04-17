package com.ssdd.UrbanThreads.UrbanThreads.DTO;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Order;
import com.ssdd.UrbanThreads.UrbanThreads.entities.OrderStatus;
import com.ssdd.UrbanThreads.UrbanThreads.entities.OrderedProduct;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderDTO {

    private int orderId;

    private OrderStatus orderStatus;

    private List<OrderedProductDTO> orderedProductsDTO = new ArrayList<>();

    public OrderDTO(int orderId, OrderStatus orderStatus, List<OrderedProductDTO> orderedProductsDTO) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderedProductsDTO = orderedProductsDTO;
    }

    public  OrderDTO (Order order) {
        this.orderId = order.getId();
        this.orderStatus = order.getOrderStatus();
        for (OrderedProduct p : order.getOrderedProducts()) {
            this.orderedProductsDTO.add(new OrderedProductDTO(p));
        }
    }
}
