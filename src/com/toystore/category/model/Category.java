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

    // Getters
    public String getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public String getDescription() {

        return description;
    }

    // Setters
    public void setName(String name) {

        this.name = name;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    // Convert object into one line for file storage
    public String toFileString() {

        return id + "," + name + "," + description;
    }

    // Convert one line from file back into a Category object
    public static Category fromFileString(String line) {

        String[] parts = line.split(",");

        return new Category(
                parts[0],
                parts[1],
                parts[2]
        );
    }
}