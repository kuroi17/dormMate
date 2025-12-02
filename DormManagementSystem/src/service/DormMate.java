package service;


import util.InputValidator;
import java.util.InputMismatchException;
import ui.Main;
import java.util.Scanner;

public class DormMate {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // Initialize data from Main class
        Main.initializeData();
        StudentMenu studentMenu = new StudentMenu();
        LandlordMenu landlordMenu = new LandlordMenu();
        
        System.out.println("=== WELCOME TO DormMate! ===");

        boolean exit = false;
        while (!exit) {
            System.out.println("\nAre you a:\n1. Student\n2. Landlord\n3. Exit");
            System.out.print("Enter choice: ");
            
            try {
                int choice = input.nextInt();
                input.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> studentMenu.start(input);
                    case 2 -> landlordMenu.start(input);
                    case 3 -> {
                        exit = true;
                        System.out.println("Exiting DormMate. Goodbye!");
                    }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (InputMismatchException e) { // runtime exception for mismatch input types
                System.out.println("Invalid input! Please enter a number.");
                input.nextLine(); // clear buffer
            }
        }

        input.close();
    }   
   
}