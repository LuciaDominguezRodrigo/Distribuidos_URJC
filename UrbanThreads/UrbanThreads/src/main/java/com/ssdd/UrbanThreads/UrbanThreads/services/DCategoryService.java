package com.ssdd.UrbanThreads.UrbanThreads.services;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Category;
import com.ssdd.UrbanThreads.UrbanThreads.entities.DCategory;
import com.ssdd.UrbanThreads.UrbanThreads.repository.DCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DCategoryService {
    @Autowired
    private DCategoryRepository categoryRepository;

    public Optional<DCategory> findCategory(Long id){
        return categoryRepository.findById(id);
    }

    public DCategory findCategoryByName (String name){
        return categoryRepository.findCategoryByName(name);
    }



    public void addNewCategory (DCategory newC){
        categoryRepository.save(newC);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public void updateCategory(Long id, DCategory category) {
        category.setId(id);
        categoryRepository.save(category);
    }

    public Collection<DCategory> getAllCategories() {
       return categoryRepository.findAll();
    }

    public Collection<DCategory> findAllC() {
            return categoryRepository.findAll();
    }
}
