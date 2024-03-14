package com.ssdd.UrbanThreads.UrbanThreads.repository;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Category;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CategoryRepository {
    private final Map<Long, Category> categories = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong(0);


    public Category findCategory(Long id){
        return categories.get(id);
    }

    public Category saveCategory(@NotNull Category category){
        long id = nextId.incrementAndGet();
        category.setId(id);
        categories.put(id,category);
        return category;

    }

    public List<Category> findAllCategories() {
        List<Category> allCategories = new ArrayList<>();
        allCategories.addAll(categories.values());
        return allCategories;
    }

    public Category updateCategory(Long id, Category category){
        if (!categories.containsKey(id)) {
            return null;
        }
        category.setId(id);
        categories.put(id,category);
        return category;
    }

    public void deleteCategory(Long id) {
        categories.remove(id);
    }

    public Category findCategoryByName(String categoryName) {
        for (Category category : categories.values()) {
            if (category.getName().equals(categoryName)) {
                return category;
            }
        }
        return null;
    }

    public void addCategory(Category newCategory) {
        long id = nextId.incrementAndGet();
        newCategory.setId(id);
        categories.put(id, newCategory);

    }
}
