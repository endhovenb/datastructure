/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.dmci.ict.se.datastructures;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 *
 * @author Bart
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int AANTAL_STUDENTEN = 50;
        Student[] studenten = new Student[AANTAL_STUDENTEN];
        String[] klassen = KlasGenerator.maakKlassen(AANTAL_STUDENTEN);
        for (int i = 0; i < AANTAL_STUDENTEN; i++) {
            Random rand = new Random();
            double afronden = (rand.nextInt(91) + 10) / 10.0;
            studenten[i] = new Student(i, afronden, klassen[i]);
        }
        //dit is de quick sort functie.
        //studenten = Student.sorterenStudenten(studenten);
        
        //dit is de bubblesort functie.
        //StudentBubbleSorter bubble = new StudentBubbleSorter();
        //bubble.sort(studenten);
        
        //Dit is een injectionsort functie.
        //studenten = Student.insertionSort(studenten);
        
        //Dit is een bucket sort om op klas te sorteren.
        //StudentBucketSorter bucket = new StudentBucketSorter();
        //bucket.sort(studenten);
        
        //printen van alle studenten die nu gesoorteerd zijn.
        for (Student student : studenten) {
            System.out.println(student);
        }

        // in een treemap weergeven het aantal keer dat een cijfer is toegekent aan studenten.
        Map<Double, Integer> map = new TreeMap<>();
        for (Student temp : studenten) {
            Integer count = map.get(temp.getToetsCijfer());
            map.put(temp.getToetsCijfer(), (count == null) ? 1 : count + 1);
        }
        //System.out.println(map);
        
        
        
        
    }
}
