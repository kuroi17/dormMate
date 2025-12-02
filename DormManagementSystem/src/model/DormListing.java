package model;
import java.util.ArrayList;
import java.util.List;

public class DormListing {
    
    private String listingID;
    private Dorm dorm;  
    private Landlord landlord; 
    private String datePosted;
    private String status;
    private int availableRooms;
    private List<String> photos;
    private double priceRange;

    public DormListing(
        String listingID,
        Dorm dorm,
        Landlord landlord, 
        String datePosted,
        int availableRooms,
        double priceRange
    ) {
        this.listingID = listingID;
        this.dorm = dorm;
        this.landlord = landlord;
        this.datePosted = datePosted;
        this.availableRooms = availableRooms;
        this.priceRange = priceRange;
        this.status = "Active";
        this.photos = new ArrayList<>();
    }

    // special methods getters and setters 

    public String getDescription() {
        return "Dorm Listing: " + dorm.getDormName() + " at " + dorm.getAddress();
    }
    public void updateDescription(String newDesc) {
        dorm.shortDescription = newDesc;
    }

    public String getListingID() {
        return listingID;
    }

    public void setListingID(String listingID) {
        this.listingID = listingID;
    }

    public Dorm getDorm() {
        return dorm;
    }

    public void setDorm(Dorm dorm) {
        this.dorm = dorm;
    }

    public Landlord getLandlord() {
        return landlord;
    }

    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public double getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(double priceRange) {
        this.priceRange = priceRange;
    }

    // Helper method
    public void addPhoto(String photoPath) {
        this.photos.add(photoPath);
    }
    @Override
    public String toString() {
        return "\n=== DORM LISTING ===" +
               "\nListing ID: " + listingID +
               "\nDorm Name: " + dorm.getDormName() +
               "\nAddress: " + dorm.getAddress() +
               "\nGoogle Maps: " + dorm.getGoogleMapLink() +
               "\nPrice Range: (Php)" + String.format("%.2f", priceRange) +
               "\nAvailable Rooms: " + availableRooms +
               "\nStatus: " + status +
               "\nDate Posted: " + datePosted;
    }
}