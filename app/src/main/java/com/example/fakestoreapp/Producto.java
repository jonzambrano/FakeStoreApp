package com.example.fakestoreapp; // Asegúrate que este sea tu paquete

import android.media.Rating;

public class Producto {

    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private Rating rating; // Aquí usamos la clase Rating que creamos antes

    // Getters (opcional, pero buena práctica)
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public Rating getRating() {
        return rating;
    }
}