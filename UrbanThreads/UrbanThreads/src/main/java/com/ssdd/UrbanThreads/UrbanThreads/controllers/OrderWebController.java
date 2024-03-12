package com.ssdd.UrbanThreads.UrbanThreads.controllers;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Order;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Size;
import com.ssdd.UrbanThreads.UrbanThreads.services.OrderService;
import com.ssdd.UrbanThreads.UrbanThreads.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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
        orderService.addProductToOrder(currentOrder, product, size, color, quantity);

        return "redirect:/orderPage";
    }

    @GetMapping("/orderPage")
    public String showOrderPage(Model model) {
        if (currentOrder != null) {
            model.addAttribute("productList", currentOrder.getProducts());
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
}

