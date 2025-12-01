

package model;

public class Room {
    private String roomNumber;
    private int capacity;
    private int currentOccupants;
    private double pricePerMonth;
    private boolean isAvailable;
    
    public Room(String roomNumber, int capacity, double pricePerMonth) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.currentOccupants = 0;
        this.pricePerMonth = pricePerMonth;
        this.isAvailable = true;
    }
    
    // ============================================================
    // ROOM-SPECIFIC OPERATIONS
    // ============================================================
    
    public void incrementCurrentOccupants() {
        if (currentOccupants < capacity) {
            currentOccupants++;
            if (currentOccupants >= capacity) {
                isAvailable = false;
            }
        }
    }
    
    public void decrementCurrentOccupants() {
        if (currentOccupants > 0) {
            currentOccupants--;
            if (currentOccupants < capacity) {
                isAvailable = true;
            }
        }
    }
    
    public boolean hasVacancy() {
        return currentOccupants < capacity;
    }
    
    public int getRemainingSlots() {
        return capacity - currentOccupants;
    }
    
    // Getters and Setters
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    
    public int getCurrentOccupants() { return currentOccupants; }
    
    public double getPricePerMonth() { return pricePerMonth; }
    public void setPricePerMonth(double pricePerMonth) { this.pricePerMonth = pricePerMonth; }
    
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}