package com.toystore.toy.service;

import com.toystore.toy.model.toy;
import com.toystore.toy.util.Node;
import com.toystore.toy.util.ToyLinkedList;
import com.toystore.toy.util.SelectionSort;

import java.io.*;

public class ToyService {

    private ToyLinkedList toyList = new ToyLinkedList();
    private final String FILE_PATH = "data/toys.txt";

    // CREATE
    public void addToy(toy toy) {
        toyList.addToy(toy);
        saveToFile(toy);
    }

    // READ
    public void viewToys() {
        toyList.displayToys();
    }

    // SORT
    public void sortToys() {
        SelectionSort.sortByAgeGroup(toyList);
    }

    // FILE WRITE
    private void saveToFile(toy toy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(toy.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Adding Update
    public void updateToy(String toyId, double newPrice, int newStock) {

        Node current = toyList.getHead();

        while (current != null) {
            if (current.data.getToyId().equals(toyId)) {
                current.data.setPrice(newPrice);
                current.data.setStock(newStock);
                break;
            }
            current = current.next;
        }
    }

    //Adding Delete
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
                return;
            }

            previous = current;
            current = current.next;
        }
    }
}
