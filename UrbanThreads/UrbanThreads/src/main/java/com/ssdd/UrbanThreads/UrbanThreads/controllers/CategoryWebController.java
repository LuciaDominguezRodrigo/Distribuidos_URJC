package com.ssdd.UrbanThreads.UrbanThreads.controllers;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Category;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.services.CategoryService;
import com.ssdd.UrbanThreads.UrbanThreads.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/deleteCategory")
    public String deleteCategoryAndReplace(@RequestParam("name") String categoryName) {
        // Buscar la categoría a eliminar
        Category deleteCategory = categoryService.findCategoryByName(categoryName);

        // Si la categoría a eliminar existe
        if (deleteCategory != null) {
            // Obtener la categoría "Sin categoría"
            Category changeCategory = categoryService.findCategoryByName("Sin categoria");

            // Obtener todos los productos que pertenecen a la categoría a eliminar
            List<Product> productsInCategory = productService.findProductsByCategory(categoryName);

            // Iterar sobre los productos y reemplazar la categoría
            for (Product product : productsInCategory) {
                product.setCategory(changeCategory);
                productService.updateProduct(product.getId(), product);
            }

            // Finalmente, eliminar la categoría
            categoryService.deleteCategory(deleteCategory.getId());

            return "redirect:/";
        } else {
            return "La categoría especificada no existe.";
        }
    }


}
