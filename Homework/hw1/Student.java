/*
 * C343 Summer 2018
 * Lab mini-assignment 04
 * Ian Polito
 * ipolito
 */

public class Student {
 
    //name, department are instance variables
    public String name = "Unknown";
    private String department = "Unknown";
    
    public Student(String name) {
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getDepartment() {
        return department;
    }
    public void display() {
        System.out.println("Student Information");
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
    }
 
} // end of class Student
