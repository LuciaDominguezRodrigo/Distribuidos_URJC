package com.ssdd.UrbanThreads.UrbanThreads.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class InitLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime initializedAt;

    @Column(nullable = false)
    private String description;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getInitializedAt() {
        return initializedAt;
    }

    public void setInitializedAt(LocalDateTime initializedAt) {
        this.initializedAt = initializedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}