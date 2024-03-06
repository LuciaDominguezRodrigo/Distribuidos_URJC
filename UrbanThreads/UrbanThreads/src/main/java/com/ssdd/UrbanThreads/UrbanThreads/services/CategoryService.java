package com.ssdd.UrbanThreads.UrbanThreads.services;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Category;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class CategoryService {
    private final Map<Long, Category> categories = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong();


    public Category getOrder (Long id){
        return categories.get(id);
    }

    public Category createPorduct(@NotNull Category category){
        long id = nextId.incrementAndGet();
        category.setId(id);
        categories.put(id,category);
        return category;

    }

    public Collection<Category> getAllProducts() {
        return categories.values();
    }

    public Category updateProduct (Long id, Category category){
        if (!categories.containsKey(id)) {
            return null;
        }
        category.setId(id);
        categories.put(id,category);
        return category;
    }


    public void deleteCategory(Long id) { categories.remove(id);}


}
