package com.toystore.category;

public class CategoryService {package toystore.service;

import toystore.model.Category;
import toystore.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

    public void addCategory(Category category) {
        List<String> lines = FileUtil.readFile();
        lines.add(category.toString());
        FileUtil.writeFile(lines);

        System.out.println("Category added successfully!");
    }

    public void viewCategories() {
        List<String> lines = FileUtil.readFile();

        if (lines.isEmpty()) {
            System.out.println("No categories available.");
            return;
        }

        System.out.println("Category List:");

        for (String line : lines) {
            String[] data = line.split(",");
            System.out.println("ID: " + data[0] + " | Name: " + data[1]);
        }
    }

    public void deleteCategory(int id) {

        List<String> lines = FileUtil.readFile();
        List<String> updated = new ArrayList<>();

        boolean found = false;

        for (String line : lines) {

            String[] data = line.split(",");
            int categoryId = Integer.parseInt(data[0]);

            if (categoryId != id) {
                updated.add(line);
            } else {
                found = true;
            }
        }

        FileUtil.writeFile(updated);

        if (found) {
            System.out.println("Category deleted.");
        } else {
            System.out.println("Category not found.");
        }
    }


}

}