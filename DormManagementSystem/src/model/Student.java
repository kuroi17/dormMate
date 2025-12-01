package model;

public class Student extends Person {
    private String studentID;
    private String universitySchool;
    private double budget;
    private boolean isRenting;
    private Room currentRoom;
    private String leaseStartDate;
    private String leaseEndDate;
    private double monthlyRent;
    private String paymentStatus;
    
    public Student(String fullName, String email, String contactNumber, 
                   String address, String studentID, String universitySchool, 
                   double budget) {
        super(fullName, email, contactNumber, address);
        this.studentID = studentID;
        this.universitySchool = universitySchool;
        this.budget = budget;
        this.isRenting = false;
        this.paymentStatus = "N/A";
    }
    
    // ============================================================
    // STUDENT-SPECIFIC OPERATIONS (moved from dormMate.java)
    // ============================================================
    
    public boolean bookRoom(Room room, String startDate, String endDate, double rent) {
        if (isRenting) {
            System.out.println("❌ You are already renting a room!");
            return false;
        }
        
        if (!room.isAvailable()) {
            System.out.println("❌ Room is not available!");
            return false;
        }
        
        if (rent > budget) {
            System.out.println("❌ Room rent exceeds your budget!");
            return false;
        }
        
        this.currentRoom = room;
        this.leaseStartDate = startDate;
        this.leaseEndDate = endDate;
        this.monthlyRent = rent;
        this.isRenting = true;
        this.paymentStatus = "Pending";
        
        room.setAvailable(false);
        room.incrementCurrentOccupants();
        
        System.out.println("✅ Room booked successfully!");
        return true;
    }
    
    public void payRent() {
        if (!isRenting) {
            System.out.println("❌ You are not renting any room!");
            return;
        }
        
        if ("Paid".equals(paymentStatus)) {
            System.out.println("✅ Rent already paid for this month!");
            return;
        }
        
        this.paymentStatus = "Paid";
        System.out.printf("✅ Rent of ₱%.2f paid successfully!\n", monthlyRent);
    }
    
    public void vacateRoom() {
        if (!isRenting) {
            System.out.println("❌ You are not renting any room!");
            return;
        }
        
        if (currentRoom != null) {
            currentRoom.setAvailable(true);
            currentRoom.decrementCurrentOccupants();
        }
        
        this.currentRoom = null;
        this.isRenting = false;
        this.leaseStartDate = null;
        this.leaseEndDate = null;
        this.monthlyRent = 0;
        this.paymentStatus = "N/A";
        
        System.out.println("✅ Room vacated successfully!");
    }
    
    @Override
    public String displayInfo() {
        StringBuilder info = new StringBuilder();
        info.append("\n👤 STUDENT INFORMATION\n");
        info.append("━".repeat(40)).append("\n");
        info.append(String.format("Name: %s\n", getfullName()));
        info.append(String.format("Student ID: %s\n", studentID));
        info.append(String.format("University: %s\n", universitySchool));
        info.append(String.format("Email: %s\n", getEmail()));
        info.append(String.format("Contact: %s\n", getContactNumber()));
        info.append(String.format("Budget: ₱%.2f\n", budget));
        info.append(String.format("Renting: %s\n", isRenting ? "Yes" : "No"));
        
        if (isRenting && currentRoom != null) {
            info.append("\n🏠 RENTAL DETAILS\n");
            info.append(String.format("Room: %s\n", currentRoom.getRoomNumber()));
            info.append(String.format("Monthly Rent: ₱%.2f\n", monthlyRent));
            info.append(String.format("Lease: %s to %s\n", leaseStartDate, leaseEndDate));
            info.append(String.format("Payment Status: %s\n", paymentStatus));
        }
        
        return info.toString();
    }
    
    // Getters and Setters
    public String getStudentID() { return studentID; }
    public void setStudentID(String studentID) { this.studentID = studentID; }
    
    public String getUniversitySchool() { return universitySchool; }
    public void setUniversitySchool(String universitySchool) { this.universitySchool = universitySchool; }
    
    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }
    
    public boolean isRenting() { return isRenting; }
    
    public Room getCurrentRoom() { return currentRoom; }
    
    public String getLeaseStartDate() { return leaseStartDate; }
    public String getLeaseEndDate() { return leaseEndDate; }
    
    public double getMonthlyRent() { return monthlyRent; }
    
    public String getPaymentStatus() { return paymentStatus; }
}