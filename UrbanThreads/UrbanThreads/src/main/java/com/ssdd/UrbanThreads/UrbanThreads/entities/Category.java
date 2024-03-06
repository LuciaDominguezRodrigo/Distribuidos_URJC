package com.ssdd.UrbanThreads.UrbanThreads.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {

    private Long id;
    private String name;

    public Category(){};

    public Category(Long id,String name) {
        this.id = id;
        this.name = name;
    }


}
