package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    private String roomNumber;
    private int capacity;
    private int occupiedCount;
    private double pricePerMonth;
    private boolean isAvailable;
    private List<Student> tenants;

    public Room(String roomNumber, int capacity, double pricePerMonth) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.pricePerMonth = pricePerMonth;
        this.occupiedCount = 0;
        this.isAvailable = (capacity > 0); 
        this.tenants = new ArrayList<>();
    }

    public String getRoomNumber() {
         return roomNumber; 
    }
    public void setRoomNumber(String roomNumber){
        this.roomNumber = roomNumber;
    }
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    public int getCapacity() { 
        return capacity; 
    }
    public void setOccupiedCount(int occupiedCount){
        this.occupiedCount = occupiedCount;

    }
    public int getOccupiedCount() {
         return occupiedCount; 
    }
    public void setPricePerMonth(double pricePerMonth){
        this.pricePerMonth = pricePerMonth;
    }
    public double getPricePerMonth() {
         return pricePerMonth; 
    }
  
    public boolean isAvailable() { 
        return occupiedCount < capacity; 
    }
    public void setTenants(List<Student> tenants){
        this.tenants = tenants;
    }
    
    public List<Student> getTenants() { 
        return tenants; 
    }

    // Methods
    public boolean book(Student student) { 
        if (occupiedCount < capacity) {
            tenants.add(student); // add method to add student sa tenants list
            occupiedCount++;

            if (occupiedCount == capacity) {
                isAvailable = false; 
            }
            return true;
        }
        return false;
    }

    public void vacate() {
        tenants.clear();
        occupiedCount = 0;
        isAvailable = true;
    }

    public String getOccupancyStatus() {
        return occupiedCount + "/" + capacity + " occupants";
    }

    @Override
    public String toString() {
        return "Room " + roomNumber +
                " | Price (Php)" + String.format("%.2f", pricePerMonth) + "/month" +
                " | " + getOccupancyStatus() +
                " | Available: " + (isAvailable() ? "YES" : "NO");
    }
}