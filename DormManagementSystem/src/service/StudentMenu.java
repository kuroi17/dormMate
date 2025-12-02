package service;

import model.*;
import util.InputValidator;
import java.util.InputMismatchException;
import ui.Main;
import java.util.Scanner;

// ========== STUDENT MENU ==========
public class StudentMenu {
    public void start(Scanner input){
        System.out.println("\n--- STUDENT LOGIN ---");
        System.out.print("Enter your student ID: ");
        String sid = input.nextLine();
        
        Student currentStudent = null;
        for (Student student : Main.students) {
            if (student.getStudentID().equals(sid)) {
                currentStudent = student;
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
                int studentChoice = input.nextInt();
                input.nextLine();

                switch (studentChoice) {
                    case 1 -> browseListing();
                    case 2 -> inquireDorm(input, currentStudent);
                    case 3 -> bookRoom(input, currentStudent);
                    case 4 -> currentStudent.payRent();
                    case 5 -> System.out.println(currentStudent.displayInfo());
                    case 6 -> {
                        studentExit = true;
                        System.out.println("Logged out successfully!");
                    }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please try again.");
                input.nextLine();
            }
        }
    }

    // ========== STUDENT FUNCTIONS ==========

    private void browseListing() {
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

private void inquireDorm(Scanner sc, Student student) {
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

    private void bookRoom(Scanner sc, Student student) {
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

    
}
