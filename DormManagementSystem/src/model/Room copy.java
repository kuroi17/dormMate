// /*
//  * 👤 MEMBER 2 - Cole
//  * 
//  * File: DormManagementSystem/src/model/Room.java
//  *
//  * Instructions:
//  * - Replace [MEMBER 2 NAME] with your name
//  * - Follow the TODO hints inside this file
//  * - Fill in the blanks where indicated
//  * - After finishing, run the following git commands:
//  *      1. git pull
//  *      2. ctrl + j (open terminal)
//  *      3. git add .
//  *      4. git commit -m "Add Room.java by [MEMBER 2 NAME]"
//  *      5. git push
//  * - Message Jyvhan when done!
//  *
//  * Notes:
//  * - Simple class, no extends
//  * - Fill in the parts marked with TODO
//  */

// package model;

// import java.util.List;
// import java.util.ArrayList; 
// public class Room {

//     // Attributes
//     private String roomNumber;      // TODO: add your attribute here
//     private int capacity;           // TODO: add your attribute here
//     private int occupiedCount;      // TODO: initialize in constructor
//     private double pricePerMonth;   // TODO: add your attribute here
//     private List<Student> tenants;  // TODO: initialize in constructor

//     // Constructor
//     public Room(String roomNumber, int capacity, double pricePerMonth) {
//         // TODO: initialize roomNumber (yung may mga this. sa unahan)
//         // TODO: initialize capacity  (yung may mga this. sa unahan)
//         // TODO: initialize pricePerMonth  (yung may mga this. sa unahan)
//         // TODO: initialize occupiedCount  (yung may mga this. sa unahan)
//         // TODO: initialize tenants as empty list  (yung may mga this. sa unahan)
//     }

//     // getters and setters po from enscapsulation lesson
//     public String getRoomNumber() {
//         // TODO: return roomNumber
//     }

//     public int getCapacity() {
//         // TODO: return capacity
//     }

//     public int getOccupiedCount() {
//         // TODO: return occupiedCount
//     }

//     public double getPricePerMonth() {
//         // TODO: return pricePerMonth
//     }

//     public List<Student> getTenants() {
//         // TODO: return tenants
//     }

//     // Methods
//     public boolean isAvailable() {
//         // TODO: return true if occupiedCount < capacity
//     }

//     public boolean book(Student student) {
//         // TODO: if available, add student to tenants,
//         //increment occupiedCount, return true
//         // else return false
//     }

//     public void vacate() {
//         // TODO: clear tenants list and set occupiedCount to 0
//     }

//     public String getOccupancyStatus() {
//         // TODO: return a string like "occupiedCount / capacity occupied"
//     }
// }

package model;
import java.util.ArrayList;
import java.util.List;

public class Room {
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

    public String getRoomNumber() { return roomNumber; }
    public int getCapacity() { return capacity; }
    public int getOccupiedCount() { return occupiedCount; }
    public double getPricePerMonth() { return pricePerMonth; }

    public boolean isAvailable() { 
        return occupiedCount < capacity; 
    }
    
    public List<Student> getTenants() { return tenants; }

    public boolean book(Student student) {
        if (occupiedCount < capacity) {
            tenants.add(student);
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
                " | Price ₱" + String.format("%.2f", pricePerMonth) + "/month" +
                " | " + getOccupancyStatus() +
                " | Available: " + (isAvailable() ? "YES" : "NO");
    }
}