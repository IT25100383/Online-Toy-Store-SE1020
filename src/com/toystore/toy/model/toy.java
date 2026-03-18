package com.toystore.toy.model;

public class Toy {

    private String toyId;
    private String name;
    private double price;
    private int ageGroup;
    private int stock;

    public Toy(String toyId, String name, double price, int ageGroup, int stock) {
        this.toyId = toyId;
        this.name = name;
        this.price = price;
        this.ageGroup = ageGroup;
        this.stock = stock;
    }

    public String getToyId() { return toyId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getAgeGroup() { return ageGroup; }
    public int getStock() { return stock; }

    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return toyId + "," + name + "," + price + "," + ageGroup + "," + stock;
    }
}
