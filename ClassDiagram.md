

# 🏢 DormMATE - Dormitory Management, Allocation, Tracking, and Engagement System

## 📋 Project Description

**DormMATE** is a Java-based dormitory listing and management system that helps **landlords** post available dorms and **students** browse and book accommodations. Think of it like a simplified Facebook Marketplace for dorms - landlords create listings, students search and inquire, avoiding scams through a centralized platform.

### 🎯 Core Features:
1. **Landlords** can post dorm listings with details (location, price, amenities)
2. **Students** can browse available dorms and filter by location/budget
3. **Students** can inquire/book rooms
4. **Real-time tracking** of room availability
5. Prevents scams by centralizing all interactions

---
Concept	displayInfo()
Abstraction	✅ Abstract method in Person (walang body)
Polymorphism	✅ Different implementations in Student & Landlord

## 🏛️ Four Pillars of OOP (Simplified for Java Console/GUI)

| Pillar | Implementation |
|--------|----------------|
| **Encapsulation** | All classes use private fields with getters/setters |
| **Inheritance** | `Student` and `Landlord` both extend `Person` |
| **Polymorphism** | `displayInfo()` overridden in Student and Landlord |
| **Abstraction** | `Person` is abstract; `DormMATE` hides complex logic |

---

## 📊 CLASS DIAGRAM (UPDATED)

```
+--------------------------------------------+
|           Person (ABSTRACT) 👤             |  ◄─── ABSTRACTION
+--------------------------------------------+
| - fullName: String                         |
| - email: String                            |
| - contactNumber: String                    |
| - address: String                          |
+--------------------------------------------+
| + Person(fullName, email, contactNumber,   |
|          address)                          |
| + getfullName(): String                    |
| + setfullName(String): void                |
| + getEmail(): String                       |
| + setEmail(String): void                   |
| + getContactNumber(): String               |
| + setContactNumber(String): void           |
| + getAddress(): String                     |
| + setAddress(String): void                 |
| + displayInfo(): String (ABSTRACT)         |  ◄─── POLYMORPHISM
+--------------------------------------------+
              ▲                   ▲
              |                   |
      ┌───────┴────────┐  ┌──────┴────────┐
      |                |  |               |
+------------------+   +-------------------+
|    Student 🎓    |   |   Landlord 🏠     |
+------------------+   +-------------------+
| INHERITANCE ◄────────┴─── INHERITANCE    |
+------------------+   +-------------------+
| - studentID: String                      |
| - universitySchool: String               |
| - budget: double                         |
| - isRenting: boolean                     |
| - currentRoom: Room                      |
| - leaseStartDate: String                 |
| - leaseEndDate: String                   |
| - monthlyRent: double                    |
| - paymentStatus: String                  |
+------------------------------------------+
| + Student(fullName, email, contactNumber,|
|           address, studentID,            |
|           university, budget)            |
| + getStudentID(): String                 |
| + setStudentID(String): void             |
| + getUniversitySchool(): String          |
| + setUniversitySchool(String): void      |
| + getBudget(): double                    |
| + setBudget(double): void                |
| + isRenting(): boolean                   |
| + getCurrentRoom(): Room                 |
| + getLeaseStartDate(): String            |
| + getLeaseEndDate(): String              |
| + getMonthlyRent(): double               |
| + getPaymentStatus(): String             |
| + setPaymentStatus(String): void         |
| + bookRoom(Room, String, String,         |
|            double): void                 |
| + vacateRoom(): void                     |
| + payRent(): void                        |
| + browseListings(): void                 |
| + inquireRoom(DormListing): void         |
| + displayInfo(): String                  |  ◄─── POLYMORPHISM
+------------------------------------------+

+------------------------------------------+
|            Landlord 🏠                    |
+------------------------------------------+
| - landlordID: String                     |
| - ownedDorms: List<Dorm>                 |
+------------------------------------------+
| + Landlord(fullName, email, contactNumber,|
|            address, landlordID)          |
| + getLandlordID(): String                |
| + setLandlordID(String): void            |
| + getOwnedDorms(): List<Dorm>            |
| + setOwnedDorms(List<Dorm>): void        |
| + addDorm(Dorm): void                    |
| + postDormListing(DormListing): void     |
| + updateListing(DormListing, String): void|
| + deleteListing(String): void            |
| + viewInquiries(List<Inquiry>): void     |
| + displayInfo(): String                  |  ◄─── POLYMORPHISM
+------------------------------------------+

+------------------------------------------+
|              Dorm 🏢                      |
+------------------------------------------+
| - dormName: String                       |
| - rooms: List<Room>                      |
| - googleMapLink: String                  |
| - address: String                        |
| - shortDescription: String               |
+------------------------------------------+
| + Dorm(dormName, googleMapLink,          |
|        address, shortDescription)        |
| + getDormName(): String                  |
| + setDormName(String): void              |
| + getRooms(): List<Room>                 |
| + setRooms(List<Room>): void             |
| + getGoogleMapLink(): String             |     
| + setGoogleMapLink(String): void         |
| + getAddress(): String                   |
| + setAddress(String): void               |
| + getShortDescription(): String          |
| + setShortDescription(String): void      |
| + addRoom(Room): void                    |
| + getAvailableRooms(): List<Room>        |
| + getTotalRooms(): int                   |
| + displayInfo(): String                  |
+------------------------------------------+
               |
               | contains
               ▼
+------------------------------------------+
|              Room 🚪                      |
+------------------------------------------+
| - roomNumber: String                     |
| - capacity: int                          |
| - occupiedCount: int                     |
| - pricePerMonth: double                  |
| - isAvailable: boolean                   |
| - tenants: List<Student>                 |
+------------------------------------------+
| + Room(roomNumber, capacity,             |
|        pricePerMonth)                    |
| + getRoomNumber(): String                |
| + getCapacity(): int                     |
| + getOccupiedCount(): int                |
| + getPricePerMonth(): double             |
| + getTenants(): List<Student>            |
| + isAvailable(): boolean                 |
| + book(Student): boolean                 |
| + vacate(): void                         |
| + getOccupancyStatus(): String           |
| + displayInfo(): String                  |
+------------------------------------------+

+------------------------------------------+
|          DormListing 📋                   |
+------------------------------------------+
| - listingID: String                      |
| - dorm: Dorm                             |
| - landlord: Landlord                     |
| - datePosted: String                     |
| - status: String                         |
| - availableRooms: int                    |
| - photos: List<String>                   |
| - priceRange: double                     |
+------------------------------------------+
| + DormListing(listingID, dorm, landlord, |
|               datePosted, availableRooms,|
|               priceRange)                |
| + getListingID(): String                 |
| + setListingID(String): void             |
| + getDorm(): Dorm                        |
| + setDorm(Dorm): void                    |
| + getLandlord(): Landlord                |
| + setLandlord(Landlord): void            |
| + getDatePosted(): String                |
| + setDatePosted(String): void            |
| + getStatus(): String                    |
| + setStatus(String): void                |
| + getAvailableRooms(): int               |
| + setAvailableRooms(int): void           |
| + getPhotos(): List<String>              |
| + setPhotos(List<String>): void          |
| + getPriceRange(): double                |
| + setPriceRange(double): void            |
| + addPhoto(String): void              |
| + displayInfo(String): void              |
+------------------------------------------+

+------------------------------------------+
|           Inquiry 💬                      |
+------------------------------------------+
| - inquiryID: String                      |
| - student: Student                       |
| - listing: DormListing                   |
| - message: String                        |
| - dateInquired: String                   |
| - status: String                         |
+------------------------------------------+
| + Inquiry(inquiryID, student, listing,   |
|           message, dateInquired)         |
| + getInquiryID(): String                 |
| + setInquiryID(String): void             |
| + getStudent(): Student                  |
| + setStudent(Student): void              |
| + getListing(): DormListing              |
| + setListing(DormListing): void          |
| + getMessage(): String                   |
| + setMessage(String): void               |
| + getDateInquired(): String              |
| + setDateInquired(String): void          |
| + getStatus(): String                    |
| + setStatus(String): void                |
| + respond(String): void                  |
+------------------------------------------+
```

---

## 📁 Project Folder Structure

```
DormManagementSystem/
│
├─ src/
│   ├─ 📦 model/              ← Data classes (the "entities")
│   │   ├─ Person.java        → Abstract base class
│   │   ├─ Student.java       → Extends Person (students browsing dorms)
│   │   ├─ Landlord.java      → Extends Person (landlords posting dorms)
│   │   ├─ Dorm.java          → Represents a dormitory building
│   │   ├─ Room.java          → Individual rooms in a dorm
│   │   ├─ DormListing.java   → Listings posted by landlords
│   │   └─ Inquiry.java       → Student inquiries about listings
│   │
│   ├─ 📦 service/            ← Business logic
│   │   └─ DormMATE.java      → Main system (search, book, manage)
│   │
│   ├─ 📦 util/               ← Helper utilities
│   │   └─ InputValidator.java → Validate emails, prices, etc.
│   │
│   └─ 📦 ui/                 ← User interface
│       └─ Main.java          → Entry point (GUI or Console)
│
└─ .gitignore
```

---

## 📚 Class Descriptions

### 1. **Person (Abstract)** - `model/Person.java`
**Purpose:** Base class for all users (students and landlords)

**Why Abstract?** You're either a Student or Landlord, never just a "Person"

| Field | Type | Description |
|-------|------|-------------|
| fullName | String | User's full name |
| email | String | Email address |
| contactNumber | String | Phone number |
| address | String | Home address |

---

### 2. **Student** - `model/Student.java`
**Purpose:** Students looking for dorms (can also be renting)

**Extends:** Person ✅ (Inheritance)

| Field | Type | Description |
|-------|------|-------------|
| studentID | String | Student ID number |
| universitySchool | String | School name |
| budget | double | Maximum rent budget |
| isRenting | boolean | Currently renting status |
| currentRoom | Room | Room being rented (null if not renting) |
| leaseStartDate | String | Lease start date |
| leaseEndDate | String | Lease end date |
| monthlyRent | double | Monthly rental amount |
| paymentStatus | String | Payment status (Pending/Paid/N/A) |

**Methods:**
- `browseListings()` - View available listings
- `inquireRoom(DormListing)` - Ask about a listing
- `bookRoom(Room, String, String, double)` - Reserve a room with lease details
- `vacateRoom()` - Leave current room
- `payRent()` - Record rent payment
- `displayInfo()` - Override (Polymorphism) ✅

---

### 3. **Landlord** - `model/Landlord.java`
**Purpose:** Landlords posting dorm listings

**Extends:** Person ✅ (Inheritance)

| Field | Type | Description |
|-------|------|-------------|
| landlordID | String | Landlord ID |
| ownedDorms | List\<Dorm\> | Dorms they own |

**Methods:**
- `postDormListing(DormListing)` - Create new listing
- `updateListing(DormListing)` - Edit listing
- `deleteListing(listingID)` - Remove listing
- `viewInquiries()` - See student inquiries
- `displayInfo()` - Override (Polymorphism) ✅

---

### 4. **DormListing** - `model/DormListing.java`
**Purpose:** A posted listing (like a Facebook post for a dorm)

| Field | Type | Description |
|-------|------|-------------|
| listingID | String | Unique listing ID |
| dorm | Dorm | The dorm being advertised |
| landlord | Landlord | Who posted it |
| datePosted | String | When it was posted |
| status | String | "Active" or "Inactive" |
| availableRooms | int | Number of available rooms |
| photos | List\<String\> | Photo URLs or paths |
| priceRange | double | Price range for the listing |

---

### 5. **Dorm** - `model/Dorm.java`
**Purpose:** Represents a dormitory building

| Field | Type | Description |
|-------|------|-------------|
| dormName | String | Name of dorm |
| rooms | List\<Room\> | Rooms in this dorm |
| googleMapLink | String | Link to Google Maps location |
| address | String | Physical location |
| shortDescription | String | Brief description of the dorm |

---

### 6. **Room** - `model/Room.java`
**Purpose:** Individual room in a dorm

| Field | Type | Description |
|-------|------|-------------|
| roomNumber | String | Room identifier |
| capacity | int | Max occupants |
| occupiedCount | int | Current occupants |
| pricePerMonth | double | Monthly rent |
| isAvailable | boolean | Can be booked? |
| tenants | List\<Student\> | Students in room |

**Methods:**
- `book(Student)` - Reserve room for student
- `vacate()` - Empty the room
- `isAvailable()` - Check if room has space

---

### 7. **Inquiry** - `model/Inquiry.java`
**Purpose:** Student asking about a listing (like commenting on FB post)

| Field | Type | Description |
|-------|------|-------------|
| inquiryID | String | Unique inquiry ID |
| student | Student | Who's asking |
| listing | DormListing | Which listing |
| message | String | Student's question |
| dateInquired | String | When asked |
| status | String | "Pending" or "Responded" |

---

### 8. **DormMATE** - `service/DormMATE.java`
**Purpose:** Main system brain (handles all operations)

**This is where the magic happens!** ✨

| Method | Description |
|--------|-------------|
| `registerStudent(Student)` | Add new student user |
| `registerLandlord(Landlord)` | Add new landlord user |
| `createListing(DormListing)` | Landlord posts new dorm |
| `searchByLocation(String)` | Find dorms by address |
| `searchByBudget(double)` | Find dorms within budget |
| `browseAllListings()` | Show all active listings |
| `submitInquiry(Inquiry)` | Student asks about listing |
| `bookRoom(Student, Room)` | Reserve a room |
| `generateReport()` | Show stats (occupancy, etc.) |

---

## 🔄 How It Works (User Flow)

### Landlord Posts a Dorm:
```
1. Landlord logs in (or registers)
2. Creates Dorm object (name, address, amenities)
3. Adds Rooms to the Dorm
4. Creates DormListing (links Dorm to Landlord)
5. Calls DormMATE.createListing(listing)
6. Listing appears in browse list ✅
```

### Student Books a Room:
```
1. Student logs in (or registers)
2. Calls DormMATE.searchByBudget(5000.0)
3. System returns matching listings
4. Student views details of a listing
5. Student submits Inquiry (asks questions)
6. Student calls bookRoom(student, room)
7. Room marked as occupied ✅
```

---

## ✅ OOP Pillars Checklist

| Pillar | Where | How |
|--------|-------|-----|
| **Encapsulation** | All classes | Private fields + public getters/setters |
| **Inheritance** | Student, Landlord | Both extend Person |
| **Polymorphism** | displayInfo() | Overridden in Student and Landlord |
| **Abstraction** | Person class | Abstract class (can't instantiate) |
| **Abstraction** | DormMATE | Hides complexity behind simple methods |

---

