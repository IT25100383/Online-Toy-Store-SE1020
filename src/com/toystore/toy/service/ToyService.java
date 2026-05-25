package com.toystore.toy.service;

import com.toystore.toy.model.toy;
import com.toystore.toy.util.Node;
import com.toystore.toy.util.SelectionSort;
import com.toystore.toy.util.ToyLinkedList;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class ToyService {

    private ToyLinkedList toyList = new ToyLinkedList();
    private final String FILE_PATH = "C:/Users/USER/Documents/EDU/Y1/Y1S2/OOP/Project/Toy Store Sandbox/data/toys.txt";

    public ToyService() {
        loadFromFile();
    }

    // CREATE
    public void addToy(toy toy) {
        toyList.addToy(toy);
        saveAllToFile();
    }

    // RETURN ALL TOYS
    public ToyLinkedList getAllToys() {
        return toyList;
    }

    // FILTER TOYS BY CATEGORY
    public List<toy> getToysByCategory(String category) {
        List<toy> filtered = new ArrayList<>();
        Node current = toyList.getHead();

        while (current != null) {
            if (current.data.getCategory().equalsIgnoreCase(category)) {
                filtered.add(current.data);
            }
            current = current.next;
        }
        return filtered;
    }

    // SORT
    public void sortToys() {
        SelectionSort.sortByAgeGroup(toyList);
    }

    // UPDATE
    public void updateToy(String toyId, double newPrice, int newStock) {
        Node current = toyList.getHead();
        while (current != null) {
            if (current.data.getToyId().equals(toyId)) {
                current.data.setPrice(newPrice);
                current.data.setStock(newStock);
                saveAllToFile();
                return;
            }
            current = current.next;
        }
    }

    public List<toy> getToyList() {
        List<toy> list = new ArrayList<>();
        Node current = toyList.getHead();
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
    }

    // DELETE
    public void deleteToy(String toyId) {
        Node current = toyList.getHead();
        Node previous = null;
        while (current != null) {
            if (current.data.getToyId().equals(toyId)) {
                if (previous == null) {
                    toyList.setHead(current.next);
                } else {
                    previous.next = current.next;
                }
                saveAllToFile();
                return;
            }
            previous = current;
            current = current.next;
        }
    }

    // LOW STOCK COUNT
    public int getLowStockCount() {
        int count = 0;
        for (toy t : getToyList()) {
            if (t.getStock() <= 5) {
                count++;
            }
        }
        return count;
    }

    // LOAD DATA FROM FILE
    private void loadFromFile() {
        File file = new File(FILE_PATH);
        System.out.println("Attempting to load toys from: " + file.getAbsolutePath());

        if (!file.exists()) {
            System.out.println("toys.txt NOT FOUND!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                try {
                    toy loadedToy = toy.fromFileString(line);
                    toyList.addToy(loadedToy);
                } catch (Exception e) {
                    System.out.println("FAILED TO PARSE LINE: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // SAVE ENTIRE LIST TO FILE
    private void saveAllToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            Node current = toyList.getHead();
            while (current != null) {
                writer.write(current.data.toFileString());
                writer.newLine();
                current = current.next;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}