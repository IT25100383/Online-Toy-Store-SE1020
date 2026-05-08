package com.toystore.category.model;

public class Category {

    // Encapsulation
    private String id;
    private String name;
    private String description;

    // Constructor
    public Category(String id, String name, String description) {

        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Getters -> used to read data
    public String getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public String getDescription() {

        return description;
    }

    // Setters -> used to update data
    public void setName(String name) {

        this.name = name;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    // Convert object into one line for file storage
    // Example:
    // C01,Toys,Kids toys
    public String toFileString() {

        return id + "," + name + "," + description;
    }

    // Convert one line from file back into a Category object
    public static Category fromFileString(String line) {

        // Split line using comma
        String[] parts = line.split(",");

        // Basic safety check
        if (parts.length < 3) {

            throw new IllegalArgumentException("Invalid category data");
        }

        return new Category(
                parts[0],
                parts[1],
                parts[2]
        );
    }
}