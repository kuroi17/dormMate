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
            System.out.println("2. Add new dorm");
            System.out.println("3. Add room to dorm");
            System.out.println("4. View inquiries");
            System.out.println("5. Respond to inquiry");
            System.out.println("6. View all bookings");
            System.out.println("7. Display info");
            System.out.println("8. Logout");
            System.out.print("Choice: ");
            
            try {
                int lChoice = input.nextInt();
                input.nextLine();

                switch (lChoice) {
                    case 1 -> viewLandlordDorms(currentLandlord);
                    case 2 -> addNewDorm(input, currentLandlord);
                    case 3 -> addRoomToDorm(input, currentLandlord);
                    case 4 -> viewInquiries(currentLandlord);
                    case 5 -> respondToInquiry(input, currentLandlord);
                    case 6 -> viewAllBookings(currentLandlord);
                    case 7 -> System.out.println(currentLandlord.displayInfo());
                    case 8 -> {
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
    String inputName = sc.nextLine();

    System.out.print("Email: ");
    String inputEmail = sc.nextLine();

    System.out.print("Phone Number: ");
    String inputPhone = sc.nextLine();

    System.out.print("Address: ");
    String inputAddress = sc.nextLine();

    System.out.print("Landlord ID: ");
    String inputLandlordID = sc.nextLine();

    Landlord newLandlord = new Landlord(inputName, inputEmail, inputPhone, inputAddress, inputLandlordID);
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
        
        for (Dorm dorm : dorms) {

            System.out.println("\nDorm: " + dorm.getDormName());
            System.out.println("Address: " + dorm.getAddress());
            System.out.println("Google Map: " + dorm.getGoogleMapLink());
            System.out.println("Total Rooms: " + dorm.getRooms().size());
            
            int available = 0;
            for (Room room : dorm.getRooms()) {
                if (room.isAvailable()) available++;
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

    private void addNewDorm(Scanner sc, Landlord landlord) {
        try {
            System.out.println("\n--- ADD NEW DORM ---");
            
            System.out.print("Dorm Name: ");
            String name = sc.nextLine();
            
            if (!InputValidator.isNotEmpty(name)) {
                InputValidator.printError("Dorm name cannot be empty!");
                return;
            }
            
            System.out.print("Address: ");
            String address = sc.nextLine();
            
            System.out.print("Google Maps Link: ");
            String mapLink = sc.nextLine();
            
            System.out.print("Description: ");
            String description = sc.nextLine();
            
            // Create new dorm with empty room list
            Dorm newDorm = new Dorm(name, new ArrayList<>(), mapLink, address, description);
            landlord.addDorm(newDorm);
            
            // Create a listing for the new dorm
            System.out.print("Available rooms count: ");
            int availableRooms = sc.nextInt();
            sc.nextLine();
            
            System.out.print("Starting price: ");
            double price = sc.nextDouble();
            sc.nextLine();
            
            if (!InputValidator.isValidPrice(price)) {
                InputValidator.printError("Invalid price!");
                return;
            }
            
            String listingID = "DL-" + System.currentTimeMillis();
            String datePosted = "2025-12-02";
            
            DormListing newListing = new DormListing(
                listingID, newDorm, landlord, datePosted, availableRooms, price
            );
            Main.listings.add(newListing);
            
            System.out.println("✓ Dorm added successfully!");
            System.out.println("Listing ID: " + listingID);
            
        } catch (Exception e) {
            System.out.println("✗ Failed to add dorm: " + e.getMessage());
            sc.nextLine();
        }
    }

    private void addRoomToDorm(Scanner sc, Landlord landlord) {
        try {
            System.out.println("\n--- ADD ROOM TO DORM ---");
            
            List<Dorm> dorms = landlord.getOwnedDorms();
            if (dorms.isEmpty()) {
                System.out.println("You have no dorms. Add a dorm first!");
                return;
            }
            
            // Display owned dorms
            System.out.println("Your dorms:");
            for (int i = 0; i < dorms.size(); i++) {
                System.out.println((i + 1) + ". " + dorms.get(i).getDormName());
            }
            
            System.out.print("Select dorm number: ");
            int dormIndex = sc.nextInt() - 1;
            sc.nextLine();
            
            if (dormIndex < 0 || dormIndex >= dorms.size()) {
                InputValidator.printError("Invalid selection!");
                return;
            }
            
            Dorm selectedDorm = dorms.get(dormIndex);
            
            System.out.print("Room Number: ");
            String roomNumber = sc.nextLine();
            
            System.out.print("Capacity: ");
            int capacity = sc.nextInt();
            sc.nextLine();
            
            System.out.print("Price per month: ");
            double price = sc.nextDouble();
            sc.nextLine();
            
            if (!InputValidator.isValidPrice(price)) {
                InputValidator.printError("Invalid price!");
                return;
            }
            
            Room newRoom = new Room(roomNumber, capacity, price);
            selectedDorm.addRoom(newRoom);
            
            System.out.println("✓ Room added successfully to " + selectedDorm.getDormName());
            
        } catch (Exception e) {
            System.out.println("✗ Failed to add room: " + e.getMessage());
            sc.nextLine();
        }
    }

    private void respondToInquiry(Scanner sc, Landlord landlord) {
        try {
            System.out.println("\n--- RESPOND TO INQUIRY ---");
            
            List<Inquiry> landlordInquiries = new ArrayList<>();
            for (Inquiry inq : Main.inquiries) {
                if (inq.getListing().getLandlord().getLandlordID().equals(landlord.getLandlordID())) {
                    landlordInquiries.add(inq);
                }
            }
            
            if (landlordInquiries.isEmpty()) {
                System.out.println("No inquiries to respond to.");
                return;
            }
            
            // Display inquiries with numbers
            for (int i = 0; i < landlordInquiries.size(); i++) {
                Inquiry inq = landlordInquiries.get(i);
                System.out.println("\n" + (i + 1) + ". From: " + inq.getStudent().getfullName());
                System.out.println("   Message: " + inq.getMessage());
                System.out.println("   Date: " + inq.getDateSent());
            }
            
            System.out.print("\nSelect inquiry number to respond: ");
            int inquiryIndex = sc.nextInt() - 1;
            sc.nextLine();
            
            if (inquiryIndex < 0 || inquiryIndex >= landlordInquiries.size()) {
                InputValidator.printError("Invalid selection!");
                return;
            }
            
            Inquiry selectedInquiry = landlordInquiries.get(inquiryIndex);
            
            System.out.print("Your response: ");
            String response = sc.nextLine();
            
            if (!InputValidator.isNotEmpty(response)) {
                InputValidator.printError("Response cannot be empty!");
                return;
            }
            
            System.out.println("\n✓ Response sent to " + selectedInquiry.getStudent().getfullName());
            System.out.println("Response: " + response);
            System.out.println("(In a real system, this would be sent via email/notification)");
            
        } catch (Exception e) {
            System.out.println("✗ Failed to respond: " + e.getMessage());
            sc.nextLine();
        }
    }

    private void viewAllBookings(Landlord landlord) {
        System.out.println("\n--- ALL BOOKINGS ---");
        
        boolean hasBookings = false;
        
        for (Dorm dorm : landlord.getOwnedDorms()) {
            for (Room room : dorm.getRooms()) {
                if (!room.isAvailable()) {
                    // Find which student booked this room
                    for (Student student : Main.students) {
                        if (student.isRenting() && student.getCurrentRoom() != null 
                            && student.getCurrentRoom().getRoomNumber().equals(room.getRoomNumber())) {
                            
                            hasBookings = true;
                            System.out.println("\n--- Booking Details ---");
                            System.out.println("Dorm: " + dorm.getDormName());
                            System.out.println("Room: " + room.getRoomNumber());
                            System.out.println("Student: " + student.getfullName());
                            System.out.println("Contact: " + student.getContactNumber());
                            System.out.println("Lease: " + student.getLeaseStartDate() + " to " + student.getLeaseEndDate());
                            System.out.println("Monthly Rent: ₱" + student.getMonthlyRent());
                            System.out.println("Payment Status: " + student.getPaymentStatus());
                        }
                    }
                }
            }
        }
        
        if (!hasBookings) {
            System.out.println("No bookings yet.");
        }
    }
}