package com.ssdd.UrbanThreads.UrbanThreads.DTO;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {

    public String name;
    public String color;
    public String description;
    public Long id;

    public CategoryDTO(){}

    public CategoryDTO(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public CategoryDTO(Category category) {
        this.name = category.getName();
        this.color = category.getColor();
        this.description = category.getDescription();
        this.id = category.getId();
    }
}
