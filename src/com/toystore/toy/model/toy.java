package com.toystore.toy.model;

public class toy {

    private String toyId;
    private String name;
    private double price;
    private int ageGroup;
    private int stock;

    // Constructor
    public toy(String toyId,
               String name,
               double price,
               int ageGroup,
               int stock) {

        this.toyId = toyId;
        this.name = name;
        this.price = price;
        this.ageGroup = ageGroup;
        this.stock = stock;
    }

    // Getters
    public String getToyId() {

        return toyId;
    }

    public String getName() {

        return name;
    }

    public double getPrice() {

        return price;
    }

    public int getAgeGroup() {

        return ageGroup;
    }

    public int getStock() {

        return stock;
    }

    // Setters
    public void setPrice(double price) {

        this.price = price;
    }

    public void setStock(int stock) {

        this.stock = stock;
    }

    // Convert object to file string
    public String toFileString() {

        return toyId + "," +
                name + "," +
                price + "," +
                ageGroup + "," +
                stock;
    }

    // Convert file string back into object
    public static toy fromFileString(String line) {

        String[] parts = line.split(",");

        if (parts.length < 5) {

            throw new IllegalArgumentException("Invalid toy data");
        }

        return new toy(
                parts[0],
                parts[1],
                Double.parseDouble(parts[2]),
                Integer.parseInt(parts[3]),
                Integer.parseInt(parts[4])
        );
    }

    @Override
    public String toString() {

        return toFileString();
    }
}