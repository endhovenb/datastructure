/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.dmci.ict.se.datastructures;


/**
 *
 * @author Peter en Bart
 */
public class Bucket implements Comparable<Bucket> {

    private String klas;
    private Queue<Student> studenten;

    public Bucket(String klas) {
        this.klas = klas;
        this.studenten = new Queue<>();
    }

    public String getKlas() {
        return klas;
    }

    public void setKlas(String klas) {
        this.klas = klas;
    }

    public Queue<Student> getStudenten() {
        return studenten;
    }

    public void setStudenten(Queue<Student> studenten) {
        this.studenten = studenten;
    }

    public void voegStudentInBucket(Student student) {
        this.studenten.add(student);
    }

    @Override
    public int compareTo(Bucket other) {
        if (this.getKlas().compareTo(other.getKlas()) < 0) {
            return -1;
        } else if (this.getKlas().equals(other.getKlas())) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        String returnString = "";
        returnString += "Klas: " + klas;
        for (Student student : studenten) {
            returnString += "\nStudent: " + student.getStudentnummer()
                    + " Cijfer " + student.getToetsCijfer();
        }
        return returnString + "\n";
    }
}