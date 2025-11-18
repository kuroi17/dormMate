package ui;
import model.Student;


    /*
     *  Main driver class to test those methods and\
     * attributes from other class
     * @author Kuroi
     * 
     */

public class Main {


    public static void main (String[] args){

        // sutdent object

        Student student1 = new Student(
             "Juan Dela Cruz",              
            "juan.delacruz@email.com",      
            "09123456789",               
            "Manila, Philippines",          
            "2021-12345",                  
            "Batangas State University", 
            5000.00          
            );

            System.out.println(student1.displayInfo());  // abstract method from Person class na inoverride sa Student class

        
    }
}