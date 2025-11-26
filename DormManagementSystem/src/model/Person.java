package model;

/**
 * Abstract class representing a person in the dorm management system.
 * @author Kuroi
 * 
 * // person may contain common attributes like fullName, address, contact number, email, etc.
 */


 public abstract class Person {
    private String fullName;
    private String email;
    private String contactNumber;
    private String address;



    public Person(String fullName, String email, String contactNumber, String address) {
    this.fullName = fullName;
    this.email = email;
    this.contactNumber = contactNumber;
    this.address = address;
 }


 // gettters and setters special methods
    public String getfullName() {
        return fullName;
    }
    public void setfullName(String fullName) {
        this.fullName = fullName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
 // ABSTRACTION: Abstract method for polymorphism   
 //POLYMORPHISM: Different subclasses will provide their own implementation of this method
    public abstract String displayInfo(); 
    // abstract method walang body or implementation dito sa parentg class
 }


 

    
    