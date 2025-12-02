package ui;

import java.util.ArrayList;
import java.util.List;
import model.*;
public class Main {
    
    // Make these PUBLIC and STATIC so dormMate can access them
    public static List<Student> students = new ArrayList<>();
    public static List<Landlord> landlords = new ArrayList<>();
    public static List<DormListing> listings = new ArrayList<>();
    public static List<Inquiry> inquiries = new ArrayList<>();

    // Initialize data method
    public static void initializeData() {
        // Sample students
        Student student1 = new Student
        ("Juan Dela Cruz", 
        "juan.delacruz@gmail.com",
        "09123456789",
        "Manila, Philippines",
        "2021-12345",
        "Batangas State University", 
        5000.0);

        Student student2 = new Student(
        "Maria Santos", 
        "maria.santos@gmail.com", 
        "09198765432",
        "Quezon City, Philippines", 
        "2021-67890", 
        "University of the Philippines", 
        7000.0);

        students.add(student1);
        students.add(student2);

        // Sample landlords
        Landlord landlord1 = new Landlord(
        "Pedro Santos", 
        "pedro.santos@gmail.com", 
        "09876543210",
        "Cebu, Philippines",
        "LL-001");

        Landlord landlord2 = new Landlord
        ("Ana Reyes", 
        "ana.reyesgemail.com", 
        "09171234567",
        "Batangas City, Philippines",
        "LL-002");
        landlords.add(landlord1);
        landlords.add(landlord2);

        // Sample rooms
        Room room1 = new Room("101", 2, 3000.0);
        Room room2 = new Room("102", 4, 5000.0);
        Room room3 = new Room("201", 2, 3500.0);

        // Sample dorms
        List<Room> rooms1 = new ArrayList<>();
        rooms1.add(room1);
        rooms1.add(room2);

        List<Room> rooms2 = new ArrayList<>();
        rooms2.add(room3);

        Dorm dorm1 = new Dorm(
        "Nueva Villa", 
        rooms1,
        "https://maps.google.com/nuevavilla",
        "123 Dorm St, Batangas City", 
        "A cozy dormitory near BatStateU.");
        
        Dorm dorm2 = new Dorm(
        "Sunshine Dorms", 
        rooms2, 
        "https://maps.google.com/sunshine",
        "456 Campus Ave, Batangas City", 
        "Modern dorm with free Wi-Fi.");

        // Link dorms to landlords
        landlord1.addDorm(dorm1);
        landlord2.addDorm(dorm2);

        // Sample dorm listings
        DormListing listing1 = new DormListing(
                "DL-001",
                dorm1, 
                landlord1,
                "2025-11-26",
                2,
                3000.0);
        DormListing listing2 = new DormListing(
                "DL-002",
                dorm2, 
                landlord2, 
                "2025-11-26",
                1,
                3500.0);
                
        listings.add(listing1);
        listings.add(listing2);
    }
}