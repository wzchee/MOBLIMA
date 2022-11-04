import java.io.Serializable;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;  


public class MovieTicket implements Serializable{
    private MovieScreening aMovieScreening;
    private int seatNumber;
    private User userObj;
    private Double price;



    public MovieTicket(MovieScreening aMovieScreening, int seatNumber,User userObj,Double price) {
        this.seatNumber = seatNumber;
        this.userObj = userObj;
        this.aMovieScreening = aMovieScreening;
        this.price = price;
    }

    public Cinema getLocation(){
        return aMovieScreening.getMovieScreeningLocation();
    }

    public int getseatNumber(){
        return this.seatNumber;
    }

   public User getUser(){
       return this.userObj;
   }

   public Double getPrice(){
        return this.price;
    }

    public MovieScreening getMovieScreening(){
        return this.aMovieScreening;
    }

    public String toString(){
        LocalDateTime myDateTime = this.getMovieScreening().getMydate();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
        String formatDateTime = myDateTime.format(format);  

        return this.getMovieScreening().getMovieObj().getMovieTitle() + " at " + formatDateTime;
    }


    public static void displayBookings(User sessionUser)throws Exception{
        ArrayList<MovieTicket> movieTicketsHistory = getArrListOfBookings(sessionUser);
        for(int i=0;i<movieTicketsHistory.size();i++){
            System.out.println(movieTicketsHistory.get(i).toString());
            
        }


    }

    private static ArrayList<MovieTicket> getArrListOfBookings(User sessionUser) throws Exception{
        ArrayList<MovieTicket> listOfMovieTix = fileio.readMovieTicketData();
        ArrayList<MovieTicket> movieTicketsHistory = new ArrayList<MovieTicket>();
        for(int i =0;i<listOfMovieTix.size();i++){
            if(listOfMovieTix.get(i).getUser().getEmail().equals(sessionUser.getEmail())){
                movieTicketsHistory.add(listOfMovieTix.get(i));
            }
        }
        return movieTicketsHistory;

    }
    
}



//===============================================================================
// Movie screening should have movie object and then in our movie ticket we can has - a movie screening only then can
// we reference the cinema type, public holiday or not. Then in our movie

//===============================================================================
// ///
