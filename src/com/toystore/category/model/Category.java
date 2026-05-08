package com.toystore.category.model;

public class Category {
    //These are the data fields for a category
    private String id;
    private String name;
    private String description;

    // This constructor runs when we create a new Category object
    public Category(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // These are getters -> used to READ data
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // These are setters -> used to UPDATE data
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // This converts the object into a single line to save in a text file
    // Example: C01|Toys|Kids toys
    public String toFileString() {
        return id + "," + name + "," + description;
    }
    // This does the opposite → takes a line from the file and turns it back into an object
    public static Category fromFileString(String line) {
    // Split the line using "|" symbol
        String[] parts = line.split("\\|");

        // Basic safety check
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid category data");
        }

        // Create and return a Category object using the split data
        return new Category(parts[0], parts[1], parts[2]);
    }
}
