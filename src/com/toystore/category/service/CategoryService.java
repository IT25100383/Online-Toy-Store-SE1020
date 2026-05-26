package com.toystore.category.service;

import com.toystore.category.model.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {

    private static final String FILE_PATH = "C:/Users/USER/Documents/EDU/Y1/Y1S2/OOP/Project/Toy Store Sandbox/data/categories.txt";

    public void addCategory(Category category) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(category.toFileString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error adding category.");
        }
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return categories;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                categories.add(Category.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("Error reading categories.");
        }
        return categories;
    }

    public void updateCategory(Category updatedCategory) {
        List<Category> categories = getAllCategories();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Category category : categories) {
                if (category.getId().equals(updatedCategory.getId())) {
                    writer.write(updatedCategory.toFileString());
                } else {
                    writer.write(category.toFileString());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating category.");
        }
    }

    public void deleteCategory(String categoryId) {
        List<Category> categories = getAllCategories();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Category category : categories) {
                if (!category.getId().equals(categoryId)) {
                    writer.write(category.toFileString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error deleting category.");
        }
    }
}