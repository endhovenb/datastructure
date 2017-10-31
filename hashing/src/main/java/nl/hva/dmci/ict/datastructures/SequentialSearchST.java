/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.dmci.ict.datastructures;

/**
 *
 * @author Bart en Peter
 * @param <Key>
 * @param <Value>
 */
public class SequentialSearchST<Key, Value> {

    private Node first; // first node in the linked list
    private int Collisions = 0; // keeps track of the number of collisions

    private class Node { // linked-list node

        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key) { // Search for key, return associated value.
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val; // search hit
            }
        }
        return null; // search miss
    }

    public void put(Key key, Value val) { // Search for key. Update value if found; grow table if new.
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            } // Search hit: update val.
            Collisions++;
        }
        first = new Node(key, val, first); // Search miss: add new node.
    }

    public int getCollisions() {
        return Collisions;
    }

}
