package model;

public class Student extends Person {
    private String studentID; // own attribute or instanceVariable ng studentClass
    private String universitySchool; // own attribute or instanceVariable ng studentClass
    private double budget; // own attribute or instanceVariable ng studentClass

    // parameterized constructoir 

    public Student(
        String name,
        String email,
        String contactInfo,
        String address,
        String studentID,
        String universitySchool,
        double budget
    ) {
        // usinf super to call parent class constructoir

        super(name, email, contactInfo, address);
        this.studentID = studentID;
        this.universitySchool = universitySchool;
        this.budget = budget;
    }

    // special method of polymorphism called getters and setters
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getUniversitySchool() {
        return universitySchool;
    }

    public void setUniversitySchool(String universitySchool) {
        this.universitySchool = universitySchool;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
    
    @Override // implementation of abstract method from Person class
    public String displayInfo(){
        return "\nName: " + getName() +
               "\nStudent ID: " + studentID +
               "\nUniversity: " + universitySchool +
               "\nBudget: ₱" + String.format("%.2f", budget) +
               "\nEmail: " + getEmail() +
               "\nContact: " + getContactInfo() +
               "\nAddress: " + getAddress();
    }
}
