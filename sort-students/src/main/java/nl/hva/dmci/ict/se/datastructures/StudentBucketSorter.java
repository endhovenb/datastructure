/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.dmci.ict.se.datastructures;

import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author Bart
 */
public class StudentBucketSorter implements Sorter<Student> {

    @Override
    public Student[] sort(Student[] unsorted) {
        //Generate een sorted linkedlist .
        SortedLinkedList<KlasBucket> klassen = new SortedLinkedList(klasBucketComparator());
        for (Student student : unsorted) {
            KlasBucket bucket = getBucket(klassen, student.getKlas());
            bucket.addStudent(student);
        }
        //loop door de linkedlist heen om er wee een array van te maken.
        Iterator<KlasBucket> klasIterator = klassen.iterator();
        int counter = 0;
        while (klasIterator.hasNext()) {
            KlasBucket bucket = klasIterator.next();
            Iterator<Student> studentIterator = bucket.getStudents().iterator();
            while (studentIterator.hasNext()) {
                Student student = studentIterator.next();
                unsorted[counter] = student;
                counter++;
            }
        }
        return unsorted;
    }

    /**
     * 
     * @param buckets
     * @param klas
     * @return 
     */
    private KlasBucket getBucket(SortedLinkedList<KlasBucket> buckets, String klas) {
        Iterator<KlasBucket> klasBucketIterator = buckets.iterator();
        while (klasBucketIterator.hasNext()) {
            KlasBucket bucket = klasBucketIterator.next();
            if (bucket.getKlas().equals(klas)) {
                return bucket;
            }
        }
        KlasBucket bucket = new KlasBucket(klas);
        buckets.add(bucket);
        return bucket;
    }

    /**
     * 
     * @param student
     * @param t1
     * @return 
     */
    @Override
    public int compare(Student student, Student t1) {
        return student.compareTo(t1);
    }

    /**
     * 
     * @return 
     */
    private Comparator<KlasBucket> klasBucketComparator() {
        return (bucket1, bucket2) -> bucket1.getKlas().compareTo(bucket2.getKlas());
    }
}
