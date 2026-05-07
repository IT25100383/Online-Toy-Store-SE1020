package com.toystore.toy.util;

import com.toystore.toy.model.toy;

public class Node {

    public toy data;
    public Node next;

    public Node(toy data) {
        this.data = data;
        this.next = null;
    }
}