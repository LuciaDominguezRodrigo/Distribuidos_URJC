package com.ssdd.UrbanThreads.UrbanThreads.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(nullable = false)
    private String name;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;


    @Column (nullable = false)
    private double price;


    @ElementCollection
    @CollectionTable(name = "product_sizes", joinColumns = @JoinColumn(name = "product_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "quantity")
    private Map<Size, Integer> availableSizes;


    private String description;

    @Lob
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Blob photo;

    private boolean deleted;

    @OneToMany(mappedBy = "product")
    private List<OrderedProduct> orderedProducts;


    public Product() {
    }

    // Constructor con parámetros
    public Product(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public Product(String name, Category category, double price, Blob photo, String description, Map<Size, Integer>as) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.photo = photo;
        this.description = description;
        this.availableSizes = as;
    }

    public Product(String name, Category category, double price, String photo, String description, Map<Size, Integer>as) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.setPhoto2(photo);
        this.description = description;
        this.availableSizes = as;
    }



    public void setPhoto(String photo) {
        try {
            byte[] bytes = photo.getBytes();

            InputStream inputStream = new ByteArrayInputStream(bytes);

            this.photo = new SerialBlob(bytes);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void setPhoto2(String photoUrl) {
        try {
            // Crear una URL a partir de la URL de la foto
            URL url = new URL(photoUrl);

            // Abrir una conexión URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Verificar el código de estado de la conexión
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("Error al obtener la imagen. Código de estado: " + responseCode);
            }

            // Obtener la entrada de la conexión URL
            InputStream inputStream = connection.getInputStream();

            // Leer los datos de la imagen en un ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Verificar si se ha leído algún dato
            if (outputStream.size() == 0) {
                throw new IOException("No se han leído datos de la imagen.");
            }

            // Convertir la imagen a un array de bytes
            byte[] photoBytes = outputStream.toByteArray();

            // Crear un objeto Blob a partir de los bytes de la foto
            this.photo = new SerialBlob(photoBytes);

            // Cerrar los streams y la conexión
            outputStream.close();
            inputStream.close();
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.err.println("URL de la imagen incorrecta: " + photoUrl);
        } catch (ProtocolException e) {
            e.printStackTrace();
            System.err.println("Error al establecer el método de solicitud HTTP.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error de E/S al obtener la imagen.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error SQL al crear el Blob.");
        }
    }


    public String getPhotoPath() {
       Blob blob = this.photo;
        try {
            InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            return new String(outputStream.toByteArray(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] getBlobData() {
            try {
                if (photo != null && photo.length() > 0) {
                    return photo.getBytes(1, (int) photo.length());
                } else {
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

    public Blob getPhoto() {
        return this.photo;
    }

    public Long getCategoryId() {
        return this.category.getId();
    }
}


