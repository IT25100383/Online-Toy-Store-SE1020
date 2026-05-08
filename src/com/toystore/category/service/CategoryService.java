package com.toystore.category.service;

import com.toystore.category.model.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {

    // File path where category data is stored
    private static final String FILE_PATH = "data/categories.txt";

    // ADD CATEGORY
    public void addCategory(Category category) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {

            // Save category as one line in the text file
            writer.write(category.toFileString());
            writer.newLine();

            System.out.println("Category added successfully!");

        } catch (IOException e) {

            System.out.println("Error adding category.");
        }
    }

    // VIEW ALL CATEGORIES
    public void viewCategories() {

        List<Category> categories = getAllCategories();

        // Check if category list is empty
        if (categories.isEmpty()) {

            System.out.println("No categories available.");
            return;
        }

        System.out.println("===== CATEGORY LIST =====");

        // Display all categories
        for (Category category : categories) {

            System.out.println(
                    "ID: " + category.getId()
                            + " | Name: " + category.getName()
                            + " | Description: " + category.getDescription()
            );
        }
    }

    // DELETE CATEGORY
    public void deleteCategory(String categoryId) {

        List<Category> categories = getAllCategories();

        boolean found = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {

            for (Category category : categories) {

                // Keep categories that do NOT match the ID
                if (!category.getId().equals(categoryId)) {

                    writer.write(category.toFileString());
                    writer.newLine();

                } else {

                    found = true;
                }
            }

            if (found) {

                System.out.println("Category deleted successfully.");

            } else {

                System.out.println("Category not found.");
            }

        } catch (IOException e) {

            System.out.println("Error deleting category.");
        }
    }

    // READ ALL CATEGORIES FROM FILE
    public List<Category> getAllCategories() {

        List<Category> categories = new ArrayList<>();

        File file = new File(FILE_PATH);

        // If file doesn't exist yet, return empty list
        if (!file.exists()) {

            return categories;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {

            String line;

            while ((line = reader.readLine()) != null) {

                // Convert each line into a Category object
                categories.add(Category.fromFileString(line));
            }

        } catch (IOException e) {

            System.out.println("Error reading categories.");
        }

        return categories;
    }
}