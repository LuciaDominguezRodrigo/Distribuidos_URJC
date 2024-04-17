package com.ssdd.UrbanThreads.UrbanThreads.services;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Category;
import com.ssdd.UrbanThreads.UrbanThreads.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<Category> findCategory(Long id){
        return categoryRepository.findById(id);
    }

    public Category findCategoryByName (String name){
        return categoryRepository.findCategoryByName(name);
    }



    public void addNewCategory (Category newC){
        categoryRepository.save(newC);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public void updateCategory(Long id, Category category) {
        category.setId(id);
        categoryRepository.save(category);
    }

    public Collection<Category> getAllCategories() {
       return categoryRepository.findAll();
    }

    public Collection<Category> findAllC() {
            return categoryRepository.findAll();
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }
}
