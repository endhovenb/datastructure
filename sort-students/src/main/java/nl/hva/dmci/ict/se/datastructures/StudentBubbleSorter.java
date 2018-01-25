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
public class StudentBubbleSorter implements Sorter<Student> {

    @Override
    public int compare(Student student, Student t1) {

        return student.compareTo(t1);
    }

    @Override
    public Student[] sort(Student[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int secondary = 0; secondary < array.length - 1; secondary++) {
                if (compare(array[secondary], array[secondary + 1]) > 0) {
                    array = swap(array, secondary, secondary + 1);
                }
            }
        }
        return array;
    }
    /**
     * Swap value in array
     *
     * @param array
     * @param index
     * @param secondIndex
     * @return
     */
    private Student[] swap(Student[] array, int index, int secondIndex) {
        Student s = array[index];
        array[index] = array[secondIndex];
        array[secondIndex] = s;
        return array;

    }
}
