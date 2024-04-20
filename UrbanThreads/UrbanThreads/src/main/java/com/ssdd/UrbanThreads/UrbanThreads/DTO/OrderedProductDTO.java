package com.ssdd.UrbanThreads.UrbanThreads.DTO;

import com.ssdd.UrbanThreads.UrbanThreads.entities.OrderedProduct;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderedProductDTO {

    private Long productId;

    private String size;

    private String color;

    private int quantity;

    private double totalPrice;


    public OrderedProductDTO(Long productId, String size, String color, int quantity, double totalPrice) {
        this.productId = productId;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public OrderedProductDTO (OrderedProduct product) { //Product object, only filled with specific order information
        this.productId = product.getProduct().getId();
        this.size = product.getSize().toString();
        this.color = product.getColor();
        this.quantity = product.getQuantity();
        this.totalPrice = product.getTotalPrice();
    }
}
