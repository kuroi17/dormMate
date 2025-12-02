package model;
import java.util.ArrayList;
import java.util.List;

 public class Dorm {
    // instance variable
    private String dormName;
    private List <Room> rooms;
    private String googleMapLink;
    private String address;
    public String shortDescription;

    // constructor
    public Dorm (
        String dormName,
        List <Room> rooms,
        String googleMapLink,
        String address,
        String shortDescription
    ){
        this.dormName = dormName;
        this.rooms = rooms;
        this.googleMapLink = googleMapLink;
        this.address = address;
        this.shortDescription = shortDescription;
    }

    public String getDormName (){
        return dormName;
    }

    public void setDormName(String dormName){
        this.dormName = dormName;
    }
    public List <Room> getRooms (){
        return rooms;
    }
    public void setRooms (List <Room> rooms){
        this.rooms = rooms;
    }
    public String getGoogleMapLink (){
        return googleMapLink;
    }
    public void setGoogleMapLink (String googleMapLink){
        this.googleMapLink = googleMapLink;
    }
    public String getAddress (){
        return address;
    }
    public void setAddress (String address){
        this.address = address;
    }
    public String getShortDescription (){
        return shortDescription;
    }
    public void setShortDescription (String shortDescription){
        this.shortDescription = shortDescription;
    }
    
    public void addRoom(Room room){
        if (room != null){
            rooms.add(room);
        }
    }

    @Override  // implementation of abstract method from the Person class 
    // POLYMORPHISM: Different implementation of displayInfo() method
    public String toString(){
        return "Dorm Name: " + dormName + "\n" +
               "Address: " + address + "\n" +
               "Google Map Link: " + googleMapLink + "\n" +
               "Short Description: " + shortDescription + "\n" +
               "Number of Rooms: " + rooms.size() + "\n" ;
    } 
}