package com.ssdd.UrbanThreads.UrbanThreads.repository;


import com.ssdd.UrbanThreads.UrbanThreads.entities.DCategory;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DCategoryRepository extends JpaRepository<DCategory, Long> {
    @NotNull Optional<DCategory> findById(Long id);

    void deleteById(Long id);


    DCategory findCategoryByName(String name);
}
