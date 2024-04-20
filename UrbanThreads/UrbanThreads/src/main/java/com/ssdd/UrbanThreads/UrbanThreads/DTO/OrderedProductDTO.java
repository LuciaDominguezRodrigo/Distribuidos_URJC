package com.ssdd.UrbanThreads.UrbanThreads.DTO;

import com.ssdd.UrbanThreads.UrbanThreads.entities.OrderedProduct;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderedProductDTO {

    private Long id;

    private Long orderId;

    private Long productId;

    private String name;

    private String size;

    private String color;

    private int quantity;

    private double totalPrice;


    public OrderedProductDTO(Long id, Long orderId, Long productId, String name, String size, String color, int quantity, double totalPrice) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.name = name;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public  OrderedProductDTO (OrderedProduct product) { //Product object, only filled with specific order information
        this.id = product.getId();
        this.orderId = product.getOrder().getId();
        this.productId = product.getProduct().getId();
        this.name = product.getName();
        this.size = product.getSize().toString();
        this.color = product.getColor();
        this.quantity = product.getQuantity();
        this.totalPrice = product.getTotalPrice();
    }
}
