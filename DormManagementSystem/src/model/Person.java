package model;

/**
 * Abstract class representing a person in the dorm management system.
 * @author Kuroi
 * 
 * // person may contain common attributes like name, address, contact number, email, etc.
 */


 public abstract class Person {
    private String name;
    private String email;
    private String contactInfo;
    private String address;



    public Person(String name, String email, String contactInfo, String address) {
    this.name = name;
    this.email = email;
    this.contactInfo = contactInfo;
    this.address = address;
 }


 // gettters and setters special methods
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getContactInfo() {
        return contactInfo;
    }
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
 // ABSTRACTION: Abstract method for polymorphism
    public abstract String displayInfo(); 
    // abstract method walang body or implementation dito sa parentg class
 }

 

    
    