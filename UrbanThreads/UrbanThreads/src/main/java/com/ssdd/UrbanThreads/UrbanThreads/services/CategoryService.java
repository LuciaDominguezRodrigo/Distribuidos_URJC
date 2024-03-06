package com.ssdd.UrbanThreads.UrbanThreads.services;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Category;
import com.ssdd.UrbanThreads.UrbanThreads.repository.CategoryRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findCategory(Long id){
        return categoryRepository.findCategory(id);
    }

    public Collection<Category> findAllCategories () {
        return categoryRepository.findAllCategories();
    }

    public Category saveCategory (@NotNull Category category){
        return categoryRepository.saveCategory(category);
    }

    public Category updateCategory (Long id, Category category){
        return categoryRepository.updateCategory(id,category);
    }

    public void deleteCategory (Long id) { categoryRepository.deleteCategory(id);}


}
