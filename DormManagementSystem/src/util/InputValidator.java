package util;

/**
 * Simple InputValidator for dormManagementSystem project.
 * Returns true/false instead of throwing exceptions for easier usage.
 */
public class InputValidator {

    // Check if email contains @ and .
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) return false;
        return email.contains("@") && email.contains(".");
    }

    // Check if Philippine contact number starts with 09 and has 11 digits
    public static boolean isValidContact(String contact) {
        if (contact == null || contact.isEmpty()) return false;
        return contact.startsWith("09") && contact.length() == 11;
    }

    // Check if budget/price is positive
    public static boolean isValidPrice(double price) {
        return price > 0;
    }

    // Check if a string is not empty
    public static boolean isNotEmpty(String text) {
        return text != null && !text.trim().isEmpty();
    }

    // Simple date format check (YYYY-MM-DD)
    public static boolean isValidDate(String date) {
        if (date == null || date.isEmpty()) return false;
        String[] parts = date.split("-");
        if (parts.length != 3) return false;
        
        try {
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);
            
            return year >= 2024 && month >= 1 && month <= 12 && day >= 1 && day <= 31;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Print error message helper
    public static void printError(String message) {
        System.out.println("X " + message);
    }
}