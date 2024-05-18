package com.ssdd.UrbanThreads.UrbanThreads.controllers;

import com.ssdd.UrbanThreads.UrbanThreads.DTO.CategoryDTO;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Category;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.services.CategoryService;
import com.ssdd.UrbanThreads.UrbanThreads.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryRESTController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable long id) {
        Optional<Category> category = categoryService.findCategory(id);
        if (!category.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        CategoryDTO categoryDTO = new CategoryDTO(category.get());
        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<CategoryDTO>> getCategoryAll() {
       Collection<Category> categories = categoryService.getAllCategories();
       Collection<CategoryDTO> cDTO = new ArrayList<>();
        if (categories== null) {
            return ResponseEntity.notFound().build();
        }
        for (Category c: categories){
            CategoryDTO categoryDTO = new CategoryDTO(c);
            assert false;
            cDTO.add(categoryDTO);

        }
        return ResponseEntity.status(200).body(cDTO);
    }

    @PostMapping("new")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setColor(categoryDTO.getColor());
        category.setDescription(categoryDTO.getDescription());


        Collection<Category> categoriesAvailable = categoryService.getAllCategories();

        boolean categoryName = false;
        for (Category c : categoriesAvailable) {
            if (c.getName().equals(category.getName()))  {
                categoryName = true;
            }
        }

        if (categoryName) {
            return ResponseEntity.status(409).build(); // Category name is taken
        }

        Long id = categoryService.saveCategory(category);
        categoryDTO.setId(id);

        return ResponseEntity.status(201).body(categoryDTO);
    }


    @PutMapping("edit/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable long id, @RequestBody CategoryDTO categoryDTO) {
        Optional<Category> existingCategoryOptional = categoryService.findCategory(id);
        if (existingCategoryOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Category existingCategory = existingCategoryOptional.get();

        if (categoryDTO.getName() != null) {
            existingCategory.setName(categoryDTO.getName());
        }
        if (categoryDTO.getColor() != null) {
            existingCategory.setColor(categoryDTO.getColor());
        }
        if (categoryDTO.getDescription() != null) {
            existingCategory.setDescription(categoryDTO.getDescription());
        }

        categoryService.saveCategory(existingCategory);

        return ResponseEntity.status(202).body(new CategoryDTO(existingCategory));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable long id) {
        Optional<Category> existingCategoryOptional = categoryService.findCategory(id);
        if (existingCategoryOptional.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        Category existingCategory = existingCategoryOptional.get();
        Category sinCategoria = categoryService.findCategoryByName("Sin Categoria");

        if (existingCategory.equals(sinCategoria)) {
            return ResponseEntity.status(403).build(); // Forbidden
        }

        List<Product> productsToDelete = productService.findProductsByCategory(existingCategory.getName());

        for (Product product : productsToDelete) {
            product.setCategory(sinCategoria);
            productService.updateProduct(product.getId(), product);
        }

        categoryService.deleteCategory(id);

        return ResponseEntity.status(200).build();
    }

    @PatchMapping("edit/{id}")
    public ResponseEntity<CategoryDTO> editCategoryP(@PathVariable long id, @RequestBody Category partialCategoryDTO) {
        Optional<Category> existingCategoryOptional = categoryService.findCategory(id);
        if (existingCategoryOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Category existingCategory = existingCategoryOptional.get();

        if (partialCategoryDTO.getName() != null) {
            existingCategory.setName(partialCategoryDTO.getName());
        }
        if (partialCategoryDTO.getColor() != null) {
            existingCategory.setColor(partialCategoryDTO.getColor());
        }

        categoryService.updateCategory(existingCategory.getId(), existingCategory);

        return ResponseEntity.status(200).body(new CategoryDTO(existingCategory));
    }


}


