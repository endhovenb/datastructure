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
class KlasBucket {

    private String klas;
    private SortedLinkedList<Student> students;

    public KlasBucket(String klas) {
        this.klas = klas;
        this.students = new SortedLinkedList(studentIdComparator());
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     *
     * @return
     */
    private Comparator<Student> studentIdComparator() {
        //Compare de studentnummers.
        return (student1, student2)
                -> Long.compare(student1.getStudentnummer(), student2.getStudentnummer());
    }

    public String getKlas() {
        return klas;
    }

    public SortedLinkedList<Student> getStudents() {
        return students;
    }
}
