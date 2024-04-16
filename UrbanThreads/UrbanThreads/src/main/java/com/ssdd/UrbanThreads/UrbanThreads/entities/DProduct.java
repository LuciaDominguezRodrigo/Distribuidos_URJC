package com.ssdd.UrbanThreads.UrbanThreads.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "products")
public class DProduct {
    @Getter
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @Getter
    @Column(name = "name")
    private String name;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "category_id")
    private DCategory category;

    @Setter
    @Getter
    @Column (name = "price")
    private double price;

    @Lob
    @Column (name = "photo")
    private Blob photo;

    @Setter
    @Getter
    @Column (name = "description")
    private String description;


    /*@Column (name ="availableSizes")
    private Map<Size,Integer> availableSizes;*/
    public DProduct() {
    }

    // Constructor con parámetros
    public DProduct(String name, DCategory category) {
        this.name = name;
        this.category = category;
    }

    @Setter
    @Getter
    @ElementCollection
    @CollectionTable(name = "product_sizes", joinColumns = @JoinColumn(name = "product_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "quantity")
    private Map<Size, Integer> availableSizes;

    public DProduct (String name, DCategory category, double price,Blob photo,String description, Map<Size, Integer>as) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.photo = photo;
        this.description = description;
        this.availableSizes = as;
    }

    public DProduct (String name, DCategory category, double price,String photo,String description, Map<Size, Integer>as) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.setPhoto(photo);
        this.description = description;
        this.availableSizes = as;
    }

    public void setPhoto(String photo) {
        try {
            // Convertir el String a un arreglo de bytes
            byte[] bytes = photo.getBytes();

            // Crear un objeto InputStream desde el arreglo de bytes
            InputStream inputStream = new ByteArrayInputStream(bytes);

            // Crear un objeto Blob a partir del InputStream
            this.photo = new SerialBlob(bytes);
        } catch (SQLException ex) {
            // Manejar la excepción
            ex.printStackTrace();
        }
    }
}

