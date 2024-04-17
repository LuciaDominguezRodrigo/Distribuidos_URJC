package com.ssdd.UrbanThreads.UrbanThreads.repository;


import com.ssdd.UrbanThreads.UrbanThreads.entities.Category;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @NotNull Optional<Category> findById(Long id);

    void deleteById(Long id);


    Category findCategoryByName(String name);
}
