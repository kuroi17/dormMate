package model;
import util.InputValidator;
import java.io.Serializable;

public class Student extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
   
    // own attributes of Student
    private String studentID; 
    private String universitySchool;
    private double budget;
    
    // rental state attributes
    private boolean isRenting;
    private Room currentRoom;
    private String leaseStartDate;
    private String leaseEndDate;
    private double monthlyRent;
    private String paymentStatus;

    public Student(
        String fullName,
        String email,
        String contactNumber,
        String address,

        String studentID,
        String universitySchool,
        double budget
    ) {
        // use super to call Person constructor
        super(fullName, email, contactNumber, address); 
        
        this.studentID = studentID;
        this.universitySchool = universitySchool;
        this.budget = budget;

        // initialize rental state
        this.isRenting = false;
        this.currentRoom = null;
        this.paymentStatus = "N/A";
    }

    // use special methods known as getters and setters from enscapsulation
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
        if (InputValidator.isValidPrice(budget)){
            this.budget = budget;
        }
        else{
            InputValidator.printError("Budget must be positive: " + budget);
        }
        
    }

    // rental state getters/setters
    public boolean isRenting() {
        return isRenting;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public String getLeaseStartDate() {
        return leaseStartDate;
    }

    public String getLeaseEndDate() {
        return leaseEndDate;
    }

    public double getMonthlyRent() {
        return monthlyRent;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    // methods for booking/vacating
    public void bookRoom(Room room, String startDate, String endDate, double rent) {
        
        if(!room.isAvailable()){
            InputValidator.printError("Room " + room.getRoomNumber() + " is not available.");
            return;
        }

        if (!InputValidator.isValidDate(startDate)){
            InputValidator.printError("Invalid start date: " + startDate);
            return;
        }

         if (!InputValidator.isValidDate(endDate)){
            InputValidator.printError("Invalid end date: " + endDate);
            return;
        }
         // Validate budget
        if (this.budget < rent) {
            InputValidator.printError("Insufficient budget! Need (Php)" + rent + " but have (Php)" + this.budget);
            return;
        }
        
        
        this.isRenting = true;
        this.currentRoom = room;
        this.leaseStartDate = startDate;
        this.leaseEndDate = endDate;
        this.monthlyRent = rent;
        this.paymentStatus = "Pending";
        System.out.println(getfullName() + " successfully booked the room " + room.getRoomNumber());
    }

    public void vacateRoom() {
        if (currentRoom != null) {
            System.out.println(getfullName() + " vacated room " + currentRoom.getRoomNumber());
        }
        this.isRenting = false;
        this.currentRoom = null;
        this.leaseStartDate = null;
        this.leaseEndDate = null;
        this.monthlyRent = 0;
        this.paymentStatus = "N/A";
    }

    public void browseListings() {
        System.out.println(getfullName() + " is browsing available dorm listings...");
    }

    public void inquireRoom(DormListing listing) {
        System.out.println(getfullName() + " inquired about: " + listing.getDorm().getDormName());
    }
    // abstract method implementation from Person class
    // polymorphism: different implementation of displayInfo() method
    @Override
    public String displayInfo() {
        String info = "\n=== STUDENT INFO ===" +
                      "\nName: " + getfullName() +
                      "\nStudent ID: " + studentID +
                      "\nUniversity: " + universitySchool +
                      "\nBudget: (Php)" + String.format("%.2f", budget) +
                      "\nEmail: " + getEmail() +
                      "\nContact: " + getContactNumber() +
                      "\nAddress: " + getAddress();
        
        // Show rental info if currently renting
        if (isRenting && currentRoom != null) {
            info += "\n\n=== RENTAL INFO ===" +
                    "\nRoom: " + currentRoom.getRoomNumber() +
                    "\nMonthly Rent: (Php)" + String.format("%.2f", monthlyRent) +
                    "\nLease: " + leaseStartDate + " to " + leaseEndDate +
                    "\nPayment Status: " + paymentStatus;
        }
        return info;
    }
}