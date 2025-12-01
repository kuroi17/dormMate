package service;

import model.*;
import util.InputValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * DormMATE - Main Service Layer (CLEAN VERSION)
 * Now with proper separation of concerns!
 */
public class dormMate {
    
    // Data storage
    private static List<Student> students = new ArrayList<>();
    private static List<Landlord> landlords = new ArrayList<>();
    private static List<DormListing> listings = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        initializeSampleData();
        
        boolean running = true;
        while (running) {
            printHeader("DORMMATE - MAIN MENU");
            System.out.println("1. Student Portal");
            System.out.println("2. Landlord Portal");
            System.out.println("3. View All Listings");
            System.out.println("4. OOP Demo");
            System.out.println("5. Exit");
            printLine();
            
            System.out.print("Choose option: ");
            String choice = input.nextLine();
            
            switch (choice) {
                case "1": studentMenu(); break;
                case "2": landlordMenu(); break;
                case "3": viewAllListings(); break;
                case "4": runOOPDemo(); break;
                case "5": 
                    System.out.println("\n👋 Thank you for using DormMATE!");
                    running = false;
                    break;
                default: printError("Invalid option!");
            }
        }
    }
    
    // ============================================================
    // INITIALIZATION
    // ============================================================
    
    private static void initializeSampleData() {
        try {
            Landlord landlord = new Landlord(
                "Pedro Santos", "pedro@dormmate.com", 
                "09171234567", "Batangas City", "LL-001"
            );
            
            List<Room> rooms = new ArrayList<>();
            rooms.add(new Room("101", 2, 3000.0));
            rooms.add(new Room("102", 4, 5000.0));
            
            Dorm dorm = new Dorm(
                "Nueva Villa", rooms, 
                "https://maps.google.com/nuevavilla",
                "123 P. Burgos St, Batangas City",
                "Clean and safe dorm near BatStateU"
            );
            
            landlord.addDorm(dorm);
            
            DormListing listing = new DormListing(
                "DL-001", dorm, landlord, "2025-11-26", 2, 3000.0
            );
            
            landlords.add(landlord);
            listings.add(listing);
            
        } catch (Exception e) {
            System.err.println("Error initializing data: " + e.getMessage());
        }
    }
    
    // ============================================================
    // STUDENT MENU (SIMPLIFIED)
    // ============================================================
    
    private static void studentMenu() {
        printHeader("STUDENT PORTAL");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Back");
        printLine();
        
        System.out.print("Choose: ");
        String choice = input.nextLine();
        
        switch (choice) {
            case "1": registerStudent(); break;
            case "2": loginStudent(); break;
            case "3": return;
        }
    }
    
    private static void registerStudent() {
        printHeader("STUDENT REGISTRATION");
        
        try {
            System.out.print("Full Name: ");
            String name = input.nextLine();
            
            System.out.print("Email: ");
            String email = input.nextLine();
            
            System.out.print("Contact: ");
            String contact = input.nextLine();
            
            System.out.print("Address: ");
            String address = input.nextLine();
            
            System.out.print("Student ID: ");
            String studentID = input.nextLine();
            
            System.out.print("University: ");
            String university = input.nextLine();
            
            System.out.print("Budget (₱): ");
            double budget = Double.parseDouble(input.nextLine());
            
            // Validation
            if (!InputValidator.isValidEmail(email)) {
                throw new IllegalArgumentException("Invalid email!");
            }
            if (!InputValidator.isValidContact(contact)) {
                throw new IllegalArgumentException("Invalid contact!");
            }
            
            Student student = new Student(name, email, contact, address, 
                                        studentID, university, budget);
            students.add(student);
            
            printSuccess("Registration successful!");
            System.out.println(student.displayInfo());
            pause();
            
            studentDashboard(student);
            
        } catch (Exception e) {
            printError(e.getMessage());
        }
    }
    
    private static void loginStudent() {
        if (students.isEmpty()) {
            printError("No registered students.");
            return;
        }
        
        System.out.println("\nStudents:");
        for (int i = 0; i < students.size(); i++) {
            System.out.printf("[%d] %s\n", i + 1, students.get(i).getfullName());
        }
        
        System.out.print("\nSelect: ");
        try {
            int idx = Integer.parseInt(input.nextLine()) - 1;
            if (idx >= 0 && idx < students.size()) {
                studentDashboard(students.get(idx));
            }
        } catch (NumberFormatException e) {
            printError("Invalid input!");
        }
    }
    
    private static void studentDashboard(Student student) {
        boolean active = true;
        
        while (active) {
            printHeader("DASHBOARD - " + student.getfullName());
            System.out.println("1. My Info");
            System.out.println("2. Browse Dorms");
            System.out.println("3. Book Room");
            System.out.println("4. Pay Rent");
            System.out.println("5. Vacate Room");
            System.out.println("6. Logout");
            printLine();
            
            System.out.print("Choose: ");
            String choice = input.nextLine();
            
            switch (choice) {
                case "1":
                    System.out.println(student.displayInfo());
                    pause();
                    break;
                case "2":
                    viewAllListings();
                    pause();
                    break;
                case "3":
                    bookRoomInteractive(student);
                    break;
                case "4":
                    student.payRent(); // Clean! Logic in Student class
                    pause();
                    break;
                case "5":
                    student.vacateRoom(); // Clean! Logic in Student class
                    pause();
                    break;
                case "6":
                    active = false;
                    break;
            }
        }
    }
    
    private static void bookRoomInteractive(Student student) {
        if (listings.isEmpty()) {
            printError("No listings available!");
            return;
        }
        
        viewAllListings();
        
        System.out.print("\nSelect listing #: ");
        try {
            int idx = Integer.parseInt(input.nextLine()) - 1;
            if (idx >= 0 && idx < listings.size()) {
                Dorm dorm = listings.get(idx).getDorm();
                List<Room> available = dorm.getAvailableRooms();
                
                if (available.isEmpty()) {
                    printError("No rooms available!");
                    return;
                }
                
                System.out.println("\nAvailable Rooms:");
                for (int i = 0; i < available.size(); i++) {
                    Room r = available.get(i);
                    System.out.printf("[%d] Room %s - ₱%.2f\n", 
                        i + 1, r.getRoomNumber(), r.getPricePerMonth());
                }
                
                System.out.print("\nSelect room: ");
                int roomIdx = Integer.parseInt(input.nextLine()) - 1;
                
                if (roomIdx >= 0 && roomIdx < available.size()) {
                    Room room = available.get(roomIdx);
                    
                    System.out.print("Start date (YYYY-MM-DD): ");
                    String start = input.nextLine();
                    
                    System.out.print("End date (YYYY-MM-DD): ");
                    String end = input.nextLine();
                    
                    // Logic now in Student class! Clean!
                    student.bookRoom(room, start, end, room.getPricePerMonth());
                    pause();
                }
            }
        } catch (NumberFormatException e) {
            printError("Invalid input!");
        }
    }
    
    // ============================================================
    // LANDLORD MENU (SIMPLIFIED)
    // ============================================================
    
    private static void landlordMenu() {
        printHeader("LANDLORD PORTAL");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Back");
        printLine();
        
        System.out.print("Choose: ");
        String choice = input.nextLine();
        
        switch (choice) {
            case "1": registerLandlord(); break;
            case "2": loginLandlord(); break;
        }
    }
    
    private static void registerLandlord() {
        printHeader("LANDLORD REGISTRATION");
        
        try {
            System.out.print("Name: ");
            String name = input.nextLine();
            System.out.print("Email: ");
            String email = input.nextLine();
            System.out.print("Contact: ");
            String contact = input.nextLine();
            System.out.print("Address: ");
            String address = input.nextLine();
            System.out.print("Landlord ID: ");
            String id = input.nextLine();
            
            Landlord landlord = new Landlord(name, email, contact, address, id);
            landlords.add(landlord);
            
            printSuccess("Registration successful!");
            System.out.println(landlord.displayInfo());
            pause();
            
        } catch (Exception e) {
            printError(e.getMessage());
        }
    }
    
    private static void loginLandlord() {
        if (landlords.isEmpty()) {
            printError("No landlords registered.");
            return;
        }
        
        for (int i = 0; i < landlords.size(); i++) {
            System.out.printf("[%d] %s\n", i + 1, landlords.get(i).getfullName());
        }
        
        System.out.print("\nSelect: ");
        try {
            int idx = Integer.parseInt(input.nextLine()) - 1;
            if (idx >= 0 && idx < landlords.size()) {
                Landlord landlord = landlords.get(idx);
                landlord.viewMyDorms(); // Logic in Landlord class!
                pause();
            }
        } catch (NumberFormatException e) {
            printError("Invalid!");
        }
    }
    
    // ============================================================
    // VIEW LISTINGS
    // ============================================================
    
    private static void viewAllListings() {
        printHeader("AVAILABLE LISTINGS");
        
        if (listings.isEmpty()) {
            System.out.println("No listings yet.");
            return;
        }
        
        for (int i = 0; i < listings.size(); i++) {
            System.out.printf("[%d] %s\n", i + 1, listings.get(i).getListingSummary());
        }
        printLine();
    }
    
    // ============================================================
    // OOP DEMO
    // ============================================================
    
    private static void runOOPDemo() {
        printHeader("OOP DEMONSTRATION");
        
        System.out.println("✅ Testing Polymorphism:\n");
        if (!students.isEmpty()) System.out.println(students.get(0).displayInfo());
        if (!landlords.isEmpty()) System.out.println(landlords.get(0).displayInfo());
        
        System.out.println("\n✅ Testing Encapsulation:");
        System.out.println("All fields are private with getters/setters!");
        
        System.out.println("\n✅ Testing Inheritance:");
        System.out.println("Student & Landlord both extend Person!");
        
        System.out.println("\n✅ Testing Abstraction:");
        System.out.println("Person is abstract, complex logic hidden in classes!");
        
        pause();
    }
    
    // ============================================================
    // UI HELPERS
    // ============================================================
    
    private static void printHeader(String title) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("  " + title);
        System.out.println("=".repeat(60));
    }
    
    private static void printLine() {
        System.out.println("-".repeat(60));
    }
    
    private static void printSuccess(String msg) {
        System.out.println("\n✅ " + msg);
    }
    
    private static void printError(String msg) {
        System.out.println("\n❌ " + msg);
    }
    
    private static void pause() {
        System.out.print("\nPress Enter...");
        input.nextLine();
    }
}