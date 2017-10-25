package nl.hva.dmci.ict.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Small program that creates a list of students which can be used for the
 * practical lab 3 of the course Datastructures.
 *
 * @author Dennis Breuker
 * @author Nico Tromp
 */
public class GenerateStudents {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StudentList students = new StudentList(10000);
        //System.out.println(students);

//        Student[] studenten = students.getList();
//        
//        for (Student student : studenten) {
//            long hash = hashing(student.getLdap());
//            System.out.println(hash);
//        }
        long hen = hashing("hen");
        long bart = hashing("bart");
        long pete = hashing("pete");
        System.out.println(hen);
        System.out.println(bart);
        System.out.println(pete);

    }

    /**
     * creer een hashcode van 9 cijfers waarbij het eerste cijfer de lengte is
     * de string van de letters worden omgezet in een cijfer afhankelijk van de
     * plek in het alfabet. a = 01, b = 02 etc.
     */
    public static long hashing(String ldap) {
        long hash;
        int lengte = ldap.length();
        List<String> t = new ArrayList<>();
        for (int i = 0; i < ldap.length(); ++i) {
            char ch = ldap.charAt(i);
            int n = (int) ch - (int) 'a' + 1;
            t.add(String.valueOf(n));
        }
        String[] cijfers = new String[4];
        t.toArray(cijfers);
        int cijfer1 = Integer.parseInt(cijfers[0]);
        int cijfer2 = Integer.parseInt(cijfers[1]);
        int cijfer3 = Integer.parseInt(cijfers[2]);
        if (cijfers[3] == null) {
            cijfers[3] = "00";
        }
        int cijfer4 = Integer.parseInt(cijfers[3]);
        hash = (lengte * 100000000) + (cijfer1 * 1000000) + (cijfer2 * 10000) + (cijfer3 * 100) + (cijfer4);
        return hash;
    }
}
