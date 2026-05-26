package com.toystore.toy.model;

import java.io.Serializable;

public class toy implements Serializable {

    private String toyId;
    private String name;
    private String category;
    private double price;
    private int ageGroup;
    private int stock;

    public toy(String toyId, String name, String category, double price, int ageGroup, int stock) {
        this.toyId = toyId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.ageGroup = ageGroup;
        this.stock = stock;
    }

    // GETTERS
    public String getToyId() { return toyId; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getAgeGroup() { return ageGroup; }
    public int getStock() { return stock; }

    // SETTERS
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
    public void setCategory(String category) { this.category = category; }

    // FILE STORAGE
    public String toFileString() {
        return toyId + "," + name + "," + category + "," + price + "," + ageGroup + "," + stock;
    }

    public static toy fromFileString(String line) {
        String[] parts = line.split(",");

        if (parts.length < 6) {
            throw new IllegalArgumentException("Invalid toy data");
        }

        return new toy(
                parts[0],
                parts[1],
                parts[2],
                Double.parseDouble(parts[3]),
                Integer.parseInt(parts[4]),
                Integer.parseInt(parts[5])
        );
    }
}