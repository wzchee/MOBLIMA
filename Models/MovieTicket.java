package Models;
import java.io.Serializable;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;  
/**
 Represents the MovieTicket object after a user has purchased it
 @author Oliver Low
 @version 1.0
 @since 2022-11-7
*/

public class MovieTicket implements Serializable{
    // Include an array of public holiday dates to check against that of the movie screening and if its pub hol, then change price
    
    
    /** 
     * Contains the movie screening object which is the session
     * 
     */
    private MovieScreening movieScreening;
    /** 
     * Contains the seatID that the user chose
     * 
     */
    private int seatNumber;
    /** 
     * Contains the user object 
     * 
     */
    private User userObj;
    /** 
     * Contains the price of the ticket
     * 
     */
    private Double price;
    /** 
     * Contains the ticket ID
     * 
     */
    private String TID;
    /** 
     * Contains the ticket ID
     * 
     */
    private LocalDateTime dateOfPurchase;



    public MovieTicket(MovieScreening movieScreening, int seatNumber,User userObj,Double price,LocalDateTime dateOfPurchase,String TID) throws Exception {
        this.seatNumber = seatNumber;
        this.userObj = userObj;
        this.movieScreening = movieScreening;
        this.price = price;
        this.dateOfPurchase = dateOfPurchase;
        this.TID = TID;

    }
    
    
    

    public MovieTicket(){

    }

    /**returns the ticket ID 
     * 
     * @return String is the ticket ID
     */
    public String getTID(){
        return this.TID;
    }

    /** 
     * getter for cinema location
     * 
     * @return Cinema which is the Cinema location of this screening
     */
    public Cinema getLocation(){
        return movieScreening.getMovieScreeningLocation();
    }

    
    /** 
     * getter for the seat number
     * 
     * @return int for the seat number
     */
    public int getseatNumber(){
        return this.seatNumber;
    }

   
   /** 
    * getter for user object
    * 
    * @return User object that this ticket has
    */
   public User getUser(){
       return this.userObj;
   }

   
   /**
    * getter for the price of this ticket 
    * 
    * @return Double which is the price of this ticket
    */
   public Double getPrice(){
        return this.price;
    }

    
    /** 
     * getter for movie screening that is associated with this ticket
     * 
     * @return MovieScreening
     */
    public MovieScreening getMovieScreening(){
        return this.movieScreening;
    }

    
    /** 
     * setter for the movie screening
     * 
     * @param movieScreeningToSet that we want to set the MovieScreening object to
     */
    public void setMovieScreening(MovieScreening movieScreeningToSet){
        this.movieScreening = movieScreeningToSet;
    }


    /** 
     * getter for movie ticket datetime
     * 
     * @return LocalDateTime which is the time at which the ticket was purchased
     */
    public LocalDateTime getDateOfPurchase(){
        return this.dateOfPurchase;
    }

    
    /** 
     * setter for movie ticket datetime
     * 
     * @param dateOfPurchase is the time at which the ticket was purchased
     */
    public void setDateOfPurchase(LocalDateTime dateOfPurchase){
        this.dateOfPurchase = dateOfPurchase;
    }

    
    /** 
     * gives a string representation of this ticket
     * 
     * @return String
     */
    public String toString(){
        LocalDateTime myDateTime = this.getMovieScreening().getMydate();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
        String formatDateTime = myDateTime.format(format);  

        return this.getMovieScreening().getMovieObj().getMovieTitle() + " at " + formatDateTime;
    }




    

    

    



}



//===============================================================================
// Movie screening should have movie object and then in our movie ticket we can has - a movie screening only then can
// we reference the cinema type, public holiday or not. Then in our movie

//===============================================================================
// ///
