package service;

import model.*;
import util.InputValidator;
import ui.Main;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class dormMate {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Initialize data from Main class
        Main.initializeData();
        
        System.out.println("=== WELCOME TO DORMMATE ===");

        boolean exit = false;
        while (!exit) {
            System.out.println("\nAre you a:\n1. Student\n2. Landlord\n3. Exit");
            System.out.print("Enter choice: ");
            
            try {
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> handleStudentMenu(sc);
                    case 2 -> handleLandlordMenu(sc);
                    case 3 -> {
                        exit = true;
                        System.out.println("Exiting DormMATE. Goodbye!");
                    }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine(); // clear buffer
            }
        }

        sc.close();
    }
    
    // ========== STUDENT MENU ==========
    private static void handleStudentMenu(Scanner sc) {
        System.out.println("\n--- STUDENT LOGIN ---");
        System.out.print("Enter your student ID: ");
        String sid = sc.nextLine();
        
        Student currentStudent = null;
        for (Student s : Main.students) {
            if (s.getStudentID().equals(sid)) {
                currentStudent = s;
                break;
            }
        }
        
        if (currentStudent == null) {
            System.out.println("Student not found!");
            return;
        }
        
        System.out.println("Welcome, " + currentStudent.getfullName() + "!");

        boolean studentExit = false;
        while (!studentExit) {
            System.out.println("\n--- STUDENT MENU ---");
            System.out.println("1. Browse listings");
            System.out.println("2. Inquire about a dorm");
            System.out.println("3. Book a room");
            System.out.println("4. Pay rent");
            System.out.println("5. Display info");
            System.out.println("6. Logout");
            System.out.print("Choice: ");
            
            try {
                int sChoice = sc.nextInt();
                sc.nextLine();

                switch (sChoice) {
                    case 1 -> browseListing();
                    case 2 -> inquireDorm(sc, currentStudent);
                    case 3 -> bookRoom(sc, currentStudent);
                    case 4 -> currentStudent.payRent();
                    case 5 -> System.out.println(currentStudent.displayInfo());
                    case 6 -> {
                        studentExit = true;
                        System.out.println("Logged out successfully!");
                    }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please try again.");
                sc.nextLine();
            }
        }
    }
    
    // ========== LANDLORD MENU ==========
    private static void handleLandlordMenu(Scanner sc) {
        System.out.println("\n--- LANDLORD LOGIN ---");
        System.out.print("Enter your landlord ID: ");
        String lid = sc.nextLine();
        
        Landlord currentLandlord = null;
        for (Landlord l : Main.landlords) {
            if (l.getLandlordID().equals(lid)) {
                currentLandlord = l;
                break;
            }
        }
        
        if (currentLandlord == null) {
            System.out.println("Landlord not found!");
            return;
        }
        
        System.out.println("Welcome, " + currentLandlord.getfullName() + "!");

        boolean landlordExit = false;
        while (!landlordExit) {
            System.out.println("\n--- LANDLORD MENU ---");
            System.out.println("1. View my dorms");
            System.out.println("2. View inquiries");
            System.out.println("3. Display info");
            System.out.println("4. Logout");
            System.out.print("Choice: ");
            
            try {
                int lChoice = sc.nextInt();
                sc.nextLine();

                switch (lChoice) {
                    case 1 -> viewLandlordDorms(currentLandlord);
                    case 2 -> viewInquiries(currentLandlord);
                    case 3 -> System.out.println(currentLandlord.displayInfo());
                    case 4 -> {
                        landlordExit = true;
                        System.out.println("Logged out successfully!");
                    }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please try again.");
                sc.nextLine();
            }
        }
    }
    
    // ========== STUDENT FUNCTIONS ==========
    
    private static void browseListing() {
        System.out.println("\n--- AVAILABLE LISTINGS ---");
        if (Main.listings.isEmpty()) {
            System.out.println("No listings available.");
            return;
        }
        
        for (DormListing l : Main.listings) {
            System.out.println("\nListing ID: " + l.getListingID());
            System.out.println("Dorm: " + l.getDorm().getDormName());
            System.out.println("Address: " + l.getDorm().getAddress());
            System.out.println("Available Rooms: " + l.getAvailableRooms());
            System.out.println("Monthly Rate: ₱" + l.getPriceRange());
            System.out.println("Description: " + l.getDorm().getShortDescription());
        }
    }
    
    private static void inquireDorm(Scanner sc, Student student) {
        System.out.print("Enter listing ID to inquire: ");
        String lid = sc.nextLine();
        
        DormListing selected = null;
        for (DormListing l : Main.listings) {
            if (l.getListingID().equals(lid)) {
                selected = l;
                break;
            }
        }
        
        if (selected != null) {
            System.out.print("Enter your message: ");
            String msg = sc.nextLine();
            
            try {
                Inquiry inquiry = new Inquiry(
                    "INQ-" + System.currentTimeMillis(),
                    student,
                    selected,
                    msg,
                    "2025-12-01"
                );
                Main.inquiries.add(inquiry);
                System.out.println("✓ Inquiry sent successfully!");
            } catch (Exception e) {
                System.out.println("✗ Failed to send inquiry: " + e.getMessage());
            }
        } else {
            System.out.println("Listing not found!");
        }
    }
    
    private static void bookRoom(Scanner sc, Student student) {
        System.out.print("Enter room number to book: ");
        String rn = sc.nextLine();
        
        Room selected = null;
        for (DormListing l : Main.listings) {
            for (Room r : l.getDorm().getRooms()) {
                if (r.getRoomNumber().equals(rn)) {
                    selected = r;
                    break;
                }
            }
            if (selected != null) break;
        }
        
        if (selected == null) {
            System.out.println("Room not found!");
            return;
        }
        
        if (!selected.isAvailable()) {
            System.out.println("Room is not available!");
            return;
        }
        
        try {
            System.out.print("Enter start date (YYYY-MM-DD): ");
            String startDate = sc.nextLine();
            InputValidator.validateDate(startDate);
            
            System.out.print("Enter end date (YYYY-MM-DD): ");
            String endDate = sc.nextLine();
            InputValidator.validateDate(endDate);
            
            student.bookRoom(selected, startDate, endDate, selected.getPricePerMonth());
            System.out.println("✓ Room booked successfully!");
            
        } catch (Exception e) {
            System.out.println("✗ Booking failed: " + e.getMessage());
        }
    }
    
    // ========== LANDLORD FUNCTIONS ==========
    
    private static void viewLandlordDorms(Landlord landlord) {
        System.out.println("\n--- MY DORMS ---");
        List<Dorm> dorms = landlord.getOwnedDorms();
        
        if (dorms.isEmpty()) {
            System.out.println("You have no dorms listed.");
            return;
        }
        
        for (Dorm d : dorms) {
            System.out.println("\nDorm: " + d.getDormName());
            System.out.println("Address: " + d.getAddress());
            System.out.println("Total Rooms: " + d.getRooms().size());
            
            int available = 0;
            for (Room r : d.getRooms()) {
                if (r.isAvailable()) available++;
            }
            System.out.println("Available Rooms: " + available);
        }
    }
    
    private static void viewInquiries(Landlord landlord) {
        System.out.println("\n--- INQUIRIES ---");
        
        List<Inquiry> landlordInquiries = new ArrayList<>();
        for (Inquiry inq : Main.inquiries) {
            if (inq.getListing().getLandlord().getLandlordID().equals(landlord.getLandlordID())) {
                landlordInquiries.add(inq);
            }
        }
        
        if (landlordInquiries.isEmpty()) {
            System.out.println("No inquiries yet.");
            return;
        }
        
        landlord.viewInquiries(landlordInquiries);
    }
}