/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.dmci.ict.se.datastructures;

/**
 *
 * @author Bart
 */
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedLinkedList<ELEMENT> implements Iterable<ELEMENT> {

    private Node<ELEMENT> head = null;
    private Node<ELEMENT> tail = null;
    private int size = 0;
    private Comparator<ELEMENT> sorter;

    /**
     * Construct a sorted linkedlist. This uses the given comparator to
     * implement sorting.
     *
     * @param sorter a {@link Comparator} implementation used to sort the list
     * with.
     */
    public SortedLinkedList(Comparator<ELEMENT> sorter) {
        this.sorter = sorter;
    }

    /**
     * Method used to simplify comparisons on {@code Node#getValue} The Node
     * their values will be compared by the set {@code sorter}
     *
     * @param node the first node to compare the value of.
     * @param node1 the second node to compare the value of.
     * @return the comparison result of node's value and node1's value.
     */
    private int compare(Node<ELEMENT> node, Node<ELEMENT> node1) {
        return sorter.compare(node.getValue(), node1.getValue());
    }

    /**
     * Method used to easily insert a {@code Node} after one another.
     *
     * @param nodeToInsertAfter the {@code Node} which will be inserted after.
     * @param newNode the {@code Node} to insert.
     */
    private void insertAfter(Node nodeToInsertAfter, Node newNode) {
        Node<ELEMENT> iterationNode;
//Some logic to swap and insert inbetween nodes.
//We have to correctly set all next and previous attributes inside of the Nodes involved.
        if (nodeToInsertAfter == null) {
//Set the head.
            head.setPrevious(newNode);
            iterationNode = this.head;
            head = newNode;
            head.setNext(iterationNode);
            if (size <= 1) {
                tail = iterationNode;
                tail.setNext(null);
            }
        } else if (nodeToInsertAfter.getNext() == null) {
//When we're inserted at the last position in de list also set the tail.
            newNode.setPrevious(nodeToInsertAfter);
            nodeToInsertAfter.setNext(newNode);
            tail = newNode;
        } else {
//Just place it after the insertion node.
            Node<ELEMENT> previous = nodeToInsertAfter;
            Node<ELEMENT> next = nodeToInsertAfter.getNext();
            previous.setNext(newNode);
            newNode.setPrevious(previous);
            newNode.setNext(next);
            next.setPrevious(newNode);
        }
        this.size++;
    }

    /**
     * Find the Node in the current list prior to the given new Node. The prior
     * node is found through comparing all the current Node values. These are
     * compared with the new Node value and thus we can find our closest match
     * to the left. The closest match to the left is chosen because we will be
     * inserting our new Node after the prior Node.
     *
     * @param newNode the {@code Node} to find the closest(leftside) Node with.
     * @return a Node which is the closest to the {@code newNode} from the left
     * side(negative).
     */
    private Node<ELEMENT> findPriorNode(Node<ELEMENT> newNode) {
        Node<ELEMENT> curNode = head;
        if (compare(newNode, curNode) < 0) {
            return null;
        }
        while (curNode.getNext() != null) {
            if (compare(newNode, curNode) == 0) {
                return curNode;
            } else if (compare(newNode, curNode) > 0 && compare(newNode,
                    curNode.getNext()) < 0) {
                return curNode;
            }
            curNode = curNode.getNext();
        }
        return curNode;
    }

    /**
     * Insert a new element and put it sorted into the current linked list.
     *
     * @param value the new element to insert.
     */
    public void add(ELEMENT value) {
        Node<ELEMENT> newNode = new Node(value);
        Node<ELEMENT> nodeToInsertAfter;
        if (head == null) {
            head = newNode;
            head.setNext(tail);
            size++;
        } else {
            nodeToInsertAfter = findPriorNode(newNode);
            if (nodeToInsertAfter == null || compare(nodeToInsertAfter, newNode) != 0) {
                insertAfter(nodeToInsertAfter, newNode);
            }
        }
    }

    /**
     * @return Simpele iterator implementatie. Deze is gekopieerd uit opdracht
     * 1C(Deque).
     */
    @Override
    public Iterator<ELEMENT> iterator() {
        return new Iterator<ELEMENT>() {
//huidige {@code Node} waar we zijn.
            private Node<ELEMENT> next = head;

            /**
             * Check of er nog {@code Node} objecten volgen.
             *
             * @return
             */
            @Override
            public boolean hasNext() {
                return next != null;

            }

            /**
             * Verkrijgt de volgende {@code Node} value. Vervolgens wordt de
             * volgende {@code Node} al klaar gezet.
             *
             * @return
             */
            @Override
            public ELEMENT next() {
                if (next == null) {
                    throw new NoSuchElementException("No next element found.");
                }
                ELEMENT val = next.getValue();
                next = next.getNext();
                return val;
            }
        };

    }

    /**
     * Represents a single element inside of the sorted linkedlist.
     *
     * @param <T>
     */
    private class Node<T> {

        /**
         * The next element/node inside of the list.
         */
        private Node<T> next;
        private Node<T> previous;
        /**
         * Value of the node which is basically the added element.
         */
        private T value;

        /**
         * Constructs a new Node.
         *
         * @param next the next {@code Node} in the list.
         * @param previous the {@code Node} before this one.
         * @param value the actual element that was added.
         */
        public Node(Node<T> next, Node<T> previous, T value) {
            this(previous, value);
            this.next = next;
        }

        /**
         * Constructs a new Node.
         *
         * @param previous the {@code Node} before this one.
         * @param value the actual element that was added.
         */
        public Node(Node<T> previous, T value) {
            this(value);
            this.previous = previous;
        }

        /**
         * Constructs a new Node without setting a next {@code Node}
         *
         * @param value the actual element that was added.
         */
        public Node(T value) {

            this.value = value;
        }

        /**
         * Gets the next {@code Node} in the list.
         *
         * @return next {@code Node} but null if this is currently the last.
         */
        public Node<T> getNext() {
            return next;

        }

        /**
         * Set the next {@code Node}
         *
         * @param next the {@code Node} which should be after the current Node.
         */
        public void setNext(Node<T> next) {
            this.next = next;
        }

        /**
         * @return {@code Node} value which is the element that was added.
         */
        public T getValue() {
            return value;
        }

        /**
         * Sets the {@code Node} value.
         *
         * @param value the element to replace the current one with.
         */
        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<T> previous) {
            this.previous = previous;
        }
    }
}
