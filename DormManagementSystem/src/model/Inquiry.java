package model;

import java.io.Serializable;

public class Inquiry implements Serializable {
private static final long serialVersionUID = 1L;
    // Attributes
    private String inquiryID;         // Unique identifier for the inquiry
    private Student student;          // Student who made the inquiry
    private DormListing listing;      // Dorm listing being inquired about
    private String message;           // Content of the inquiry message
    private String dateInquired;      // Date when inquiry was made
    private String status;            // Status of inquiry ("Pending" or "Responded")
    private String response;          // Response to the inquiry

    // Constructor
    public Inquiry(String inquiryID, Student student, DormListing listing, String message, String dateInquired) {
        this.inquiryID = inquiryID;
        this.student = student;
        this.listing = listing;
        this.message = message;
        this.dateInquired = dateInquired;
        this.status = "Pending";
        this.response = "";
    }

    // Getters
    public String getInquiryID() {
        return inquiryID;
    }

    public Student getStudent() {
        return student;
    }

    public DormListing getListing() {
        return listing;
    }

    public String getMessage() {
        return message;
    }

    public String getDateInquired() {
        return dateInquired;
    }

    public String getStatus() {
        return status;
    }

    public String getResponse() {
        return response;
    }

    // Setters
    public void setStudent(Student student) {
        this.student = student;
    }

    public void setListing(DormListing listing) {
        this.listing = listing;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDateInquired(String dateInquired) {
        this.dateInquired = dateInquired;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    // Methods
    public void respond(String response) {
        this.response = response;
        this.status = "Responded";
    }

    public String displayInfo() {
        return "Inquiry ID: " + inquiryID +
               "\nStudent: " + student.getfullName() +
               "\nMessage: " + message +
               "\nDate: " + dateInquired +
               "\nStatus: " + status;
    }
}
