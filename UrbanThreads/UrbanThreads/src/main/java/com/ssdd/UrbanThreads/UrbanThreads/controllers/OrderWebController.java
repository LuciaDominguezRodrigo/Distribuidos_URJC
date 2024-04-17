package com.ssdd.UrbanThreads.UrbanThreads.controllers;

import com.ssdd.UrbanThreads.UrbanThreads.entities.*;
import com.ssdd.UrbanThreads.UrbanThreads.services.OrderService;
import com.ssdd.UrbanThreads.UrbanThreads.services.OrderedProductService;
import com.ssdd.UrbanThreads.UrbanThreads.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
public class OrderWebController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderedProductService orderedProductService;

    @Autowired
    private ProductService productService;


    @PostMapping("/newProductInOrder")
    public String addToOrder(@RequestParam("id") int productId,
                             @RequestParam("selectedSize") String size,
                             @RequestParam("selectedColor") String color,
                             @RequestParam("quantity") int quantity) {
        Optional<Product> product = productService.findProduct(productId);
        if (product.isPresent()) {
            orderedProductService.addProductToCurrentOrder(product.get(), Size.valueOf(size), color, quantity);
        }

        return "redirect:/orderPage";
    }


    @GetMapping("/orderPage")
    public String showOrderPage(Model model) {
        Order currentOrder = orderService.getCurrentOrder();
        if(currentOrder == null){
            currentOrder = new Order();
            orderService.addNewOrder(currentOrder);
            orderService.changeCurrentOrder(currentOrder.getId());
        }
        List<OrderedProduct> orderProducts = currentOrder.getOrderedProducts();
        //Remaining selected size units updating for each product in order (now not neccessary because of size table query)
        model.addAttribute("orderId", currentOrder.getId());
        model.addAttribute("allOrdersId", orderService.getAllPendingOrdersId());
        model.addAttribute("productList", orderProducts);


        return "orderPage";
    }

    @PostMapping("/cancelOrder")
    public String cancelOrder() {
        orderService.deleteCurrentOrder();
        //When an order is removed, the current order changes to next created order or, if there´s no more orders created, a new one is created and marked as current order.
        List<Integer> allOrdersId = orderService.getAllPendingOrdersId();
        if(!allOrdersId.isEmpty()){
            orderService.changeCurrentOrder(allOrdersId.get(0));
        } else{
            int newOrderId = orderService.addNewOrder(new Order());
            orderService.changeCurrentOrder(newOrderId);
        }

        return "redirect:/";
    }

    @PostMapping("/orderReady")
    public String makeOrder(Model model) {
        Order currentOrder = orderService.getCurrentOrder();
        if (currentOrder != null) {
            model.addAttribute("productList", currentOrder.getOrderedProducts());
            currentOrder.setOrderStatus(OrderStatus.COMPLETED);
            productService.reduceProductsQuantity(currentOrder.getOrderedProducts());
            orderService.saveOrder(currentOrder);

            //When an order is removed, the current order changes to next created order or, if there´s no more orders created, a new one is created and marked as current order.
            List<Integer> allOrdersId = orderService.getAllPendingOrdersId();
            if(!allOrdersId.isEmpty()){
                orderService.changeCurrentOrder(allOrdersId.get(0));
            } else{
                int newOrderId = orderService.addNewOrder(new Order());
                orderService.changeCurrentOrder(newOrderId);
            }

            return "orderMade";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/editOrder")
    public String editOrderProductQuantity(@RequestParam int productId,
                             @RequestParam String productSize,
                             @RequestParam String productColor,
                             @RequestParam("quantity") int quantity) {
        Order currentOrder = orderService.getCurrentOrder();
        OrderedProduct changedProduct = new OrderedProduct();

        //Locates edited product and updates its quantity as desired
        for (OrderedProduct orderProduct : currentOrder.getOrderedProducts()) { //If product is ordered, must be found
            if(orderProduct.getId() == productId && orderProduct.getSize().equals(Size.valueOf(productSize)) && orderProduct.getColor().equals(productColor)){
                changedProduct = orderProduct;
            }
        }
        changedProduct.setQuantity(quantity);
        orderedProductService.saveOrderedProduct(changedProduct);

        return "redirect:/orderPage";
    }

    @PostMapping("/changeOrder")
    public String changeOrder(@RequestParam int selectedOrder) {
        orderService.changeCurrentOrder(selectedOrder);
        return "redirect:/orderPage";
    }

    @PostMapping("/newOrder")
    public String newOrder() {
        Order newOrder = new Order();
        orderService.addNewOrder(newOrder);
        orderService.changeCurrentOrder(newOrder.getId());
        return "redirect:/orderPage";
    }


    @PostMapping("/deleteProductOrder")
    public String eliminarProductoDePedido(@RequestParam("productId") int productId,
                                           @RequestParam("productSize") String productSize,
                                           @RequestParam("productColor") String productColor,
                                           @RequestParam("productQuantity") int productQuantity) {
        orderedProductService.deleteOrderedProduct(productId, productSize, productColor, productQuantity);
        return "redirect:/orderPage";
    }
}

