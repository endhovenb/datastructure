package nl.hva.dmci.ict.datastructures;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * StudentList is a class for generating a list of random students.
 * The random parts are the ldap and the number of ects earned.
 * 
 * @author Dennis Breuker
 * @author Nico Tromp
 * 
 */
public class StudentList {

    private final int MAX_ECTS = 240;
    private final String PREFIX_FILE = "/prefixes.txt";
    
    private final Random randomizer;
    private final Student[] students;   // size is known --> array
    private List<Ldap> ldaps;           // size is unknown --> List

    /**
     * Constructs a StudentList object.
     * 
     * @param studentCount the number of students in the list
     */
    public StudentList(int studentCount) {
        randomizer = new SecureRandom();
        students = new Student[studentCount];
        readPrefixes();
        generateRanomizedList(studentCount);
    }

    /**
     * Reads prefixes from an input file. The prefixes are stored in
     * an ldap list. If no file is found, 0 prefixes are read.
     */
    private void readPrefixes() {
        ldaps = new ArrayList<>();
        try (Scanner scan = new Scanner(getClass().getResourceAsStream(PREFIX_FILE))) {
            while (scan.hasNext()) {
                String prefix = scan.nextLine();
                Ldap ldap = new Ldap(prefix);
                ldaps.add(ldap);
            }
        }
    }

    /**
     * Generates a list of random students.
     * 
     * @param studentCount the number of students to be generated
     */
    private void generateRanomizedList(int studentCount) {
        for (int i = 0; i < studentCount; i++) {
            Student student = generateRandomStudent();
            students[i] = student;
        }
    }

    /**
     * Generates a random student. First an ldap is generated.
     * Next, a random amount of ects is generated.
     * The amount is between 0 and MAX_ECTS (inclusive).
     * 
     * @return the generated student
     */
    private Student generateRandomStudent() {
        String ldap = generateLdap();
        int ects = randomizer.nextInt(MAX_ECTS + 1);
        Student student = new Student(ldap, ects);
        return student;
    }

    /**
     * Generates an ldap with random prefix.
     * 
     * @return the generated ldap
     */
    private String generateLdap() {
        int index = randomizer.nextInt(ldaps.size());
        Ldap name = ldaps.get(index);
        return name.generate();
    }
    
    /**
     * Gets the generated student list
     * 
     * @return the generated list
     */
    public Student[] getList() {
        return students;
    }
    
    /**
     * Converts the list to a string.
     * 
     * @return the converted list
     */
    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        for (Student student : students) {
            s = s.append(student).append(System.lineSeparator());
        }
        return s.toString();
    }
}

/**
 * Ldap is a utility class. It consists of a prefix and an id.
 * For every prefix it is known what the next id should be.
 * For instance, if the last name with prefix was breu3, then
 * the Ldap object with prefix "breu" has a nextId of 4.
 * 
 * @author Dennis Breauker
 */
class Ldap {
    private final int START_ID = 1;
    
    private final String prefix;
    private int nextId;

    public Ldap(String prefix) {
        this.prefix = prefix;
        nextId = START_ID;
    }

    /**
     * Generates a new ldap, based upon the prefix and nextId.
     * As a consequence, nextId is increased by one.
     * 
     * @return the new name.
     */
    public String generate() {
        return prefix + nextId++;
    }
}