package service;
import model.*;
import java.util.ArrayList;
import java.util.List;

public class dormMate {
    public static void main(String[] args) {
        
        System.out.println("=== TESTING DormMATE SYSTEM ===\n");
        
        // ============ CREATE OBJECTS ============
        
        // Student object
        Student student1 = new Student(
            "Juan Dela Cruz",              
            "juan.delacruz@email.com",      
            "09123456789",               
            "Manila, Philippines",          
            "2021-12345",                  
            "Batangas State University", 
            5000.00          
        );

        Student student2 = new Student(
            "Maria Santos",
            "maria.santos@email.com",
            "09198765432",
            "Quezon City, Philippines",
            "2021-67890",
            "University of the Philippines",
            7000.00
        );

        // Landlord object
        Landlord landlord1 = new Landlord(
            "Pedro Santos",
            "pedro.santos@email.com",
            "09876543210",
            "Cebu, Philippines",
            "LL-001"
        );

        Landlord landlord2 = new Landlord(
            "Ana Reyes",
            "ana.reyes@email.com",
            "09171234567",
            "Batangas City, Philippines",
            "LL-002"
        );

        //  create Rooms first
        Room room1 = new Room("101", 2, 3000.0);
        Room room2 = new Room("102", 4, 5000.0);
        Room room3 = new Room("201", 2, 3500.0);

        //  create rooms list for Dorm constructor
        List<Room> roomList1 = new ArrayList<>();
        roomList1.add(room1);
        roomList1.add(room2);

        List<Room> roomList2 = new ArrayList<>();
        roomList2.add(room3);

        //  Create Dorm with proper constructor (requires List<Room>)
        Dorm dorm1 = new Dorm(
            "Nueva Villa",
            roomList1,  // ✅ Pass the rooms list
            "https://maps.google.com/nuevavilla",
            "123 Dorm St, Batangas City",
            "A cozy dormitory near BatStateU."
        );

        Dorm dorm2 = new Dorm(
            "Sunshine Dorms",
            roomList2,  // ✅ Pass the rooms list
            "https://maps.google.com/sunshine",
            "456 Campus Ave, Batangas City",
            "Modern dorm with free Wi-Fi."
        );

        // Add dorms to landlords
        landlord1.addDorm(dorm1);
        landlord2.addDorm(dorm2);

        // Create DormListings
        DormListing listing1 = new DormListing(
            "DL-001",
            dorm1,
            landlord1,
            "2025-11-26",
            2,
            3000.00
        );

        DormListing listing2 = new DormListing(
            "DL-002",
            dorm2,
            landlord2,
            "2025-11-26",
            1,
            3500.00
        );

        // ============ TEST POLYMORPHISM ============
        System.out.println("\n--- POLYMORPHISM TEST ---");
        
        Person p1 = student1;  
        Person p2 = landlord1; 
        
        System.out.println(p1.displayInfo()); // Calls students's version
        System.out.println(p2.displayInfo()); // Calls landlord's version

        // ============ TEST STUDENT METHODS ============
        System.out.println("\n--- STUDENT ACTIONS ---");
        
        student1.browseListings();
        student1.inquireRoom(listing1);
        
        // ✅ Proper bookRoom call
        student1.bookRoom(room1, "2026-01-01", "2026-12-31", 3000.0);
        System.out.println(student1.displayInfo()); //  rental info
        
        student1.payRent();
        
        // ============ TEST LANDLORD METHODS ============
        System.out.println("\n--- LANDLORD ACTIONS ---");
        
        landlord1.postDormListing(listing1);
        landlord1.updateListing(listing1, "Affordable room near campus with free Wi-Fi.");
        
        // ============ TEST INQUIRY SYSTEM ============
        System.out.println("\n--- INQUIRY TEST ---");
        
        // ✅ Create actual Inquiry objects
        Inquiry inquiry1 = new Inquiry(
            "INQ-001",
            student1,
            listing1,
            "Is the room still available?",
            "2025-11-26"
        );

        Inquiry inquiry2 = new Inquiry(
            "INQ-002",
            student2,
            listing1,
            "Can I visit the dorm tomorrow?",
            "2025-11-26"
        );

        List<Inquiry> inquiries = new ArrayList<>();
        inquiries.add(inquiry1);
        inquiries.add(inquiry2);

        landlord1.viewInquiries(inquiries);

        // ============ TEST ROOM METHODS ============
        System.out.println("\n--- ROOM BOOKING TEST ---");
        
        System.out.println("Room 102 status before booking: " + room2.getOccupancyStatus());
        room2.book(student2);
        System.out.println("Room 102 status after booking: " + room2.getOccupancyStatus());

        // ============ TEST VACATE ============
        System.out.println("\n--- VACATE TEST ---");
        student1.vacateRoom();
        System.out.println(student1.displayInfo()); // Should show no rental info

        // ============ TEST DORM INFO ============
        System.out.println("\n--- DORM INFORMATION ---");
        System.out.println("Dorm Name: " + dorm1.getDormName());
        System.out.println("Address: " + dorm1.getAddress());
        System.out.println("Total Rooms: " + dorm1.getRooms().size());
        
        // Count available rooms manually
        int availableCount = 0;
        for (Room r : dorm1.getRooms()) {
            if (r.isAvailable()) {
                availableCount++;
            }
        }
        System.out.println("Available rooms in " + dorm1.getDormName() + ": " + availableCount);

        // ============ FINAL SUMMARY ============
        System.out.println("\n=== SYSTEM TEST COMPLETE ===");
        System.out.println("✅ Encapsulation: Private fields + getters/setters");
        System.out.println("✅ Inheritance: Student & Landlord extend Person");
        System.out.println("✅ Polymorphism: displayInfo() works differently");
        System.out.println("✅ Abstraction: Person is abstract class");
    }
}