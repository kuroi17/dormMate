

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

## 🏛️ Four Pillars of OOP (Simplified for Java Console/GUI)

| Pillar | Implementation |
|--------|----------------|
| **Encapsulation** | All classes use private fields with getters/setters |
| **Inheritance** | `Student` and `Landlord` both extend `Person` |
| **Polymorphism** | `displayInfo()` overridden in Student and Landlord |
| **Abstraction** | `Person` is abstract; `DormMATE` hides complex logic |

---

## 📊 CLASS DIAGRAM

```
+--------------------------------------------+
|           Person (ABSTRACT) 👤             |  ◄─── ABSTRACTION
+--------------------------------------------+
| - fullName: String                         |  ◄─── ENCAPSULATION
| - contactNumber: String                    |
| - email: String                            |
| - userID: String                           |
+--------------------------------------------+
| + displayInfo(): String (ABSTRACT)         |
| + getFullName(): String                    |
| + getContactNumber(): String               |
| + getEmail(): String                       |
| + getUserID(): String                      |
+--------------------------------------------+
           ▲                    ▲
           │                    │
    extends │                   │ extends (INHERITANCE)
           │                    │
+----------------------+   +----------------------+
|     Student 🎓       |   |    Landlord 🏠       |
+----------------------+   +----------------------+
| - studentID: String  |   | - landlordID: String |
| - university: String |   | - ownedDorms: List   |
| - budget: double     |   +----------------------+
+----------------------+   | + postDormListing()  |
| + browseRooms()      |   | + updateListing()    |
| + inquireRoom()      |   | + deleteListing()    |
| + bookRoom()         |   | + viewInquiries()    |
| + displayInfo()      |   | + displayInfo()      |  ◄─── POLYMORPHISM
+----------------------+   +----------------------+

+--------------------------------------------+
|              DormListing 📋                |
+--------------------------------------------+
| - listingID: String                        |
| - landlord: Landlord                       |
| - dorm: Dorm                               |
| - datePosted: String                       |
| - status: String (Active/Inactive)         |
| - description: String                      |
+--------------------------------------------+
| + getListingID(): String                   |
| + getLandlord(): Landlord                  |
| + getDorm(): Dorm                          |
| + activate(): void                         |
| + deactivate(): void                       |
| + updateDescription(desc: String): void    |
+--------------------------------------------+

+--------------------------------------------+
|                Dorm 🏢                     |
+--------------------------------------------+
| - dormID: String                           |
| - dormName: String                         |
| - address: String                          |
| - contactInfo: String                      |
| - amenities: List<String>                  |
| - rooms: List<Room>                        |
+--------------------------------------------+
| + addRoom(room: Room): void                |
| + getAvailableRooms(): List<Room>          |
| + getTotalRooms(): int                     |
| + getAddress(): String                     |
| + getAmenities(): List<String>             |
+--------------------------------------------+

+--------------------------------------------+
|                Room 🚪                     |
+--------------------------------------------+
| - roomNumber: String                       |
| - capacity: int                            |
| - occupiedCount: int                       |
| - pricePerMonth: double                    |
| - isAvailable: boolean                     |
| - tenants: List<Student>                   |
+--------------------------------------------+
| + isAvailable(): boolean                   |
| + book(student: Student): boolean          |
| + vacate(): void                           |
| + getPricePerMonth(): double               |
| + getCapacity(): int                       |
| + getOccupancyStatus(): String             |
+--------------------------------------------+

+--------------------------------------------+
|            Inquiry 💬                      |
+--------------------------------------------+
| - inquiryID: String                        |
| - student: Student                         |
| - listing: DormListing                     |
| - message: String                          |
| - dateInquired: String                     |
| - status: String (Pending/Responded)       |
+--------------------------------------------+
| + getStudent(): Student                    |
| + getListing(): DormListing                |
| + getMessage(): String                     |
| + respond(response: String): void          |
+--------------------------------------------+

+--------------------------------------------+
|          DormMATE (Main System) 🎛️        |  ◄─── ABSTRACTION (Hides complexity)
+--------------------------------------------+
| - listings: List<DormListing>              |
| - students: List<Student>                  |
| - landlords: List<Landlord>                |
| - inquiries: List<Inquiry>                 |
+--------------------------------------------+
| + registerStudent(student: Student): void  |
| + registerLandlord(landlord: Landlord): void |
| + createListing(listing: DormListing): void |
| + searchByLocation(address: String): List  |
| + searchByBudget(maxPrice: double): List   |
| + browseAllListings(): List<DormListing>   |
| + submitInquiry(inquiry: Inquiry): void    |
| + bookRoom(student: Student, room: Room): boolean |
| + generateReport(): String                 |
+--------------------------------------------+
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
| contactNumber | String | Phone number |
| email | String | Email address |
| userID | String | Unique identifier |

---

### 2. **Student** - `model/Student.java`
**Purpose:** Students looking for dorms

**Extends:** Person ✅ (Inheritance)

| Field | Type | Description |
|-------|------|-------------|
| studentID | String | Student ID number |
| university | String | School name |
| budget | double | Maximum rent budget |

**Methods:**
- `browseRooms()` - View available listings
- `inquireRoom(DormListing)` - Ask about a listing
- `bookRoom(Room)` - Reserve a room
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
| landlord | Landlord | Who posted it |
| dorm | Dorm | The dorm being advertised |
| datePosted | String | When it was posted |
| status | String | "Active" or "Inactive" |
| description | String | Details about the dorm |

---

### 5. **Dorm** - `model/Dorm.java`
**Purpose:** Represents a dormitory building

| Field | Type | Description |
|-------|------|-------------|
| dormID | String | Unique dorm ID |
| dormName | String | Name of dorm |
| address | String | Location |
| contactInfo | String | Contact details |
| amenities | List\<String\> | WiFi, AC, laundry, etc. |
| rooms | List\<Room\> | Rooms in this dorm |

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

## 💡 Important Notes for Your Group

### ✅ What You CAN Do (Realistic for Java OOP):
- Console-based interface or Swing/JavaFX GUI
- Store data in ArrayLists (or simple file I/O)
- Search/filter by location, budget
- Show dorm listings
- Book rooms
- Track availability

### ❌ What You DON'T Need (Too Complex):
- ~~Web-based (no need for HTML/CSS/JS)~~
- ~~Google Maps API (just use String addresses)~~
- ~~Real-time updates (static data is fine)~~
- ~~Database (use ArrayLists or text files)~~
- ~~Social media features (just simulate inquiries)~~

### 🎯 Keep It Simple:
Your professor wants to see **OOP pillars**, not a full-stack web app! Focus on:
1. Good class design ✅
2. Proper inheritance ✅
3. Encapsulation everywhere ✅
4. Method overriding ✅
5. Clean separation of packages ✅

---

## 🚀 Next Steps

1. ✅ Review this diagram with your group
2. ✅ Start coding `Person`, `Student`, `Landlord`
3. ✅ Implement `Dorm`, `Room`, `DormListing`, `Inquiry`
4. ✅ Build `DormMATE` service class
5. ✅ Create simple GUI (Swing is enough!)
6. ✅ Test and demonstrate OOP pillars

Good luck! This design is realistic and shows all OOP concepts clearly. 🎓