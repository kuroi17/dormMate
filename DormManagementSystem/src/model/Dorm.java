package model;

import java.util.ArrayList;
import java.util.List;

public class Dorm {
    private String dormName;
    private List<Room> rooms;
    private String googleMapsLink;
    private String address;
    private String description;
    
    public Dorm(String dormName, List<Room> rooms, String googleMapsLink, 
                String address, String description) {
        this.dormName = dormName;
        this.rooms = rooms != null ? rooms : new ArrayList<>();
        this.googleMapsLink = googleMapsLink;
        this.address = address;
        this.description = description;
    }
    
    // ============================================================
    // DORM-SPECIFIC OPERATIONS
    // ============================================================
    
    public void addRoom(Room room) {
        rooms.add(room);
        System.out.printf("✅ Room %s added to %s\n", room.getRoomNumber(), dormName);
    }
    
    public void removeRoom(Room room) {
        if (rooms.remove(room)) {
            System.out.printf("✅ Room %s removed from %s\n", room.getRoomNumber(), dormName);
        } else {
            System.out.println("❌ Room not found!");
        }
    }
    
    public int getAvailableRoomCount() {
        int count = 0;
        for (Room room : rooms) {
            if (room.isAvailable()) {
                count++;
            }
        }
        return count;
    }
    
    public List<Room> getAvailableRooms() {
        List<Room> available = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                available.add(room);
            }
        }
        return available;
    }
    
    public double getCheapestRoomPrice() {
        if (rooms.isEmpty()) return 0.0;
        
        double cheapest = Double.MAX_VALUE;
        for (Room room : rooms) {
            if (room.getPricePerMonth() < cheapest) {
                cheapest = room.getPricePerMonth();
            }
        }
        return cheapest;
    }
    
    // Getters and Setters
    public String getDormName() { return dormName; }
    public void setDormName(String dormName) { this.dormName = dormName; }
    
    public List<Room> getRooms() { return rooms; }
    public void setRooms(List<Room> rooms) { this.rooms = rooms; }
    
    public String getGoogleMapsLink() { return googleMapsLink; }
    public void setGoogleMapsLink(String googleMapsLink) { this.googleMapsLink = googleMapsLink; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}