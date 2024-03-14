package com.ssdd.UrbanThreads.UrbanThreads.controllers;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Category;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.services.CategoryService;
import com.ssdd.UrbanThreads.UrbanThreads.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Controller
public class CategoryWebController{

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/filter")
    public String filterProductsByCategory(Model model, @RequestParam("selectedFilter") String categoria) {
        categoryService.setCurrentCategoryFilter(categoria);
        return "redirect:/";
    }

    @GetMapping("/editCategory")
    public String showEditCategoriesPage(Model model) {
        Collection<Category> categories = categoryService.getAllCategories();
        List<Category> showC = new ArrayList<>();
        for (Category c: categories){
            if (!Objects.equals(c.getName(), "Sin Categoria")){
                    showC.add(c);
            }
        }
        model.addAttribute("categories", showC);
        return "editCategory";
    }

    @PostMapping("/deleteCategory")
    public String deleteCategoryAndReplace(@RequestParam("name") String categoryName) {

            Category deleteCategory = categoryService.findCategoryByName(categoryName);
            if (deleteCategory != null) {
                Category changeCategory = categoryService.findCategoryByName("Sin Categoria");

                List<Product> productsInCategory = productService.findProductsByCategory(categoryName);

                for (Product product : productsInCategory) {
                    product.setCategory(changeCategory);
                    productService.updateProduct(product.getId(), product);
                }

                categoryService.deleteCategory(deleteCategory.getId());

                return "redirect:/";
            } else {
                return "La categoría especificada no existe.";
            }
    }
    @PostMapping("/createNewCategory")
    public String createCategory(@RequestParam ("newCategoryName") String categoryName, @RequestParam("categoryColor") String color){

    Category newCategory = new Category();

    newCategory.setName(categoryName);
    newCategory.setColor(color);
    categoryService.addNewCategory(newCategory);
    return "redirect:/editCategory";
    }

    @PostMapping("/editOneCategory/{id}")
    public String editCategory(@PathVariable Long id, @RequestParam("editCategoryName") String nombre, @RequestParam("editCategoryColor") String color) {
        Category category = categoryService.findCategory(id);
        if (category != null) {
            category.setName(nombre);
            category.setColor(color);
            categoryService.updateCategory(id, category);
            return "redirect:/editCategory";
        } else {
            // Manejo de error si la categoría no se encuentra
            return "redirect:/editCategory";
        }
    }
}
