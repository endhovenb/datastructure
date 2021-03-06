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
public class QuadraticHashST<Key, Value> {
    private int N;          // number of key-value pairs in the table
    private final int M = 15401;  // size of linear-probing table
    private final Key[] keys;     // the keys
    private final Value[] vals;   // the values
    private long Collisions = 0; // keeps track of the number of collisions

    public QuadraticHashST() {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key, Value val) {
        int i;
        int j = 1;
        for (i = hash(key); keys[i] != null; i = (i+(j*j)) % M) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;             
            }
            Collisions++;
            j++;
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public long getCollisions() {
        return Collisions;
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public Value get(Key key) {
        int j = 1;
        for (int i = hash(key); keys[i] != null; i = (i + (j*j)) % M) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
            j++;
        }
        return null;
    }
}
