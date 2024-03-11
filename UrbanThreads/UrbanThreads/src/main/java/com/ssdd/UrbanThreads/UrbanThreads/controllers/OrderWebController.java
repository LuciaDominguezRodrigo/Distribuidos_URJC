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

@Controller
public class OrderWebController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping("/order")
    public String order(){
        return "orderPage";
    }

    @PostMapping("/newProductInOrder")
    public String addToOrder(Model model, @RequestParam("id") int productId,
                                          @RequestParam("selectedSize") String size,
                                          @RequestParam("selectedColor") String color,
                                          @RequestParam("quantity") int quantity){

        Product product = productService.findProduct(productId);
        orderService.addProductToCurrentOrder(product);
        Order products = orderService.findOrder(orderService.getSelectedOrder());
        List<Product> productList = new ArrayList<>();
        for (Product p : products.getOrderProducts()) {
            Product product1 = new Product();
            product1.setName(p.getName());
            product1.setPrice(p.getPrice());
            product1.setSize(p.getAvailableSizes().get(Size.valueOf(size)).toString());
            product1.setColor(color);
            product1.setQuantity(quantity);
            productList.add(product1);
        }

        model.addAttribute("productList", productList);
        return "orderPage";
    }
}

