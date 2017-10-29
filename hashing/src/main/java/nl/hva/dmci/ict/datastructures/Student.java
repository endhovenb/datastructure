
package nl.hva.dmci.ict.datastructures;

/**
 * Student consists in this example only of an ldap and the amount of ects earned.
 * TODO Implement the hashCode() method!
 * 
 * @author Dennis Breuker
 * @author Nico Tromp
 */
public class Student {
    private final String ldap;
    private final int ects;

    public Student(String ldap, int ects) {
        this.ldap = ldap;
        this.ects = ects;
    }

    public String getLdap() {
        return ldap;
    }

    public int getEcts() {
        return ects;
    }
    
    /**
     * Converts the student to a string.
     * 
     * @return the converted student
     */
    @Override
    public String toString() {
        return "Student{" + "ldap=" + ldap + ", ects=" + ects + '}';
    }
}
