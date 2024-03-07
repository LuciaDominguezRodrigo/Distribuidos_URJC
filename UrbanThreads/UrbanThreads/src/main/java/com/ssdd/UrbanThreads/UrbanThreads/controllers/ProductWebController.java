package com.ssdd.UrbanThreads.UrbanThreads.controllers;


import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ProductWebController {

    @Autowired
    private ProductService productService;

    private int nextProductIndex = 3;
    private int productsRefreshSize = 4; //this number controls how many elements are charged
                                         //with 'cargar más'button


    @GetMapping("/")
    public String index(Model model) {
        nextProductIndex = productsRefreshSize;
        List<Product> products = productService.findByIdRange(0, nextProductIndex-1);

        if (products.isEmpty()){
            model.addAttribute("products", new ArrayList<Product>());
        }
        else {
            model.addAttribute("products", products);
        }
        nextProductIndex = products.size();
        return "index";
    }


    @GetMapping("/newProducts")
    public String newEvents(Model model) {
        List<Product> products = productService.findByIdRange(nextProductIndex, (nextProductIndex+productsRefreshSize)-1);
        nextProductIndex += products.size();
        model.addAttribute("additionalProducts", products);
        if(nextProductIndex > productService.findAllProducts().size()){ //To show / hide Load more products button
            model.addAttribute("loadMoreProducts", false);
        } else{
            model.addAttribute("loadMoreProducts", true);
        }

        return "moreProducts";
    }

    @GetMapping("/product/{id}")
    public String showProduct(Model model, @PathVariable int id) {
        Product product = productService.findProduct(id);
        model.addAttribute("product", product);
        int availableUnits = product.getAvailableUnits();
        model.addAttribute("availableUnits", availableUnits);
        if(availableUnits != 0){
            model.addAttribute("availability", "En stock");
            model.addAttribute("stockState", "success");
        }
        else{
            model.addAttribute("availability", "No disponible en stock");
            model.addAttribute("stockState", "danger");
        }

        return "productDetails";
    }

    @GetMapping("/createProduct")
    public String newProductCharge(){
        return "createForm";
    }

    @PostMapping("/createProduct")
    public String newProduct(@RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("price") double price,
                             @RequestParam("numberP") int numberP,
                             @RequestParam ("photo") String photo) {


        Product newProduct = new Product(name,null,null,price,photo,description,numberP);

        //implementar
        return "index";
    }

}
