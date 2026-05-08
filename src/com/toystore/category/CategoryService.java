package com.toystore.category.service;

import com.toystore.category.model.Category;
import com.toystore.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {

    // CREATE → Add a new category to file
    public void addCategory(Category category) {

        // Read all existing data from file
        List<String> lines = FileUtil.readFile();

        // Convert the object to string and add it
        lines.add(category.toFileString());

        // Save everything back to file
        FileUtil.writeFile(lines);

        System.out.println("Category added successfully!");
    }

    // READ → Show all categories
    public void viewCategories() {

        List<String> lines = FileUtil.readFile();

        // If file is empty
        if (lines.isEmpty()) {
            System.out.println("No categories available.");
            return;
        }

        System.out.println("Category List:");

        // Loop through each line in file
        for (String line : lines) {

            try {
                // Convert line → Category object
                Category c = Category.fromFileString(line);

                // Display basic details
                System.out.println("ID: " + c.getId() + " | Name: " + c.getName());

            } catch (Exception e) {
                // If something is wrong with data, skip it
                System.out.println("Invalid data skipped");
            }
        }
    }

    // DELETE → Remove a category using ID
    public void deleteCategory(String id) {

        List<String> lines = FileUtil.readFile();
        List<String> updated = new ArrayList<>();

        boolean found = false;

        // Go through each line
        for (String line : lines) {

            Category c = Category.fromFileString(line);

            // Keep all categories except the one we want to delete
            if (!c.getId().equals(id)) {
                updated.add(line);
            } else {
                found = true;
            }
        }

        // Save updated list back to file
        FileUtil.writeFile(updated);

        if (found) {
            System.out.println("Category deleted.");
        } else {
            System.out.println("Category not found.");
        }
    }
}
