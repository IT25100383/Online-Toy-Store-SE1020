package com.toystore;

import com.toystore.toy.model.toy;
import com.toystore.toy.service.ToyService;
import com.toystore.toy.util.Node;
import com.toystore.toy.util.ToyLinkedList;

public class MainTest {

    public static void main(String[] args) {
        ToyService service = new ToyService();

        // CREATE - Updated with Category as the 3rd parameter
        service.addToy(new toy("T01", "Toy Car", "Vehicles", 10.5, 5, 20));
        service.addToy(new toy("T02", "Teddy Bear", "Plushies", 12.0, 3, 15));
        service.addToy(new toy("T03", "Lego Set", "Construction", 25.0, 8, 10));

        // READ
        System.out.println("Before Sorting:");
        displayToys(service.getAllToys());

        // SORT
        service.sortToys();

        // UPDATE
        service.updateToy("T01", 15.0, 30);

        // DELETE
        service.deleteToy("T02");

        System.out.println("\nAfter Update & Delete:");
        displayToys(service.getAllToys());
    }

    // DISPLAY METHOD
    private static void displayToys(ToyLinkedList toyList) {
        Node current = toyList.getHead();
        while (current != null) {
            toy t = current.data;
            System.out.println(
                    t.getToyId() + " | " +
                            t.getName() + " | " +
                            t.getCategory() + " | " +
                            t.getPrice() + " | " +
                            t.getAgeGroup() + " | " +
                            t.getStock()
            );
            current = current.next;
        }
    }
}