package nl.hva.dmci.ict.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Small program that creates a list of students which can be used for the
 * practical lab 3 of the course Datastructures.
 *
 * @author Dennis Breuker
 * @author Nico Tromp
 * @author Bart en Peter
 */
public class GenerateStudents {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HashMap hm = new HashMap();
        LinearProbingHashST tab1 = new LinearProbingHashST();
        SeparateChainingHashST tab2 = new SeparateChainingHashST();
        QuadraticHashST tab3 = new QuadraticHashST();
        StudentList students = new StudentList(10000);
        //System.out.println(students);

        Student[] studenten = students.getList();

        for (Student student : studenten) {
            long hash = hashing(student.getLdap());
            int punten = student.getEcts();
            hm.put(hash, punten);
            tab1.put(hash, punten);
            tab2.put(hash, punten);
            tab3.put(hash, punten);
            //System.out.println(student);
            //System.out.println("tab1: " + tab1.get(hash));
            //System.out.println("tab2: " + tab2.get(hash));
        }
        System.out.println("LinearProbingHashing");
        System.out.println("number of collisions: " + tab1.getCollisions() + " With a M size of " + tab1.getM() + "\n");
        System.out.println("SeparateChainingHashing");
        System.out.println("number of collisions: " + tab2.getCollisions() + " With a M size of " + tab2.getM() + "\n");
        System.out.println("QuadraticHashing");
        System.out.println("number of collisions: " + tab3.getCollisions() + " With a M size of " + tab3.getM() + "\n");

//        Set set = hm.entrySet();
//
//        // Get an iterator
//        Iterator i = set.iterator();
//
//        // Display elements
//        Map<Object, Integer> map = new TreeMap<>();
//        while (i.hasNext()) {
//            Map.Entry me = (Map.Entry) i.next();
//            //System.out.print(me.getKey() + ": ");
//            //System.out.println(me.getValue());
//
//            tab1.put(me.getKey() , me.getValue());
//            System.out.println(tab1.get(me.getKey()));
//            Integer count = map.get((long) me.getKey());
//            map.put(((long) me.getKey()), (count == null) ? 1 : count + 1);
//        }
//        printAantallen(map);
//        long hen = hashing("hen");
//        long bart = hashing("bart");
//        long pete = hashing("pete");
//        System.out.println(hen);
//        System.out.println(bart);
//        System.out.println(pete);
    }

    public static void printAantallen(Map<Object, Integer> map) {
        map.entrySet().forEach((entry) -> {
            System.out.println("gehasde ldap : " + entry.getKey()
                    + " Aantal : " + entry.getValue());
        });
    }

    /**
     * creer een hashcode van 9 cijfers waarbij het eerste cijfer de lengte is
     * de string van de letters worden omgezet in een cijfer afhankelijk van de
     * plek in het alfabet. a = 01, b = 02 etc.
     *
     * @param ldap
     * @return
     */
    public static long hashing(String ldap) {
        long hash;
        int lengte = ldap.length();
        List<String> t = new ArrayList<>();
        int cijfer1 = 0;
        int cijfer2 = 0;
        int cijfer3 = 0;
        int cijfer4 = 0;
        for (int i = 0; i < ldap.length(); ++i) {
            int n;
            try {
                char ch = ldap.charAt(i);
                n = (int) ch - (int) 'a' + 1;
            } catch (Exception e) {
                n = 0;
            }
            t.add(String.valueOf(n));
        }
        String[] cijfers = new String[t.size()];
        t.toArray(cijfers);
        try {
            cijfer1 = Integer.parseInt(cijfers[0]);
            cijfer2 = Integer.parseInt(cijfers[1]);
            cijfer3 = Integer.parseInt(cijfers[2]);
        } catch (Exception e) {
            System.out.println("somthing went wrong");
        }

        try {
            cijfer4 = Integer.parseInt(cijfers[3]);
        } catch (Exception e) {

        }

        hash = (lengte * 100000000) + (cijfer1 * 1000000) + (cijfer2 * 10000) + (cijfer3 * 100) + (cijfer4);
        return (hash);

    }

}
