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

    private List<Product> productList = new ArrayList<>();


    @PostMapping("/newProductInOrder")
    public String addToOrder(Model model, @RequestParam("id") int productId,
                             @RequestParam("selectedSize") String size,
                             @RequestParam("selectedColor") String color,
                             @RequestParam("quantity") int quantity) {

        Product product = productService.findProduct(productId);
        orderService.addProductToCurrentOrder(product);


        Product product1 = new Product();
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setSize(size);
        product1.setColor(color);
        product1.setQuantity(quantity);
        productList.add(product1);


        model.addAttribute("productList", productList);
       // model.addAttribute("productIds", productIds);

        return "redirect:/orderPage";
    }


    @GetMapping("/orderPage")
    public String showOrderPage(Model model) {
        model.addAttribute("productList", productList);
        return "orderPage";
    }

    /*
    @PostMapping("/deleteProductOrder")
    public String eliminarProductoDeOrden(Model model, @RequestParam("productId") int productId) {

        productList.remove(orderService.deletePorductfromCurrentOrder(productId));
        model.addAttribute("productList", productList);

        return "redirect:/orderPage";
    }*/

    @PostMapping ("/cancelOrder")
    public String cancelOrder(){
        productList.clear();
        orderService.deleteOrderT();
        return "redirect:/";
    }

    @PostMapping ("/orderReady")
    public String makeOrder(Model model){
        model.addAttribute("productList", productList);
        return "orderMade";
    }
}

