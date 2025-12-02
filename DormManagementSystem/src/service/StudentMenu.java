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
                    case 4 -> payRent(input, currentStudent);
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

    public void portal(Scanner input) {
    boolean exitPortal = false;

    while (!exitPortal) {
        System.out.println("\n--- STUDENT PORTAL ---");
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.println("3. Back");
        System.out.print("Choice: ");

        try {
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> start(input);  // existing login method
                case 2 -> signUp(input);
                case 3 -> exitPortal = true;
                default -> System.out.println("Invalid choice!");
            }
        } catch (Exception e) {
            System.out.println("Invalid input! Try again.");
            input.nextLine();
        }
    }
}


    // ========== STUDENT FUNCTIONS ==========

    private void signUp(Scanner sc) {
    System.out.println("\n--- STUDENT SIGN UP ---");

    System.out.print("Full Name: ");
    String nameInput = sc.nextLine();

    System.out.print("Email: ");
    String emailInput = sc.nextLine();

    System.out.print("Phone Number: ");
    String phoneInput = sc.nextLine();

    System.out.print("Address: ");
    String addressInput = sc.nextLine();

    System.out.print("Student ID: ");
    String studentidInput = sc.nextLine();

    System.out.print("School/University: ");
    String schoolInput = sc.nextLine();

    System.out.print("Budget (Monthly): ");
    double budgetInput = sc.nextDouble();
    sc.nextLine();

    Student newStudent = new Student(
        nameInput, emailInput, phoneInput, addressInput, 
        studentidInput, schoolInput, budgetInput);
    Main.students.add(newStudent);

    System.out.println("✓ Account created successfully!");
}



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
        System.out.println("\n---AVAILABLE DORM LISTINGS---");
        for (DormListing dormlisting: Main.listings){
            System.out.println("Available Listing ID: " +
             dormlisting.getListingID());
        }
        try {
            System.out.print("\nEnter listing ID to inquire: ");
            String input = sc.nextLine();
            
            DormListing selected = null;
            for (DormListing dormlisting : Main.listings) {
                if (dormlisting.getListingID().equals(input)) {
                    selected = dormlisting;
                    break;
                }
            }
            
            if (selected == null) {
                InputValidator.printError("Listing not found!");
                return;
            }
            
            System.out.print("Enter your message: ");
            String msg = sc.nextLine();
            
            if (!InputValidator.isNotEmpty(msg)) {
                InputValidator.printError("Message cannot be empty!");
                return;
            }
            
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
    }

        private void bookRoom(Scanner sc, Student student) {
             System.out.println("\n---AVAILABLE ROOMS---");
            for (DormListing dormlisting: Main.listings){
                for (Room room : dormlisting.getDorm().getRooms()){
                    System.out.println("Available Room Number: " +
                     room.getRoomNumber());
                }
            }
            try {
            System.out.print("\nEnter room number to book: ");
            String input = sc.nextLine();
            
            Room selected = null;
            for (DormListing dormlisting : Main.listings) {
                for (Room room : dormlisting.getDorm().getRooms()) {
                    if (room.getRoomNumber().equals(input)) {
                        selected = room;
                        break;
                    }
                }
                if (selected != null) break;
            }
            
            if (selected == null) {
                InputValidator.printError("Room not found!");
                return;
            }
            
            if (!selected.isAvailable()) {
                InputValidator.printError("Room is not available!");
                return;
            }
            
            System.out.print("Enter start date (YYYY-MM-DD): ");
            String startDate = sc.nextLine();
            
            if (!InputValidator.isValidDate(startDate)) {
                InputValidator.printError("Invalid start date format!");
                return;
            }
            
            System.out.print("Enter end date (YYYY-MM-DD): ");
            String endDate = sc.nextLine();
            
            if (!InputValidator.isValidDate(endDate)) {
                InputValidator.printError("Invalid end date format!");
                return;
            }
            
            student.bookRoom(selected, startDate, endDate, selected.getPricePerMonth());
            System.out.println("✓ Room booked successfully!");
            
        } catch (Exception e) {
            System.out.println("✗ Booking failed: " + e.getMessage());
        }
    }

    private void payRent(Scanner sc, Student student) {
        try {
            System.out.println("\n--- PAY RENT ---");
            
            if (!student.isRenting()) {
                System.out.println("You are not currently renting a room.");
                return;
            }
            
            System.out.println("Current room: " + student.getCurrentRoom().getRoomNumber());
            System.out.println("Monthly rent: ₱" + String.format("%.2f", student.getMonthlyRent()));
            System.out.println("Current budget: ₱" + String.format("%.2f", student.getBudget()));
            
            System.out.print("\nDo you want to pay rent? (yes/no): ");
            String choice = sc.nextLine().trim().toLowerCase();
            
            if (choice.equals("yes") || choice.equals("y") || choice.equals("Yes") || choice.equals("Y") || choice.equals("YES")) {
                student.payRent();
            } else {
                System.out.println("Payment cancelled.");
            }
            
        } catch (Exception e) {
            System.out.println("✗ Payment failed: " + e.getMessage());
        }
    }

    
}
