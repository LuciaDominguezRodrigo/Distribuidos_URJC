package com.ssdd.UrbanThreads.UrbanThreads.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {

    private long id;
    private String name;
    private String color;
    private String description;

    public Category(){};

    public Category(long id,String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public Category(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Category(String name, String color,String descripcion) {
        this.name = name;
        this.color = color;
        this.description = descripcion;
    }

}
