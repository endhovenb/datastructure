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
public class LinearProbingHashST<Key, Value> {

    private int N;          // number of key-value pairs in the table
    private int M = 13;     // size of linear-probing table
    private Key[] keys;     // the keys
    private Value[] vals;   // the values

    public LinearProbingHashST() {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize() {
        // See page 474.
    }

    public void put(Key key, Value val) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    /**
     *
     * @param key 
     * @return
     */
    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }
}
