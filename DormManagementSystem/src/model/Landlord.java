package model;

import java.util.ArrayList;
import java.util.List;

public class Landlord extends Person {
    private String landlordID;
    private List<Dorm> ownedDorms;
    
    public Landlord(String fullName, String email, String contactNumber, 
                    String address, String landlordID) {
        super(fullName, email, contactNumber, address);
        this.landlordID = landlordID;
        this.ownedDorms = new ArrayList<>();
    }
    
    // ============================================================
    // LANDLORD-SPECIFIC OPERATIONS
    // ============================================================
    
    public void addDorm(Dorm dorm) {
        ownedDorms.add(dorm);
        System.out.printf("✅ Dorm '%s' added successfully!\n", dorm.getDormName());
    }
    
    public void removeDorm(Dorm dorm) {
        if (ownedDorms.remove(dorm)) {
            System.out.printf("✅ Dorm '%s' removed successfully!\n", dorm.getDormName());
        } else {
            System.out.println("❌ Dorm not found!");
        }
    }
    
    public void viewMyDorms() {
        if (ownedDorms.isEmpty()) {
            System.out.println("📋 No dorms registered yet.");
            return;
        }
        
        System.out.println("\n🏢 YOUR DORMS:");
        System.out.println("━".repeat(50));
        for (int i = 0; i < ownedDorms.size(); i++) {
            Dorm dorm = ownedDorms.get(i);
            System.out.printf("[%d] %s - %s\n", i + 1, dorm.getDormName(), dorm.getAddress());
            System.out.printf("    Rooms: %d | Available: %d\n", 
                dorm.getRooms().size(), 
                dorm.getAvailableRoomCount());
        }
    }
    
    @Override
    public String displayInfo() {
        StringBuilder info = new StringBuilder();
        info.append("\n🏠 LANDLORD INFORMATION\n");
        info.append("━".repeat(40)).append("\n");
        info.append(String.format("Name: %s\n", getfullName()));
        info.append(String.format("Landlord ID: %s\n", landlordID));
        info.append(String.format("Email: %s\n", getEmail()));
        info.append(String.format("Contact: %s\n", getContactNumber()));
        info.append(String.format("Address: %s\n", getAddress()));
        info.append(String.format("Owned Dorms: %d\n", ownedDorms.size()));
        return info.toString();
    }
    
    // Getters and Setters
    public String getLandlordID() { return landlordID; }
    public void setLandlordID(String landlordID) { this.landlordID = landlordID; }
    
    public List<Dorm> getOwnedDorms() { return ownedDorms; }
}