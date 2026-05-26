package com.toystore.toy.util;

import com.toystore.toy.model.toy;

public class ToyLinkedList {

    private Node head;

    // SET HEAD

    public void setHead(Node head) {
        this.head = head;
    }

    // GET HEAD

    public Node getHead() {
        return head;
    }

    // ADD TOY

    public void addToy(toy toy) {

        Node newNode = new Node(toy);

        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }

    // DISPLAY TOYS

    public void displayToys() {

        Node current = head;

        while (current != null) {

            System.out.println(current.data);

            current = current.next;
        }
    }

    // COUNT TOYS

    public int size() {

        int count = 0;

        Node current = head;

        while (current != null) {

            count++;

            current = current.next;
        }

        return count;
    }
}