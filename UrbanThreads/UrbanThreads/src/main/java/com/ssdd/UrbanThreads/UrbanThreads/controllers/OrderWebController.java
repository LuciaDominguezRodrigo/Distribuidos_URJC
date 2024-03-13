package com.ssdd.UrbanThreads.UrbanThreads.controllers;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Order;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Size;
import com.ssdd.UrbanThreads.UrbanThreads.services.OrderService;
import com.ssdd.UrbanThreads.UrbanThreads.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
public class OrderWebController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @PostMapping("/newProductInOrder")
    public String addToOrder(@RequestParam("id") int productId,
                             @RequestParam("selectedSize") String size,
                             @RequestParam("selectedColor") String color,
                             @RequestParam("quantity") int quantity) {
        Product product = productService.findProduct(productId);
        orderService.addProductToCurrentOrder(productId, product, size, color, quantity);

        return "redirect:/orderPage";
    }

    @GetMapping("/orderPage")
    public String showOrderPage(Model model) {
        Order currentOrder = orderService.getCurrentOrder();
        if(currentOrder == null){
            return "redirect:/";
        } else{
            List<Product> orderProducts = currentOrder.getProducts();
            for (Product p : orderProducts) {
                p.setSelectedSizeAvailableUnits(productService.findProduct(p.getId()).getAvailableSizes().get(Size.valueOf(p.getSize())));
            }
            model.addAttribute("productList", orderProducts);
            return "orderPage";
        }
    }

    @PostMapping("/cancelOrder")
    public String cancelOrder() {
        orderService.deleteCurrentOrder();
        return "redirect:/";
    }

    @PostMapping("/orderReady")
    public String makeOrder(Model model) {
        Order currentOrder = orderService.getCurrentOrder();
        if (currentOrder != null) {
            model.addAttribute("productList", currentOrder.getProducts());
            orderService.deleteCurrentOrder();
            return "orderMade";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/editOrder")
    public String newProduct(@RequestParam int productId,
                             @RequestParam String productSize,
                             @RequestParam String productColor,
                             @RequestParam("quantity") int quantity) {

        Order currentOrder = orderService.getCurrentOrder();
        Product changedProduct = new Product();
        for (Product orderProduct : currentOrder.getOrderProducts()) { //If product is ordered, must be found
            if(orderProduct.getId() == productId && orderProduct.getSize().equals(productSize) && orderProduct.getColor().equals(productColor)){
                changedProduct = orderProduct;
            }
        }
        changedProduct.setQuantity(quantity);

        return "redirect:/orderPage";
    }
}

