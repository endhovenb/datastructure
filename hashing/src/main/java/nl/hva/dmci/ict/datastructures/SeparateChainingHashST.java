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
public class SeparateChainingHashST<Key, Value> {

    private int N; // number of key-value pairs
    private final int M = 13; // hash table size
    private int Collisions = 0; // keeps track of the number of collisions
    private final SequentialSearchST<Key, Value>[] st; // array of ST objects
    
    public SeparateChainingHashST() { // Create M linked lists.
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST();
        }
    }

    public int getCollisions() {
        return Collisions;
    }

    public int getM() {
        return M;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value val) {
        Collisions = Collisions + st[hash(key)].put(key, val);
    }

    public Iterable<Key> keys() {
// See Exercise 3.4.19.
        return null;
    }
}
