# DormMate - Dormitory Management System

## 📋 Project Overview

**DormMate** is a Java-based console application designed to streamline the dormitory rental process for students and landlords. The system features an eye-catching ASCII art welcome screen and allows students to browse available dorm listings, inquire about properties, and book rooms. Landlords can manage their dorm properties, view student inquiries, and track room availability. This project demonstrates core Object-Oriented Programming (OOP) principles and provides a practical solution for managing dormitory accommodations in an educational setting.

---

## 🎯 Description

DormMate addresses the common challenges faced by students searching for affordable accommodation near their universities and landlords trying to manage multiple properties efficiently. The system provides:

- **For Students**: Browse dorm listings, filter by budget, send inquiries to landlords, book dorm/rooms, and view rental information
- **For Landlords**: Manage multiple dorm properties, view and respond to student inquiries, track room availability and bookings
- **Data Validation**: Input validation for emails, phone numbers, dates, and budget amounts
- **User-Friendly Interface**: Simple menu-driven console interface for easy navigation

---

## 🏗️ OOP Concepts Applied

### 1. **Encapsulation**

Encapsulation is implemented throughout the project by keeping data members or instance variables `private` and using special methods known as `getter` and `setter` methods to access and modify them. Using encapsulation helps provide security and restrict access to those classes that are connected to it.

**Example:**

```java
// Person.java
private String fullName;
private String email;
private String contactNumber;

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    if (InputValidator.isValidEmail(email)) {
        this.email = email;
    } else {
        InputValidator.printError("Invalid email format!");
    }
}
```

### 2. **Inheritance**

The `Person` class serves as the parent class, providing common attributes and methods that are shared among all persons. The `Student` and `Landlord` classes are child classes that inherit these common features, while also introducing their own unique attributes and methods to handle specific behaviors and functionality relevant to students and landlords, respectively. We also use `super()` to call the constructor of the parent class, which allows the child classes to initialize the inherited attributes properly before adding their own specific attributes. This ensures that the common data from the parent class is set correctly and reduces code duplication.

**Class Hierarchy:**

```
Person (Abstract)
├── Student
└── Landlord
```

**Example:**

```java
// Student inherits from Person
public class Student extends Person {
    private String studentID;
    private String universitySchool;
    private double budget;
    
    // rental state attributes
    private boolean isRenting;
    private Room currentRoom;
    private String leaseStartDate;
    private String leaseEndDate;
    private double monthlyRent;
    private String paymentStatus;

    public Student(
        String fullName,
        String email,
        String contactNumber,
        String address,
        String studentID,
        String universitySchool,
        double budget
    ) {
        // use super() to call Person constructor
        super(fullName, email, contactNumber, address);
        
        this.studentID = studentID;
        this.universitySchool = universitySchool;
        this.budget = budget;

        // initialize rental state
        this.isRenting = false;
        this.currentRoom = null;
        this.paymentStatus = "N/A";
    }
}
```

```java
// Landlord inherits from Person
public class Landlord extends Person {
    private String landlordID;
    private List<Dorm> ownedDorms;

    // Parameterized constructor
    public Landlord(
        String fullName,
        String email,
        String contactNumber,
        String address,
        String landlordID
    ) {
        // use super() to call Person constructor
        super(fullName, email, contactNumber, address);
        
        this.landlordID = landlordID;
        this.ownedDorms = new ArrayList<>();
    }
}
```

### 3. **Polymorphism**

Polymorphism is demonstrated through method overriding, where child classes provide their own implementation of the abstract `displayInfo()` method. We also use the `@Override` annotation to override the method that is defined in the parent class. This allows objects of different child classes to be treated as objects of the parent class while still executing their own specific behavior, showing how the same method call can produce different results depending on the object’s actual type.

**Example:**

```java
// Person.java
public abstract String displayInfo(); // Abstract Method
```

```java
// Student.java
@Override
public String displayInfo() {
    String info = "\n=== STUDENT INFO ===" +
                  "\nName: " + getfullName() +
                  "\nStudent ID: " + studentID +
                  "\nUniversity: " + universitySchool +
                  "\nBudget: (Php)" + String.format("%.2f", budget) +
                  "\nEmail: " + getEmail() +
                  "\nContact: " + getContactNumber() +
                  "\nAddress: " + getAddress();
    
    // Show rental info if currently renting
    if (isRenting && currentRoom != null) {
        info += "\n\n=== RENTAL INFO ===" +
                "\nRoom: " + currentRoom.getRoomNumber() +
                "\nMonthly Rent: (Php)" + String.format("%.2f", monthlyRent) +
                "\nLease: " + leaseStartDate + " to " + leaseEndDate +
                "\nPayment Status: " + paymentStatus;
    }
    return info;
}
```

```java
// Landlord.java
@Override
public String displayInfo() {
    return "\nName: " + getfullName() +
           "\nLandlord ID: " + landlordID +
           "\nOwned Dorms: " + ownedDorms.size() +
           "\nEmail: " + getEmail() +
           "\nContact: " + getContactNumber() +
           "\nAddress: " + getAddress() +
           "\nTotal Dorms: " + ownedDorms.size();
}
```

### 4. **Abstraction**

The `Person` class is declared as abstract, providing a template for common attributes while enforcing that subclasses implement specific methods. This class acts as an abstraction because it does not have method bodies for certain behaviors, which forces child classes to provide their own implementations. By doing this, abstraction hides the complexity of how these behaviors are performed in each subclass, allowing the program to focus on what the objects do rather than how they do it. This makes the code cleaner, easier to maintain, and more flexible for future extensions.

**Example:**

```java
public abstract class Person {
    // Common attributes
    private String fullName;
    private String email;

    // Abstract method - must be implemented by subclasses
    public abstract String displayInfo();
}
```

---

## 📁 Program Structure

### Project Directory Structure

```
DormManagementSystem/
│
├── src/
│   ├── model/                  # Domain models
│   │   ├── Person.java         # Abstract parent class
│   │   ├── Student.java        # Student class (extends Person)
│   │   ├── Landlord.java       # Landlord class (extends Person)
│   │   ├── Dorm.java           # Dorm property cllass
│   │   ├── Room.java           # Room class
│   │   ├── DormListing.java    # Listing class
│   │   └── Inquiry.java        # Inquiry class
│   │
│   ├── service/
│   │   ├── DormMate.java       # Main entry point
│   │   ├── StudentMenu.java    # Student operations menu
│   │   └── LandlordMenu.java   # Landlord operations menu
│   │
│   ├── ui/
│   │   └── Main.java           # Data initialization
│   │
│   └── util/
│       └── InputValidator.java # Input validation methods
│
└── bin/                        # Compiled .class files
```

### Class Relationships

```
┌──────────────────────────────────────────────────────────────────┐
│                      Person (Abstract)                           │
├──────────────────────────────────────────────────────────────────┤
│ - fullName: String                                               │
│ - email: String                                                  │
│ - contactNumber: String                                          │
│ - address: String                                                │
├──────────────────────────────────────────────────────────────────┤
│ + getfullName(): String                                          │
│ + setfullName(String): void                                      │
│ + getEmail(): String                                             │
│ + setEmail(String): void                                         │
│ + getContactNumber(): String                                     │
│ + setContactNumber(String): void                                 │
│ + getAddress(): String                                           │
│ + setAddress(String): void                                       │
│ + abstract displayInfo(): String                                 │
└─────────────────────────┬────────────────────────────────────────┘
                          │
              ┌───────────┴───────────┐
              │                       │
┌─────────────▼────────────┐ ┌────────▼─────────────────┐
│        Student           │ │       Landlord           │
├──────────────────────────┤ ├──────────────────────────┤
│ - studentID: String      │ │ - landlordID: String     │
│ - universitySchool: String│ │ - ownedDorms: List<Dorm>│
│ - budget: double         │ │                          │
│ - isRenting: boolean     │ │                          │
│ - currentRoom: Room      │ │                          │
│ - leaseStartDate: String │ │                          │
│ - leaseEndDate: String   │ │                          │
│ - monthlyRent: double    │ │                          │
│ - paymentStatus: String  │ │                          │
├──────────────────────────┤ ├──────────────────────────┤
│ + getStudentID(): String │ │ + getLandlordID(): String│
│ + getBudget(): double    │ │ + getOwnedDorms(): List  │
│ + isRenting(): boolean   │ │ + addDorm(Dorm): void    │
│ + getCurrentRoom(): Room │ │ + viewInquiries(List): void│
│ + bookRoom(): void       │ │ + postDormListing(): void│
│ + vacateRoom(): void     │ │ + updateListing(): void  │
│ + browseListings(): void │ │ + deleteListing(): void  │
│ + inquireRoom(): void    │ │ + displayInfo(): String  │
│ + displayInfo(): String  │ │                          │
└──────────────────────────┘ └──────────────────────────┘
              │                       │
              │                       │
              │    ┌──────────────────┘
              │    │
              │    │         ┌─────────────────────────────┐
              │    └────────►│      DormListing            │
              │              ├─────────────────────────────┤
              │              │ - listingID: String         │
              │              │ - dorm: Dorm                │
              │              │ - landlord: Landlord        │
              │              │ - datePosted: String        │
              │              │ - status: String            │
              │              │ - availableRooms: int       │
              │              │ - photos: List<String>      │
              │              │ - priceRange: double        │
              │              ├─────────────────────────────┤
              │              │ + getListingID(): String    │
              │              │ + getDorm(): Dorm           │
              │              │ + getLandlord(): Landlord   │
              │              │ + getAvailableRooms(): int  │
              │              │ + getPriceRange(): double   │
              │              │ + addPhoto(String): void    │
              │              └─────────────┬───────────────┘
              │                            │
              │                            │
┌─────────────▼──────────────┐             │
│         Inquiry            │             │
├────────────────────────────┤             │
│ - inquiryID: String        │             │
│ - student: Student         │             │
│ - listing: DormListing     │◄────────────┘
│ - message: String          │
│ - dateInquired: String     │
│ - status: String           │
│ - response: String         │
├────────────────────────────┤
│ + getInquiryID(): String   │
│ + getStudent(): Student    │
│ + getListing(): DormListing│
│ + getMessage(): String     │
│ + getStatus(): String      │
│ + respond(String): void    │
│ + displayInfo(): String    │
└────────────────────────────┘

┌──────────────────────────────────────────────────────┐
│                    Dorm                              │
├──────────────────────────────────────────────────────┤
│ - dormName: String                                   │
│ - rooms: List<Room>                                  │
│ - googleMapLink: String                              │
│ - address: String                                    │
│ - shortDescription: String                           │
├──────────────────────────────────────────────────────┤
│ + getDormName(): String                              │
│ + getRooms(): List<Room>                             │
│ + getGoogleMapLink(): String                         │
│ + getAddress(): String                               │
│ + getShortDescription(): String                      │
│ + addRoom(Room): void                                │
│ + toString(): String                                 │
└────────────────────────┬─────────────────────────────┘
                         │
                         │ contains
                         │
                         ▼
          ┌──────────────────────────────┐
          │          Room                │
          ├──────────────────────────────┤
          │ - roomNumber: String         │
          │ - capacity: int              │
          │ - occupiedCount: int         │
          │ - pricePerMonth: double      │
          │ - isAvailable: boolean       │
          │ - tenants: List<Student>     │
          ├──────────────────────────────┤
          │ + getRoomNumber(): String    │
          │ + getCapacity(): int         │
          │ + getOccupiedCount(): int    │
          │ + getPricePerMonth(): double │
          │ + isAvailable(): boolean     │
          │ + getTenants(): List<Student>│
          │ + book(Student): boolean     │
          │ + vacate(): void             │
          │ + getOccupancyStatus(): String│
          │ + toString(): String         │
          └──────────────────────────────┘
```

### Main Classes and Their Roles

| Class              | Package   | Role                                                |
| ------------------ | --------- | --------------------------------------------------- |
| **Person**         | `model`   | Abstract base class for Student and Landlord        |
| **Student**        | `model`   | Represents a student user with booking capabilities |
| **Landlord**       | `model`   | Represents a landlord managing dorm properties      |
| **Dorm**           | `model`   | Represents a dormitory property                     |
| **Room**           | `model`   | Represents individual rooms within a dorm           |
| **DormListing**    | `model`   | Represents a dorm listing advertisement             |
| **Inquiry**        | `model`   | Represents student inquiries to landlords           |
| **DormMate**       | `service` | Main entry point and application controller         |
| **StudentMenu**    | `service` | Handles student menu operations                     |
| **LandlordMenu**   | `service` | Handles landlord menu operations                    |
| **Main**           | `ui`      | Initializes sample data                             |
| **InputValidator** | `util`    | Validates user inputs                               |

---

## 🚀 How to Run the Program

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Terminal/Command Prompt access

### Step-by-Step Instructions

#### 1. **Navigate to the Project Directory**

```bash
cd /workspaces/dormManagementSystem/DormManagementSystem
```

#### 2. **Compile the Java Files**

```bash
javac -d bin src/**/*.java
```

This command compiles all `.java` files in the `src` directory and places the compiled `.class` files in the `bin` directory.

#### 3. **Run the Program**

```bash
java -cp bin service.DormMate
```

This executes the `DormMate` class which contains the main method.

### Alternative: One-Line Command

```bash
cd /workspaces/dormManagementSystem/DormManagementSystem && javac -d bin src/**/*.java && java -cp bin service.DormMate
```

---

## 📺 Sample Output

### Program Start

```

 ______         ,-----.    .-------.    ,---.    ,---.,---.    ,---.   ____   ,---------.    .-''-.             
|    _ `''.   .'  .-,  '.  |  _ _   \   |    \  /    ||    \  /    | .'  __ `.\          \ .'_ _   \      
| _ | ) _  \ / ,-.|  \ _ \ | ( ' )  |   |  ,  \/  ,  ||  ,  \/  ,  |/   '  \  \`--.  ,---'/ ( ` )   '    
|( ''_'  ) |;  \  '_ /  | :|(_ o _) /   |  |\_   /|  ||  |\_   /|  ||___|  /  |   |   \  . (_ o _)  |       
| . (_) `. ||  _`,/ \ _/  || (_,_).' __ |  _( )_/ |  ||  _( )_/ |  |   _.-`   |   :_ _:  |  (_,_)___|          
|(_    ._) ': (  '\_/ \   ;|  |\ \  |  || (_ o _) |  || (_ o _) |  |.'   _    |   (_I_)  '  \   .---.      
|  (_.\.' /  \ `"/  \  ) / |  | \ `'   /|  (_,_)  |  ||  (_,_)  |  ||  _( )_  |  (_(=)_)  \  `-'    /     
|       .'    '. \_/``."'  |  |  \    / |  |      |  ||  |      |  |\ (_ o _) /   (_I_)    \       /       
'-----'`        '-----'    ''-'   `'-'  '--'      '--''--'      '--' '.(_,_).'    '---'     `'-..-'             

     ███████       
    █     █ █████████    
   █       █ ███████████   
  ██████████████████████  
 █                      █
 █                      █
 █          ███         █
  ██████████████████████ 

=== WELCOME TO DormMate! ===

Are you a:
1. Student
2. Landlord
3. Exit
Enter choice: 1
```

---

## 🎓 STUDENT FEATURES DEMONSTRATION

### Student Portal - Sign Up

```
--- STUDENT PORTAL ---
1. Login
2. Sign Up
3. Back
Choice: 2

--- STUDENT SIGN UP ---
Full Name: Maria Clara Santos
Email: maria.santos@student.edu.ph
Phone Number: 09171234567
Address: 456 University Ave, Manila
Student ID: S002
University/School: BS Information Technology - PUP Manila
Budget (Monthly): 8000

Account created successfully!
```

### Student Portal - Login

```
--- STUDENT PORTAL ---
1. Login
2. Sign Up
3. Back
Choice: 1

--- STUDENT LOGIN ---
Enter your student ID: S001
Welcome, Juan Dela Cruz!

--- STUDENT MENU ---
1. Browse listings
2. Inquire about a dorm
3. Book a room
4. Display info
5. Logout
Choice:
```

### Feature 1: Browse Listings

```
Choice: 1

--- AVAILABLE LISTINGS ---

Listing ID: LIST001
Dorm: Sunshine Dorm
Address: 123 Main St, Quezon City
Available Rooms: 2
Monthly Rate: (Php)5000.0 - (Php)7000.0
Description: Affordable student housing near university

Listing ID: LIST002
Dorm: Greenview Apartments
Address: 789 College Road, Manila
Available Rooms: 5
Monthly Rate: (Php)6000.0 - (Php)9000.0
Description: Modern facilities with WiFi and study areas
```

### Feature 2: Inquire About a Dorm

```
Choice: 2

---AVAILABLE DORM LISTINGS---
Available Listing ID: LIST001
Available Listing ID: LIST002

Enter listing ID to inquire: LIST001

Dorm: Sunshine Dorm
Address: 123 Main St, Quezon City
Description: Affordable student housing near university
Available Rooms: 2
Price Range: (Php)5000.0 - (Php)7000.0

Enter your message: Hello! Is the room still available? What amenities are included?

Inquiry sent successfully!
The landlord will respond to your inquiry soon.
```

### Feature 3: Book a Room

```
Choice: 3

---AVAILABLE ROOMS---
Available Room Number: 101
Available Room Number: 102
Available Room Number: 201

Enter room number to book: 101
Enter start date (YYYY-MM-DD): 2025-01-15
Enter end date (YYYY-MM-DD): 2025-06-15
Juan Dela Cruz successfully booked room 101
Room booked successfully!
```

### Feature 4: Display My Info

```
Choice: 4

=== STUDENT INFO ===
Name: Juan Dela Cruz
Student ID: S001
University: BSCS - UP Diliman
Budget: (Php)10000.00
Email: juan@email.com
Contact: 09123456789
Address: 123 Student Ave, Quezon City

=== RENTAL INFO ===
Room: 101
Monthly Rent: (Php)5000.00
Lease: 2025-01-15 to 2025-06-15
```

### Feature 5: Logout

```
Choice: 5
Logged out successfully!

--- STUDENT PORTAL ---
1. Login
2. Sign Up
3. Back
Choice: 3

Are you a:
1. Student
2. Landlord
3. Exit
Enter choice:
```

---

## 🏢 LANDLORD FEATURES DEMONSTRATION

### Landlord Portal - Sign Up

```
Are you a:
1. Student
2. Landlord
3. Exit
Enter choice: 2

--- LANDLORD PORTAL ---
1. Login
2. Sign Up
3. Back
Choice: 2

--- LANDLORD SIGN UP ---
Full Name: Roberto De Leon
Email: roberto.delon@gmail.com
Phone Number: 09181234567
Address: 789 Business St, Makati
Landlord ID: L002

Landlord account created successfully!
```

### Landlord Portal - Login

```
--- LANDLORD PORTAL ---
1. Login
2. Sign Up
3. Back
Choice: 1

--- LANDLORD LOGIN ---
Enter your landlord ID: L001
Welcome, Maria Santos!

--- LANDLORD MENU ---
1. View my dorms
2. Add new dorm
3. Add room to dorm
4. View inquiries
5. Respond to inquiry
6. View all bookings
7. Display info
8. Logout
Choice:
```

### Feature 1: View My Dorms

```
Choice: 1

--- MY DORMS ---

Dorm: Sunshine Dorm
Address: 123 Main St, Quezon City
Google Map: [Google Maps Link]
Total Rooms: 3
Available Rooms: 2

Dorm: Greenview Apartments
Address: 789 College Road, Manila
Google Map: [Google Maps Link]
Total Rooms: 6
Available Rooms: 5
```

### Feature 2: Add New Dorm

```
Choice: 2

--- ADD NEW DORM ---
Dorm Name: Blue Haven Dormitory
Address: 321 Campus Drive, Quezon City
Google Maps Link: https://maps.google.com/blue-haven
Description: Spacious rooms with air conditioning and high-speed internet
Available rooms count: 4
Starting price: 7500

Dorm added successfully!
Listing ID: DL-1733097600000
```

### Feature 3: Add Room to Dorm

```
Choice: 3

--- ADD ROOM TO DORM ---
Your dorms:
1. Sunshine Dorm
2. Greenview Apartments
3. Blue Haven Dormitory

Select dorm number: 3
Room Number: 301
Capacity: 2
Price per month: 7500

Room added successfully to Blue Haven Dormitory
```

### Feature 4: View Inquiries

```
Choice: 4

--- INQUIRIES ---

--- Inquiry ---
From: Juan Dela Cruz
Student Email: juan@email.com
Message: Hello! Is the room still available? What amenities are included?
Date: 2025-12-01

--- Inquiry ---
From: Maria Clara Santos
Student Email: maria.santos@student.edu.ph
Message: Do you allow pets? I have a small cat.
Date: 2025-12-02
```

### Feature 5: Respond to Inquiry

```
Choice: 5

--- RESPOND TO INQUIRY ---

1. From: Juan Dela Cruz
   Message: Hello! Is the room still available? What amenities are included?
   Date: 2025-12-01

2. From: Maria Clara Santos
   Message: Do you allow pets? I have a small cat.
   Date: 2025-12-02

Select inquiry number to respond: 1
Your response: Yes, the room is still available! Amenities include WiFi, electricity, water, and access to common kitchen and study area.

Response sent to Juan Dela Cruz
Response: Yes, the room is still available! Amenities include WiFi, electricity, water, and access to common kitchen and study area.
(In a real system, this would be sent via email/notification)
```

### Feature 6: View All Bookings

```
Choice: 6

--- ALL BOOKINGS ---

--- Booking Details ---
Dorm: Sunshine Dorm
Room: 101
Student: Juan Dela Cruz
Contact: 09123456789
Lease: 2025-01-15 to 2025-06-15
Monthly Rent: (Php)5000.0

--- Booking Details ---
Dorm: Greenview Apartments
Room: 201
Student: Maria Clara Santos
Contact: 09171234567
Lease: 2025-02-01 to 2025-07-01
Monthly Rent: (Php)6000.0
```

### Feature 7: Display Info

```
Choice: 7

Name: Maria Santos
Landlord ID: L001
Owned Dorms: 3
Email: maria@email.com
Contact: 09187654321
Address: 456 Owner Ave, Manila
Total Dorms: 3
```

### Feature 8: Logout

```
Choice: 8
Logged out successfully!

--- LANDLORD PORTAL ---
1. Login
2. Sign Up
3. Back
Choice: 3

Are you a:
1. Student
2. Landlord
3. Exit
Enter choice:
```

---

## ⚠️ Input Validation Examples

### Invalid Email Format

```
Enter Email: invalidemail.com
Invalid email format!
```

### Invalid Phone Number

```
Enter Phone Number: 12345
Invalid contact number format!
```

### Invalid Date Format

```
Enter start date (YYYY-MM-DD): 2025-13-45
Invalid start date format!
```

### Empty Message

```
Enter your message:
Message cannot be empty!
```

### Insufficient Budget

```
Enter room number to book: 102
Monthly rent: (Php)7000.00
Your budget: (Php)5000.00
Insufficient budget! Need (Php)7000.0 but have (Php)5000.0
```

### Student/Landlord Not Found

```
Enter your student ID: S999
Student not found!

Enter your landlord ID: L999
Landlord not found!
```

---

## 🚪 Program Exit

```
Are you a:
1. Student
2. Landlord
3. Exit
Enter choice: 3

Exiting DormMate. Goodbye!
```

---

## 👨‍💻 Authors and Acknowledgements

### Authors

#### ![Shin-mie Ramos](https://avatars.githubusercontent.com/u/191760553?v=4)

Shin-mie Ramos

- **Course:** Computer Science
- **Email:** 24-07030@g.batstate-u.edu.ph
- **GitHub:** [shinmieeeee](https://github.com/shinmieeeee)

#### ![Coleen Dichoso](https://avatars.githubusercontent.com/u/191759724?v=4)

Coleen Dichoso

- **Course:** Computer Science
- **Email:** 24-07852@g.batstate-u.edu.ph
- **GitHub:** [cole-colee](https://github.com/cole-colee)

#### ![Julianne Antoinette Deduque](https://avatars.githubusercontent.com/u/129857185?v=4)

Julianne Antoinette Deduque

- **Course:** Computer Science
- **Email:** 22-07161@g.batstate-u.edu.ph
- **GitHub:** [jasd927](https://github.com/jasd927)

#### Jyvhan Earl Ponce

- **Course:** Computer Science
- **Email:** 24-04667@g.batstate-u.edu.ph
- **GitHub:** [Kuroi17](https://github.com/kuroi17)

### Acknowledgements

- **Course Instructor**: Ms. Fatima Marie Agdon - For teaching and guidance on Java OOP principles and project requirements. Thank you so much Maam.
- **Classmates**: For collaboration and code review sessions
- **Github**: For platform to use for dynamic collaboration
- **Generative AIs**: For brainstorming and debugging code issues

---

## 🔮 Future Enhancements

1. **Database Integration**

   - Implement MySQL/PostgreSQL for persistent data storage
   - Replace in-memory lists with database queries

2. **Advanced Search & Filters**

   - Filter by location, price range, room type
   - Sort listings by price, rating, or availability

3. **Payment Integration**

   - Add online payment gateway simulation
   - Generate payment console-based receipts

4. **GUI Implementation**

   - Develop JavaFX or Swing-based graphical interface
   - Improve user experience with visual elements

---

## 📚 References

**OOP Lesson Presentations**

- **Encapsulation** – Using `private` class members and special methods (getters and setters) to control access.
- **Inheritance** – Using `extends` and `super()` to create a parent-child class relationship.
- **Polymorphism** – using `@Override` methods to allow different behaviors in child classes.
- **Abstraction** – Hiding complexity by making subclasses implement the abstract methods of the parent class.

**Online Resources**

- Youtube - Java Programming Tutorials
- Generative AIs - Brainstorm and Debugging
- GitHub - Open Source Java Repositoyies

---
