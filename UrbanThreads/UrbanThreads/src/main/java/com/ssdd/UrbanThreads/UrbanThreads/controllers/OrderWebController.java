package com.ssdd.UrbanThreads.UrbanThreads.controllers;

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
        if(product.getAvailableSizes().get(Size.valueOf(size)) <= quantity){
            orderService.addProductToCurrentOrder(product);
        } else {
            //Throw a message indicating product is not available (extra checking apart of Mustache "Add to cart" button hiding)
            return "redirect:/";
        }

        model.addAttribute("order", orderService.findOrder(orderService.getSelectedOrder()));
        return "orderPage";
    }
}

