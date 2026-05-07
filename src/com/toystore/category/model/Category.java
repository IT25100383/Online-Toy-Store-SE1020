package com.toystore.category.model;

public class Category {
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

    // Convert object to file format
    public String toFileString() {
        return id + "," + name + "," + description;
    }
}
}
