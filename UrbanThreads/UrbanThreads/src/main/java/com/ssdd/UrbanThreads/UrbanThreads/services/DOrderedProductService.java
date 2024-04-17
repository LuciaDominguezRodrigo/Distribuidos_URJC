package com.ssdd.UrbanThreads.UrbanThreads.services;


import com.ssdd.UrbanThreads.UrbanThreads.repository.DOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DOrderedProductService {

    @Autowired
    private DOrderService orderService;

    @Autowired
    private DOrderRepository orderRepository;


    //Currently implemented in OrderService
    /*public void addProductToCurrentOrder(int id, Product product, String size, String color, int quantity) {
        Order currentOrder = orderRepository.findByOrderId(orderService.getSelectedOrder());

        if (currentOrder == null) {
            currentOrder = new Order();
        }

        Product newProduct = new Product();
        newProduct.setId(id);
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setSize(size);
        newProduct.setColor(color);
        newProduct.setQuantity(quantity);

        currentOrder.getOrderProducts().add(newProduct);

    }

    //Currently implemented in OrderService
    public void deleteOrderedProduct(int orderId, int productId) {
        Order currentOrder = orderRepository.findOrder(orderId);

        if (currentOrder != null) {
            boolean productoEncontrado = false;
            Iterator<Product> iterator = currentOrder.getOrderProducts().iterator();
            while (iterator.hasNext()) {
                Product product = iterator.next();
                if (product.getId() == productId) {
                    iterator.remove();
                    productoEncontrado = true;
                }
            }

            if (productoEncontrado) {
                orderRepository.saveCurrentOrder(currentOrder);
            }
        }
    }*/
}
