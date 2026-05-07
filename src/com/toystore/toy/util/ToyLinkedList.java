package com.toystore.toy.util;

import com.toystore.toy.model.toy;

public class ToyLinkedList {

    private Node head;

    public void setHead(Node head) {
        this.head = head;
    }

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

    public void displayToys() {

        Node current = head;

        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public Node getHead() {
        return head;
    }
}
