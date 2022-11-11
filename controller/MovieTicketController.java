package controller;
import java.util.*;
import java.time.LocalDateTime;
import models.*;
import database.*;

public class MovieTicketController {
    
    /** 
     * Calls the getArrListOfBookings that will return an arraylist of MovieTickets that is associated with a user
     * 
     * @param sessionUser which is the user object associated with the MovieTicket object
     * @throws Exception
     */
    //Use getArrListOfBookings to print out the toString
    public static void displayBookings(User sessionUser)throws Exception{
        ArrayList<MovieTicket> movieTicketsHistory = getArrListOfBookings(sessionUser);
        for(int i=0;i<movieTicketsHistory.size();i++){
            System.out.println(movieTicketsHistory.get(i).toString());
            
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
