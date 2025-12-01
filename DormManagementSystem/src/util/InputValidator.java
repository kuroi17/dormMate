package util;

// ========== CUSTOM EXCEPTIONS (Required for OOP Project) ==========

class InvalidEmailException extends Exception {
    public InvalidEmailException(String message) {
        super(message);
    }
}

class InvalidPhoneException extends Exception {
    public InvalidPhoneException(String message) {
        super(message);
    }
}

class InvalidBudgetException extends Exception {
    public InvalidBudgetException(String message) {
        super(message);
    }
}

class InvalidDateException extends Exception {
    public InvalidDateException(String message) {
        super(message);
    }
}

class RoomNotAvailableException extends Exception {
    public RoomNotAvailableException(String message) {
        super(message);
    }
}

// ========== INPUT VALIDATOR ==========

/**
 * Simple InputValidator for dormManagementSystem project.
 */
public class InputValidator {

    // Check if email contains @ and .
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) return false;
        return email.contains("@") && email.contains(".");
    }

    // WITH EXCEPTION HANDLING
    public static void validateEmail(String email) throws InvalidEmailException {
        if (!isValidEmail(email)) {
            throw new InvalidEmailException("Invalid email: " + email);
        }
    }

    // Check if Philippine contact number starts with 09 and has 11 digits
    public static boolean isValidContact(String contact) {
        if (contact == null || contact.isEmpty()) return false;
        return contact.startsWith("09") && contact.length() == 11;
    }

    // WITH EXCEPTION HANDLING
    public static void validateContact(String contact) throws InvalidPhoneException {
        if (!isValidContact(contact)) {
            throw new InvalidPhoneException("Invalid phone number: " + contact);
        }
    }

    // Check if budget is positive
    public static boolean isValidBudget(double budget) {
        return budget > 0;
    }

    // WITH EXCEPTION HANDLING
    public static void validateBudget(double budget) throws InvalidBudgetException {
        if (!isValidBudget(budget)) {
            throw new InvalidBudgetException("Budget must be positive: ₱" + budget);
        }
    }

    // Check if price is non-negative
    public static boolean isValidPrice(double price) {
        return price >= 0;
    }

    // WITH EXCEPTION HANDLING
    public static void validatePrice(double price) throws InvalidBudgetException {
        if (!isValidPrice(price)) {
            throw new InvalidBudgetException("Price cannot be negative: ₱" + price);
        }
    }

    // Check if a string is not empty
    public static boolean isNotEmpty(String text) {
        return text != null && !text.trim().isEmpty();
    }

    // Check room capacity (1 to 10)
    public static boolean isValidCapacity(int capacity) {
        return capacity > 0 && capacity <= 10;
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
            
            return year > 2000 && month >= 1 && month <= 12 && day >= 1 && day <= 31;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // WITH EXCEPTION HANDLING
    public static void validateDate(String date) throws InvalidDateException {
        if (!isValidDate(date)) {
            throw new InvalidDateException("Invalid date format: " + date + " (Use YYYY-MM-DD)");
        }
    }

    // ========== TEST ==========
    public static void main(String[] args) {
        System.out.println("=== SIMPLE VALIDATION TESTS ===\n");
        
        // Basic validation tests
        System.out.println("--- Basic Validation ---");
        System.out.println("Email test: " + isValidEmail("test@gmail.com")); // true
        System.out.println("Phone test: " + isValidContact("09123456789")); // true
        System.out.println("Budget test: " + isValidBudget(1000)); // true
        System.out.println("Price test: " + isValidPrice(-5)); // false
        System.out.println("Empty test: " + isNotEmpty("Hello")); // true
        System.out.println("Capacity test: " + isValidCapacity(12)); // false
        System.out.println("Date test: " + isValidDate("2025-12-01")); // true
        
        // Exception handling tests
        System.out.println("\n--- Exception Handling Tests ---");
        
        try {
            validateEmail("invalid-email");
        } catch (InvalidEmailException e) {
            System.out.println("✗ " + e.getMessage());
        }
        
        try {
            validateContact("1234567890");
        } catch (InvalidPhoneException e) {
            System.out.println("✗ " + e.getMessage());
        }
        
        try {
            validateBudget(-500);
        } catch (InvalidBudgetException e) {
            System.out.println("✗ " + e.getMessage());
        }
        
        try {
            validateDate("2025/12/01");
        } catch (InvalidDateException e) {
            System.out.println("✗ " + e.getMessage());
        }
        
        System.out.println("\n✓ All tests completed!");
    }
}