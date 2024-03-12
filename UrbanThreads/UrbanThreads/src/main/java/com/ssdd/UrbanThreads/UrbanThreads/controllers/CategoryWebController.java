package com.ssdd.UrbanThreads.UrbanThreads.controllers;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Category;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.services.CategoryService;
import com.ssdd.UrbanThreads.UrbanThreads.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

@Controller
public class CategoryWebController{

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

@GetMapping("/productosPorCategoria")
public String productosPorCategoria(Model model, @RequestParam("categoria") String categoria) {
    List<Product> productsByCategory = productService.findProductsByCategory(categoria);
    model.addAttribute("products", productsByCategory);
    return "index";
   }

    @GetMapping("/editCategory")
    public String showEditCategoriesPage(Model model) {
        Collection<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "editCategory";
    }
}
