package util;

/**
 * Simple input validator
 * Returns true/false for validation checks
 */
public class InputValidator {
    
    /**
     * Check if email is valid (has @ and .)
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return email.contains("@") && email.contains(".");
    }
    
    /**
     * Check if contact is valid Philippine number (09XXXXXXXXX)
     */
    public static boolean isValidContact(String contact) {
        if (contact == null || contact.isEmpty()) {
            return false;
        }
        return contact.matches("^09\\d{9}$");
    }
    
    /**
     * Check if budget is positive
     */
    public static boolean isValidBudget(double budget) {
        return budget > 0;
    }
    
    /**
     * Check if price is non-negative
     */
    public static boolean isValidPrice(double price) {
        return price >= 0;
    }
    
    /**
     * Check if string is not empty
     */
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
    
    /**
     * Check if capacity is valid (1-10)
     */
    public static boolean isValidCapacity(int capacity) {
        return capacity > 0 && capacity <= 10;
    }
}