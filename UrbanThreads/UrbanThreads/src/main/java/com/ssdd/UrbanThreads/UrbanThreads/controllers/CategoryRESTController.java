package com.ssdd.UrbanThreads.UrbanThreads.controllers;

import com.ssdd.UrbanThreads.UrbanThreads.DTO.CategoryDTO;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Category;
import com.ssdd.UrbanThreads.UrbanThreads.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryRESTController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable long id) {
        Category category = categoryService.findCategory(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        CategoryDTO categoryDTO = new CategoryDTO(category);
        return ResponseEntity.status(200).body(categoryDTO);
    }

    @PostMapping("new")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setColor(categoryDTO.getColor());

        categoryService.saveCategory(category);


        return ResponseEntity.status(201).body(categoryDTO);
    }


    @PutMapping("edit/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable long id, @RequestBody CategoryDTO categoryDTO) {
        Category existingCategory = categoryService.findCategory(id);
        if (existingCategory == null) {
            return ResponseEntity.notFound().build();
        }

        if (categoryDTO.getName() != null) {
            existingCategory.setName(categoryDTO.getName());
        }

        if (categoryDTO.getColor() != null) {
            existingCategory.setColor(categoryDTO.getColor());
        }

        categoryService.saveCategory(existingCategory);

        return ResponseEntity.status(202).body(new CategoryDTO(existingCategory));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable long id) {
        Category existingCategory = categoryService.findCategory(id);
        if (existingCategory == null) {
            return ResponseEntity.status(404).build();
        }

        categoryService.deleteCategory(id);

        return ResponseEntity.status(200).build();
    }
}


