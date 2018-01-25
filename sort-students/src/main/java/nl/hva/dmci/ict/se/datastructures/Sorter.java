/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.dmci.ict.se.datastructures;

import java.util.Comparator;

/**
 *
 * @author Bart
 */
public interface Sorter<T> extends Comparator<T> {

    /**
     * Sorteer een array.
     *
     * @param unsorted ongesorteerde array.
     * @return de gesorteerde array.
     */
    T[] sort(T[] unsorted);
}
