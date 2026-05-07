package com.toystore.toy.util;

import com.toystore.toy.model.toy;

public class SelectionSort {

    public static void sortByAgeGroup(ToyLinkedList list) {

        Node start = list.getHead();

        while (start != null) {

            Node min = start;
            Node current = start.next;

            while (current != null) {

                if (current.data.getAgeGroup() < min.data.getAgeGroup()) {
                    min = current;
                }

                current = current.next;
            }

            toy temp = start.data;
            start.data = min.data;
            min.data = temp;

            start = start.next;
        }
    }
}
