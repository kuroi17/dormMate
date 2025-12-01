package ui;

import model.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class DormMateGUI {

    public static void main(String[] args) {
        // Initialize data from Main
        Main.initializeData();

        JFrame frame = new JFrame("DormMATE System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel welcomeLabel = new JLabel("Welcome to DormMATE!", SwingConstants.CENTER);
        JButton studentBtn = new JButton("Student");
        JButton landlordBtn = new JButton("Landlord");

        frame.add(welcomeLabel);
        frame.add(studentBtn);
        frame.add(landlordBtn);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // ===== STUDENT BUTTON =====
        studentBtn.addActionListener(e -> {
            String sid = JOptionPane.showInputDialog(frame, "Enter Student ID:");
            Student currentStudent = null;
            for (Student s : Main.students) {
                if (s.getStudentID().equals(sid)) {
                    currentStudent = s;
                    break;
                }
            }
            if (currentStudent == null) {
                JOptionPane.showMessageDialog(frame, "Student not found!");
                return;
            }
            showStudentMenu(frame, currentStudent);
        });

        // ===== LANDLORD BUTTON =====
        landlordBtn.addActionListener(e -> {
            String lid = JOptionPane.showInputDialog(frame, "Enter Landlord ID:");
            Landlord currentLandlord = null;
            for (Landlord l : Main.landlords) {
                if (l.getLandlordID().equals(lid)) {
                    currentLandlord = l;
                    break;
                }
            }
            if (currentLandlord == null) {
                JOptionPane.showMessageDialog(frame, "Landlord not found!");
                return;
            }
            showLandlordMenu(frame, currentLandlord);
        });
    }

    // ===== STUDENT MENU =====
    private static void showStudentMenu(JFrame frame, Student student) {
        String[] options = {"Browse Listings", "Book Room", "Pay Rent", "Display Info", "Back"};
        boolean running = true;
        while (running) {
            int choice = JOptionPane.showOptionDialog(frame,
                    "Choose an action:",
                    "Student Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);

            switch (choice) {
                case 0: // Browse Listings
                    StringBuilder sb = new StringBuilder();
                    for (DormListing l : Main.listings) {
                        sb.append(l.getListingID()).append(": ").append(l.getDorm().getDormName()).append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, sb.toString());
                    break;
                case 1: // Book Room
                    String roomNum = JOptionPane.showInputDialog(frame, "Enter Room Number to book:");
                    Room selected = null;
                    for (DormListing l : Main.listings) {
                        for (Room r : l.getDorm().getRooms()) {
                            if (r.getRoomNumber().equals(roomNum)) selected = r;
                        }
                    }
                    if (selected != null && selected.isAvailable()) {
                        student.bookRoom(selected, "2026-01-01", "2026-12-31", selected.getPricePerMonth());
                        JOptionPane.showMessageDialog(frame, "Room booked successfully!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Room not available!");
                    }
                    break;
                case 2: // Pay Rent
                    student.payRent();
                    JOptionPane.showMessageDialog(frame, "Rent payment processed.");
                    break;
                case 3: // Display Info
                    JOptionPane.showMessageDialog(frame, student.displayInfo());
                    break;
                default:
                    running = false; // Back
            }
        }
    }

    // ===== LANDLORD MENU =====
    private static void showLandlordMenu(JFrame frame, Landlord landlord) {
        String[] options = {"Post Listing", "Display Info", "Back"};
        boolean running = true;
        while (running) {
            int choice = JOptionPane.showOptionDialog(frame,
                    "Choose an action:",
                    "Landlord Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);

            switch (choice) {
                case 0: // Post Listing
                    String newListingID = JOptionPane.showInputDialog(frame, "Enter Listing ID:");
                    if (landlord.getOwnedDorms().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "No dorms found for this landlord!");
                        break;
                    }
                    DormListing newListing = new DormListing(newListingID, landlord.getOwnedDorms().get(0),
                            landlord, "2025-12-01", 1, 3000.0);
                    Main.listings.add(newListing);
                    landlord.postDormListing(newListing);
                    JOptionPane.showMessageDialog(frame, "Listing posted!");
                    break;
                case 1: // Display Info
                    JOptionPane.showMessageDialog(frame, landlord.displayInfo());
                    break;
                default:
                    running = false; // Back
            }
        }
    }
}
