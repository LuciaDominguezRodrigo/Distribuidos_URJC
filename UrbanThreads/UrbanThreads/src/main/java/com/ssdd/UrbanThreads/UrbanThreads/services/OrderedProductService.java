package com.ssdd.UrbanThreads.UrbanThreads.services;


import com.ssdd.UrbanThreads.UrbanThreads.entities.Order;
import com.ssdd.UrbanThreads.UrbanThreads.entities.OrderedProduct;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Size;
import com.ssdd.UrbanThreads.UrbanThreads.repository.OrderRepository;
import com.ssdd.UrbanThreads.UrbanThreads.repository.OrderedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedProductService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderedProductRepository orderedProductRepository;

    public OrderedProduct findByIdAndOrder(long id, Order o){
        return orderedProductRepository.findByIdAndOrder(id, o);
    }

    public void saveOrderedProduct(OrderedProduct orderedProduct) {
        orderedProductRepository.save(orderedProduct);
    }

    public Long addProductToOrder(Order o, Product product, Size size, String color, int quantity) {
        OrderedProduct newProduct = new OrderedProduct();

        newProduct.setOrder(o);
        newProduct.setProduct(product);
        newProduct.setName(product.getName());
        newProduct.setSize(size);
        newProduct.setColor(color);
        newProduct.setQuantity(quantity);
        newProduct.setTotalPrice(product.getPrice() * quantity);

        Long orderedProductId = -1L;
        for (OrderedProduct op : o.getOrderedProducts()) {
            if(op.getOrder().getId() == op.getOrder().getId() && op.getName().equals(newProduct.getName()) && op.getSize().equals(newProduct.getSize()) && op.getColor().equals(newProduct.getColor())) {
                op.setQuantity(op.getQuantity() + quantity);
                op.setTotalPrice(op.getTotalPrice() + newProduct.getTotalPrice());
                orderedProductId = op.getId();
                orderedProductRepository.save(op);
            }
        }

        orderedProductRepository.save(newProduct);
        if(orderedProductId == -1){
            orderedProductId = orderedProductRepository.findOrderedProductId(newProduct.getProduct(), newProduct.getOrder(), newProduct.getQuantity(), newProduct.getTotalPrice(), newProduct.getColor(), newProduct.getName(), newProduct.getSize());
        }
        return orderedProductId;
    }

    public void deleteOrderedProduct(Long productId, String productSize, String productColor, int productQuantity) {
        List<OrderedProduct> productsInOrder = orderedProductRepository.findByOrder(orderService.getCurrentOrder());
        if (productsInOrder != null) {
            for (OrderedProduct p : productsInOrder) { //If product is ordered, must be found
                if(p.getId().equals(productId) && p.getSize().equals(Size.valueOf(productSize)) && p.getColor().equals(productColor) && p.getQuantity() == productQuantity){
                    orderedProductRepository.delete(p);
                }
            }

        }
    }
}
