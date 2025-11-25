package model;

public class Student extends Person {
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
        this.budget = budget;
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
        this.isRenting = true;
        this.currentRoom = room;
        this.leaseStartDate = startDate;
        this.leaseEndDate = endDate;
        this.monthlyRent = rent;
        this.paymentStatus = "Pending";
        System.out.println(getName() + " successfully booked room " + room.getRoomNumber());
    }

    public void vacateRoom() {
        if (currentRoom != null) {
            System.out.println(getName() + " vacated room " + currentRoom.getRoomNumber());
        }
        this.isRenting = false;
        this.currentRoom = null;
        this.leaseStartDate = null;
        this.leaseEndDate = null;
        this.monthlyRent = 0;
        this.paymentStatus = "N/A";
    }

    public void payRent() {
        if (isRenting) {
            this.paymentStatus = "Paid";
            System.out.println("Payment of ₱" + String.format("%.2f", monthlyRent) + " recorded for " + getName());
        } else {
            System.out.println(getName() + " is not currently renting.");
        }
    }

    public void browseListings() {
        System.out.println(getName() + " is browsing available dorm listings...");
    }

    public void inquireRoom(DormListing listing) {
        System.out.println(getName() + " inquired about: " + listing.getDorm().getDormName());
    }
    
    @Override
    public String displayInfo() {
        String info = "\n=== STUDENT INFO ===" +
                      "\nName: " + getName() +
                      "\nStudent ID: " + studentID +
                      "\nUniversity: " + universitySchool +
                      "\nBudget: ₱" + String.format("%.2f", budget) +
                      "\nEmail: " + getEmail() +
                      "\nContact: " + getContactInfo() +
                      "\nAddress: " + getAddress();
        
        // Show rental info if currently renting
        if (isRenting && currentRoom != null) {
            info += "\n\n=== RENTAL INFO ===" +
                    "\nRoom: " + currentRoom.getRoomNumber() +
                    "\nMonthly Rent: ₱" + String.format("%.2f", monthlyRent) +
                    "\nLease: " + leaseStartDate + " to " + leaseEndDate +
                    "\nPayment Status: " + paymentStatus;
        }
        
        return info;
    }
}