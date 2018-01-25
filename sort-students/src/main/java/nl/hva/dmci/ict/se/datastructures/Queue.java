/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.dmci.ict.se.datastructures;

import java.util.Iterator;

/**
 *
 * @author Bart
 */
public class Queue<T extends Comparable<T>> implements Iterable<T> {

    private Node leftMost;
    private Node rightMost;
    private int N = 0;

    public boolean isEmpty() {
        return N == 0;
    }

    /**
     *
     * @return queue lenth as Int
     */
    public int size() {
        return N;
    }

    /**
     * add an item to the que
     * @param item
     */
    public void add(T item) {
        Node newNode = new Node(item);

        if (this.isEmpty()) {
            leftMost = newNode;
            rightMost = newNode;
            newNode.prev = null;
            newNode.next = null;
        } else {
            bucketSort(newNode, leftMost, null);
        }
        N++;
    }

    public Node remove() {
        if (isEmpty()) {
            return null;
        }
        if (size() == 1) {
            rightMost = null;
        }
        Node temp = leftMost;
        leftMost = temp.next;
        Node node = temp;
        N--;
        return node;
    }

    private class Node<Item> {
        Item item;
        Node next;
        Node prev;

        /**
         * Nieuwe Node wordt toegevoegd.
         *
         * @param item De waarde die de Node moet opslaan.
         */
        private Node(Item item) {
            this.item = item;
        }

        /**
         * @param item Wijzig de data van het item van een Node.
         */
        public void setItem(Item item) {
            this.item = item;
        }

        /**
         * @return Item van een Node.
         */
        public Item getItem() {
            return item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> VolgendeNode = leftMost;

            @Override
            public boolean hasNext() {
                return (VolgendeNode != null);
            }

            @Override
            public T next() {
                T item = VolgendeNode.getItem();
                VolgendeNode = VolgendeNode.next;
                return item;
            }
        };
    }

    /**
     *
     * @param node
     * @param startNode
     * @param prevNode
     */
    public void bucketSort(Node node, Node startNode, Node prevNode) {
        if (node.getItem() instanceof Student) {
            Student student = (Student) node.getItem();
            if (student.compare((Student) startNode.getItem()) > 0) {
                if (startNode.next != null) {
                    bucketSort(node, startNode.next, startNode);
                } else {
                    startNode.next = node;
                    leftMost = node;
                }
            } else {
                if (prevNode != null) {
                    prevNode.next = node;
                    node.next = startNode;
                } else {
                    leftMost = node;
                    leftMost.next = startNode;
                }
            }
        } else if (node.getItem() instanceof Bucket) {
            Bucket bucket = (Bucket) node.getItem();
            if (bucket.compareTo((Bucket) startNode.getItem()) > 0) {
                if (startNode.next != null) {
                    bucketSort(node, startNode.next, startNode);
                } else {
                    startNode.next = node;
                    rightMost = node;
                }
            } else {
                if (prevNode != null) {
                    prevNode.next = node;
                    node.next = startNode;
                } else {
                    leftMost = node;
                    leftMost.next = startNode;
                }
            }
        }
    }
}

