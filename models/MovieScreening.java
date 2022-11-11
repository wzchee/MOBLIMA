package models;
import java.util.ArrayList;
import java.io.Serializable;

import database.FileInOut;

import java.time.LocalDateTime;
/**
 Represents the MovieScreening object which is a session of a Movie
 @author Oliver Low
 @version 1.0
 @since 2022-11-7
*/

public class MovieScreening implements Serializable{
    /** 
     * movie object that the screening has
     *
     */
    private Movie movieObj;
    /** 
     * cinema object that the screening has
     *
     */
    private Cinema movieScreeningLocation;
    /** 
     * datetime that the screening has
     *
     */
    private LocalDateTime mydate;
    /** 
     * integer array to denote seat availability so it will be an array of 1s and 0s depending on availability
     *
     */
    private int[] seatArr;
    /** 
     * boolean to see if it is a public holiday
     *
     */
    private boolean isPublicHoliday;
    /** 
     * integer to see the numOfOccupiedSeats
     *
     */

    private int numOfOccupiedSeats;

    /** 
     * boolean to see if the screening has been completed
     *
     */
    private boolean hasCompleted;


    public MovieScreening(Movie movieObj,Cinema movieScreeningLocation, LocalDateTime mydate,int[] seatArr,boolean isPublicHoliday,int numOfOccupiedSeats, boolean hasCompleted){
        this.movieObj = movieObj;
        this.mydate = mydate;
        this.movieScreeningLocation = movieScreeningLocation;
        this.isPublicHoliday = isPublicHoliday;
        this.seatArr = seatArr;
        this.numOfOccupiedSeats = numOfOccupiedSeats;
        this.hasCompleted = hasCompleted;

    }   

    public MovieScreening(){

    }

    
    /** 
     * getter method for hasCompleted
     * 
     * @return boolean for whether the movie has been completed or not
     * 
     * 
     */
    public boolean hasCompleted(){
        return this.hasCompleted;
    }

    
    /** 
     * setter method for hasCompleted
     * @param hasCompleted Is the boolean that will be changed to
     */
    public void setHasCompleted(boolean hasCompleted){
        this.hasCompleted = hasCompleted;
    }

    
    /** 
     * getter method for the movie
     * 
     * @return Movie which is the movie object that this movie screening object has
     */
    public Movie getMovieObj() {
        return movieObj;
    }

    
    /** 
     * setter method for the Movie
     * 
     * @param movieObj Which is the movie object to set the attribute to
     */
    public void setMovieObj(Movie movieObj) {
        this.movieObj = movieObj;
    }

    
    /** 
     * getter method for Cinema
     * 
     * @return Cinema which is the Cinema that this movie screening object has as attribute
     */
    public Cinema getMovieScreeningLocation() {
        return movieScreeningLocation;
    }

    
    /** 
     * setter method for Cinema
     * 
     * @param movieScreeningLocation which is the Cinema object
     */
    public void setMovieScreeningLocation(Cinema movieScreeningLocation) {
        this.movieScreeningLocation = movieScreeningLocation;
    }

    
    /** 
     * getter method for datetime
     * 
     * @return LocalDateTime which is the date time for when this moviescreening will occur
     */
    public LocalDateTime getMydate() {
        return mydate;
    }

    
    /** 
     * setter method for datetime
     * @param mydate which is the datetime to set to
     */
    public void setMydate(LocalDateTime mydate) {
        this.mydate = mydate;
    }

    
    /** 
     * getter method for integer array for the seats
     * @return int[] Which is the integer array for the seats
     */
    public int[] getSeatArr() {
        return seatArr;
    }

    
    /** 
     * setter method for the integer array representing the seats
     * 
     * @param seatArr Which is the integer array representing the seats
     */
    public void setSeatArr(int[] seatArr) {
        this.seatArr = seatArr;
    }

    
    /** 
     * getter method for public holiday
     * 
     * @return boolean Which is whether it is a public holiday or not
     */
    public boolean isPublicHoliday() {
        return isPublicHoliday;
    }

    
    /** 
     * setter method for public holiday that sets the public holiday
     * 
     * @param publicHoliday Which is whether it is a public holiday or not
     */
    public void setPublicHoliday(boolean publicHoliday) {
        isPublicHoliday = publicHoliday;
    }

    
    /** 
     * getter method for the number of occupied seats
     * 
     * @return int for the number of occupied seats
     */
    public int getNumOfOccupiedSeats() {
        return numOfOccupiedSeats;
    }

    
    /** 
     * setter method for the number of occupied seats
     * 
     * @param numOfOccupiedSeats which takes in the number of occupied seats
     */
    public void setNumOfOccupiedSeats(int numOfOccupiedSeats) {
        this.numOfOccupiedSeats = numOfOccupiedSeats;
    }




    
    /** 
     * setter method that will take in a integer that represents the seat of choice and set it as occupied
     * 
     * @param seatId is the seat number that we will set the index on the int[] to be 1 indicating the occupancy of the seat
     */
    public void setSeatOccupied(int seatId){
        seatArr[seatId] = 1;
        this.numOfOccupiedSeats++;
    }


    /** 
     * to display the seat layout and also indicating which seats has been taken
     * 
     * @param seatId is the seat number that we will set the index on the int[] to be 1 indicating the occupancy of the seat
     */
    public void displayLayout(){
        for (int i = 0; i < 11; i++) {
            if (i == 5) System.out.print("       <ENT>");
            else System.out.print("     ");
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            if (i == 0) System.out.print("     ");
            System.out.print("<_" + i + "> ");
            if (i == 1 || i == 7) System.out.print("    ");
        }
        System.out.println();
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if (j == 0) System.out.print("<" + i + "_> ");
                if(this.seatArr[i*10 + j]==1){
                    System.out.print("[" + " x" + "] ");
                }else{
                    if (i == 0) System.out.print("[0"+ (i*10 + j) + "] ");
                    else System.out.print("[" + (i*10 + j) + "] ");
                }
                if (j == 1 || j == 7) System.out.print("    ");
            }
            System.out.println();
    
        }
        System.out.println("<EXIT>                         SCREEN");
    }

    



    
    /** 
     * takes in seatNumber and we'll check whether the seat is available
     * 
     * @param seatNumber which is the seat that the user has opted for
     * @return boolean that represents whether that seat has been occupied or not
     */
    public boolean getAvailabilityOfSeats(int seatNumber){
        if(this.seatArr[seatNumber]==0){
            return true;
        }else{
            return false;
        }
    }

    
    /** 
     * takes in user information and together with the MovieScreening attributes, we'll compute a price
     * 
     * @param user is the user object that contains information like age that will affect the ticket price
     * @return double
     * @throws Exception
     */
    public double calcPrice(User user) throws Exception{

        FileInOut<Configurables> configinout = new FileInOut<Configurables>();
        ArrayList<Configurables> configList = configinout.readData(new Configurables());
        Configurables config = configList.get(0);

        
        double price = config.getBasePrice();
        System.out.println("\nBase price of a movie ticket: SGD" + String.format("%.2f", price));

        String day = this.mydate.getDayOfWeek().toString();
        if(day=="SATURDAY" || day=="SUNDAY"){
            System.out.println("Weekend Pricing: +SGD 2.00");

            price+=2;
        }


        if(this.movieScreeningLocation.isPlatinumSuite()){
            System.out.println("Platinum Suite Pricing: +SGD 10.00");
            price += 10;
        }

        if(this.getMovieObj().getBlockbuster()){
            System.out.println("Blockbuster Pricing: +SGD 2.00");
            price+=2;
        }

        if(config.holidayMatch(mydate)){
            System.out.println("Holiday Pricing: +SGD 3.00");
            price+=3;
        }

        if(user.getAge()<12){
            System.out.println("Student Pricing (25% discount): -SGD" + String.format("%.2f", price * 0.25));
            price = price * 0.75;
        } else if(user.getAge()>55){
            System.out.println("Senior Citizen Pricing (25% discount): -SGD" + String.format("%.2f", price * 0.25));
            price = price * 0.75;
        }

        System.out.println("======================================");
        
        return price;
    }
}