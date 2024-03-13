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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;


@Controller
public class OrderWebController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    private Order currentOrder;

    @PostMapping("/newProductInOrder")
    public String addToOrder(Model model, @RequestParam("id") int productId,
                             @RequestParam("selectedSize") String size,
                             @RequestParam("selectedColor") String color,
                             @RequestParam("quantity") int quantity) {

        Product product = productService.findProduct(productId);
        if (currentOrder == null) {
            currentOrder = new Order();
        }
        orderService.addProductToOrder(currentOrder, productId, product, size, color, quantity);

        return "redirect:/orderPage";
    }

    @GetMapping("/orderPage")
    public String showOrderPage(Model model) {
        List<Product> orderProducts = currentOrder.getProducts();
        for (Product p : orderProducts) {
            p.setSelectedSizeAvailableUnits(productService.findProduct(p.getId()).getAvailableSizes().get(Size.valueOf(p.getSize())));
        }
        if (currentOrder != null) {
            model.addAttribute("productList", orderProducts);
        }
        return "orderPage";
    }

    @PostMapping("/cancelOrder")
    public String cancelOrder() {
        currentOrder = null;
        return "redirect:/";
    }

    @PostMapping("/orderReady")
    public String makeOrder(Model model) {
        if (currentOrder != null) {
            model.addAttribute("productList", currentOrder.getProducts());

            currentOrder = null;
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

