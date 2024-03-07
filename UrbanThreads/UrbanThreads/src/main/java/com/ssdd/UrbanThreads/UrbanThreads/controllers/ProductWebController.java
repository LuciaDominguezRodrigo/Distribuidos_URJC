package com.ssdd.UrbanThreads.UrbanThreads.controllers;


import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Size;
import com.ssdd.UrbanThreads.UrbanThreads.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        model.addAttribute("availableSizes", product.getAvailableSizes());
        model.addAttribute("sizeXS", product.getAvailableSizes().get(Size.XS));
        model.addAttribute("sizeS", product.getAvailableSizes().get(Size.S));
        model.addAttribute("sizeM", product.getAvailableSizes().get(Size.M));
        model.addAttribute("sizeL", product.getAvailableSizes().get(Size.L));
        model.addAttribute("sizeXL", product.getAvailableSizes().get(Size.XL));
        model.addAttribute("sizeXXL", product.getAvailableSizes().get(Size.XXL));

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
                             @RequestParam("sizeXS") int xsUnits,
                             @RequestParam("sizeS") int sUnits,
                             @RequestParam("sizeM") int mUnits,
                             @RequestParam("sizeL") int lUnits,
                             @RequestParam("sizeXL") int xlUnits,
                             @RequestParam("sizeXXL") int xxlUnits,
                             @RequestParam ("photo") String photo) {

        Map<Size, Integer> availableSizes = new HashMap<>();
        availableSizes.put(Size.XS, xsUnits);
        availableSizes.put(Size.S, sUnits);
        availableSizes.put(Size.M, mUnits);
        availableSizes.put(Size.L, lUnits);
        availableSizes.put(Size.XL, xlUnits);
        availableSizes.put(Size.XXL, xxlUnits);

        Product newProduct = new Product(name, null, price, photo, description, availableSizes);
        productService.saveProduct(newProduct);

        return "redirect:/";    
    }

}
