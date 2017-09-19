/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.bqs;

public class LinkedList<Item> {

    public void add(Item item) {
        Element tail = head;
        head = new Element(item, tail);
    }

    public Item remove() {
        Item result = head.item;
        head = head.next;
        return result;
    }

    public Item getHead() {
        return isEmpty() ? null : head.item ;
    }

    public boolean isEmpty() {
        return head==null;
    }
    private class Element {
        Element(Item x, Element n) {
            item = x;
            next = n;
        }
        final Item item;
        final Element next;
    }

    private Element head = null;
}
