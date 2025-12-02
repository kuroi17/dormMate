package service;

import model.*;
import util.InputValidator;
import java.util.InputMismatchException;
import ui.Main;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LandlordMenu {
    // ========== LANDLORD MENU ==========
    public void start(Scanner input) {
        System.out.println("\n--- LANDLORD LOGIN ---");
        System.out.print("Enter your landlord ID: ");
        String lid = input.nextLine();
        
        Landlord currentLandlord = null;
        for (Landlord landlord : Main.landlords) {
            if (landlord.getLandlordID().equals(lid)) {
                currentLandlord = landlord;
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
                int lChoice = input.nextInt();
                input.nextLine();

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
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please try again.");
                input.nextLine();
            }
        }
    }

    public void portal(Scanner input) {
    boolean exitPortal = false;

    while (!exitPortal) {
        System.out.println("\n--- LANDLORD PORTAL ---");
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.println("3. Back");
        System.out.print("Choice: ");

        try {
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> start(input);  // existing login
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

    // ========== LANDLORD FUNCTIONS ==========

    private void signUp(Scanner sc) {
    System.out.println("\n--- LANDLORD SIGN UP ---");

    System.out.print("Full Name: ");
    String name = sc.nextLine();

    System.out.print("Email: ");
    String email = sc.nextLine();

    System.out.print("Phone Number: ");
    String phone = sc.nextLine();

    System.out.print("Address: ");
    String address = sc.nextLine();

    System.out.print("Landlord ID: ");
    String lid = sc.nextLine();

    Landlord newLandlord = new Landlord(name, email, phone, address, lid);
    Main.landlords.add(newLandlord);

    System.out.println("Landlord account created successfully!");
}

    private void viewLandlordDorms(Landlord landlord) {
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
    
    private void viewInquiries(Landlord landlord) {
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