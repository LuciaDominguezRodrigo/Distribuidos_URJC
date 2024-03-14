package com.ssdd.UrbanThreads.UrbanThreads.DTO;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Category;

public class CategoryDTO {

    public String name;
    public String color;

    public CategoryDTO(){}

    public CategoryDTO(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public CategoryDTO(Category category) {
        this.name = category.getName();
        this.color = category.getColor();
    }
}
