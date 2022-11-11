package models;
import java.io.Serializable;
import java.util.*;

import database.FileInOut;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;  


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


/** 
 * Calls the getArrListOfBookings that will return an arraylist of MovieTickets that is associated with a user
 * 
 * @param sessionUser which is the user object associated with the MovieTicket object
 * @throws Exception
 */
//Use getArrListOfBookings to print out the toString
    public static void displayBookings(User sessionUser)throws Exception{
        System.out.println("\nHere are your tickets: \n");

        ArrayList<MovieTicket> movieTicketsHistory = getArrListOfBookings(sessionUser);
        for(int i=0;i<movieTicketsHistory.size();i++){
            System.out.println(i+1 + ". " + movieTicketsHistory.get(i).toString());
            
        }


    }


    
    /** 
     * returns the booking history of the user by taking in the user object 
     * 
     * @param sessionUser which is the user object associated with the MovieTicket object
     * @return ArrayList<MovieTicket> is an arraylist of movie tickets
     * @throws Exception
     */
    private static ArrayList<MovieTicket> getArrListOfBookings(User sessionUser) throws Exception{
        
        FileInOut<MovieTicket> movieTixinout = new FileInOut<MovieTicket>();
        ArrayList<MovieTicket> listOfMovieTix = movieTixinout.readData(new MovieTicket());
        
        
        ArrayList<MovieTicket> movieTicketsHistory = new ArrayList<MovieTicket>();
        for(int i =0;i<listOfMovieTix.size();i++){
            if(listOfMovieTix.get(i).getUser().getEmail().equals(sessionUser.getEmail())){
                movieTicketsHistory.add(listOfMovieTix.get(i));
            }
        }
        return sortArrListOfBookings(movieTicketsHistory);

    }



    
    /** 
     * will take in an arraylist of movieTickets and sort them based on the datetime which the MovieScreening attribute has
     * 
     * @param movieTicketsHistory is an arraylist of movieTickets
     * @return ArrayList<MovieTicket> is an arraylist of movieTickets which is sorted based on datetime
     */
    // Sorting for Booking History
    private static ArrayList<MovieTicket> sortArrListOfBookings(ArrayList<MovieTicket> movieTicketsHistory) {
        int j, totalTickets = 0;
        LocalDateTime keyDate;
        for (MovieTicket tix : movieTicketsHistory) totalTickets++;
        for (int i = 1; i < totalTickets; i++) {
            keyDate = movieTicketsHistory.get(i).getMovieScreening().getMydate();
            j = i-1;

            while (j >= 0 && keyDate.isBefore(movieTicketsHistory.get(j).getMovieScreening().getMydate())) {
                Collections.swap(movieTicketsHistory, j, j+1);
                j--;
            }
        }
        return movieTicketsHistory;
    }

    
    /** 
     * When movie screening has been changed, because movie ticket has-a moviescreening object, any change to a particular moviescreening object has to come with an update to the movieticket object(s)
     * 
     * @param movieScreeningThatHasBeenChanged is the moviescreening object that has been changed
     * @throws Exception
     */
    //when a movieScreening has been changed, we will take all the affected movieTicket objects and update (VOID)
    public static void updateMovieTicketWithMovieScreening(MovieScreening movieScreeningThatHasBeenChanged) throws Exception{
        String movieTitleOfMovieScreeningChanged = movieScreeningThatHasBeenChanged.getMovieObj().getMovieTitle();
        LocalDateTime mydateOfMovieScreeningChanged = movieScreeningThatHasBeenChanged.getMydate();
        String myCineplexOfMovieScreeningChanged = movieScreeningThatHasBeenChanged.getMovieScreeningLocation().getCineplexName();

        
        FileInOut<MovieTicket> movieTixinout = new FileInOut<MovieTicket>();
        ArrayList<MovieTicket> listOfMovieTix = movieTixinout.readData(new MovieTicket());

        
        String movieTitle = null;
        LocalDateTime mydate = null;
        String myCineplex = null;
        for(int i =0;i<listOfMovieTix.size();i++){
            movieTitle = listOfMovieTix.get(i).getMovieScreening().getMovieObj().getMovieTitle();
            mydate = listOfMovieTix.get(i).getMovieScreening().getMydate();
            myCineplex = listOfMovieTix.get(i).getMovieScreening().getMovieScreeningLocation().getCineplexName();
            if(movieTitle.equalsIgnoreCase(movieTitleOfMovieScreeningChanged) && mydate.equals(mydateOfMovieScreeningChanged) && myCineplex.equalsIgnoreCase(myCineplexOfMovieScreeningChanged)){
                listOfMovieTix.get(i).setMovieScreening(movieScreeningThatHasBeenChanged);
            }
        }

        movieTixinout.writeData(listOfMovieTix, new MovieTicket());

    }
    

    
    /** 
     * 
     * 
     * takes in user moviescreening object,seatID,user object,price and the current date to create a booking
     * 
     * @param movieScreeningOfChoice is the movie screening object
     * @param seatId is the seat number that the user has chosen
     * @param userBooking is the user object associated with the ticket object
     * @param price is the price of this ticket
     * @param nowDate is the datetime object that the ticket was bought
     * @throws Exception
     */
    public static void createBooking(MovieScreening movieScreeningOfChoice,int seatId,User userBooking,Double price,LocalDateTime nowDate,String TID) throws Exception{
        


        FileInOut<MovieTicket> movieTixinout = new FileInOut<MovieTicket>();
        ArrayList<MovieTicket> movieTicketArrList = movieTixinout.readData(new MovieTicket());

        
        MovieTicket createdMovieTicket = new MovieTicket(movieScreeningOfChoice, seatId, userBooking,price,nowDate,TID);
        movieTicketArrList.add(createdMovieTicket);
        movieTixinout.writeData(movieTicketArrList, new MovieTicket());
    }


}



//===============================================================================
// Movie screening should have movie object and then in our movie ticket we can has - a movie screening only then can
// we reference the cinema type, public holiday or not. Then in our movie

//===============================================================================
// ///
