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
public class Student implements Comparable<Student> {

    private final int BEGIN_NUMMER = 50080001;
    private int studentnummer;
    private double toetsCijfer;
    private String klas;

    public String getKlas() {
        return klas;
    }

    public void setKlas(String klas) {
        this.klas = klas;
    }

    public Student(int studentnummer, double toetsCijfer, String klas) {
        this.studentnummer = BEGIN_NUMMER + studentnummer;
        this.toetsCijfer = toetsCijfer;
        this.klas = klas;
    }

    public int getStudentnummer() {
        return studentnummer;
    }

    public void setStudentnummer(int studentnummer) {
        this.studentnummer = studentnummer;
    }

    public double getToetsCijfer() {
        return toetsCijfer;
    }

    public void setToetsCijfer(double toetsCijfer) {
        this.toetsCijfer = toetsCijfer;
    }

    @Override
    public String toString() {
        return "Studentnummer: " + studentnummer
                + "\t" + "Cijfer: " + toetsCijfer
                + "\t" + "Klas: " + klas;
    }

    @Override
    public int compareTo(Student s) {
        if (this.getToetsCijfer() == s.getToetsCijfer()) {
            return this.getStudentnummer() - s.getStudentnummer();
        } else if (this.getToetsCijfer() < s.getToetsCijfer()) {
            return -1;
        } else {
            return 1;
        }
    }

    public int compare(Student student) {
        return this.getStudentnummer() - student.getStudentnummer();
    }

    public static Student[] sorterenStudenten(Student[] studentenlijst) {
        Student temp;
        for (int i = 1; i < studentenlijst.length; i++) {
            for (int j = i; j > 0; j--) {
                if (studentenlijst[j] != null) {
                    if (studentenlijst[j].compareTo(studentenlijst[j - 1]) < 0) {
                        temp = studentenlijst[j];
                        studentenlijst[j] = studentenlijst[j - 1];
                        studentenlijst[j - 1] = temp;
                    }
                }
            }
        }
        return studentenlijst;
    }

    public static Student[] insertionSort(Student[] toSort) {
        int N = toSort.length;
        //voor elke waarde i in de array...
        for (int i = 1; i < N; i++) {
            //... kijk of er een waarde j voor de waarde i is die lger gesorteerd word...
            for (int j = i; j > 0 && toSort[j].compareTo(toSort[j - 1]) < 0; j--) {
                //... en switch ze van plaats.
                exchange(toSort, j, j - 1);
            }
        }
        return toSort;
    }

    private static void exchange(Student[] toSort, int first, int second) {
        Student studentToBeExchanged = toSort[first];
        toSort[first] = toSort[second];
        toSort[second] = studentToBeExchanged;
    }
}
