package model;
import java.io.Serializable;

import util.InputValidator;

 public abstract class Person implements Serializable{
    private static final long serialVersionUID = 1L;
    private String fullName;
    private String email;
    private String contactNumber;
    private String address;

    public Person(String fullName, String email, String contactNumber, String address) {
    this.fullName = fullName;
    if (InputValidator.isValidEmail(email)) {
    this.email = email;
    } else {
    this.email = "invalid@email.com";
    }
    if (InputValidator.isValidContact(contactNumber)) {
    this.contactNumber = contactNumber;
    } else {
    this.contactNumber = "This Contact Number format is incorrect.";
    }
    if (!InputValidator.isValidEmail(email)) {
            InputValidator.printError("Invalid email provided: " + email);
        }
    if (!InputValidator.isValidContact(contactNumber)) {
            InputValidator.printError("Invalid contact number: " + contactNumber);
    }
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
        if (InputValidator.isValidEmail(email)) {
            this.email = email;
        } else {
            InputValidator.printError("Invalid email provided: " + email);
        }
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        if (InputValidator.isValidContact(contactNumber)) {
            this.contactNumber = contactNumber;
        } else {
            InputValidator.printError("Invalid contact number: " + contactNumber);
        }
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