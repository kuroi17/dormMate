package model;

import java.util.ArrayList;
import java.util.List;

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

        super(fullName, email, contactNumber, address); // from person class
        this.landlordID = landlordID;
        this.ownedDorms = new ArrayList<>();
    }

    public String getLandlordID() {
        return landlordID;
    }

    public void setLandlordID(String landlordID) {
        this.landlordID = landlordID;
    }

    public List<Dorm> getOwnedDorms() {
        return ownedDorms;
    }

    public void setOwnedDorms(List<Dorm> ownedDorms) {
        this.ownedDorms = ownedDorms;
    }

    public void addDorm(Dorm dorm) {
        if (dorm != null && !ownedDorms.contains(dorm)) {
            ownedDorms.add(dorm);
             System.out.println("Dorm " + dorm.getDormName() + " added to " + getfullName() + "'s portfolio");
        }
    }
    public void postDormListing(DormListing listing) {
        System.out.println("Listing posted: " + listing.getDescription());
    }

    public void updateListing(DormListing listing, String newDesc) {
        listing.updateDescription(newDesc);
    }

    public void deleteListing(String listingID) {
        System.out.println("Listing deleted: " + listingID);
    }

    public void viewInquiries(List<Inquiry> inquiries) {
        System.out.println("\n=== Inquiries for " + getfullName() + " ===");
        boolean hasInquiries = false;
        for (Inquiry inquiry : inquiries) {
            if (inquiry.getListing().getLandlord().equals(this)) {
                System.out.println("- " + inquiry.getMessage() + 
                                 " (from: " + inquiry.getStudent().getfullName() + ")");
                hasInquiries = true;
            }
        }
        if (!hasInquiries) {
            System.out.println("No inquiries yet.");
        }
    }
      @Override 
    public String displayInfo() {
        return "\nName: " + getfullName() +
               "\nLandlord ID: " + landlordID +
               "\nOwned Dorms: " + ownedDorms.size() +
               "\nEmail: " + getEmail() +
               "\nContact: " + getContactNumber() +
               "\nAddress: " + getAddress() +
               "\n Total Dorms: " + ownedDorms.size();
               
    }
}