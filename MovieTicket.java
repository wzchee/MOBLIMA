import java.io.Serializable;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;  


public class MovieTicket implements Serializable{
    // Include an array of public holiday dates to check against that of the movie screening and if its pub hol, then change price
    private MovieScreening movieScreening;
    private int seatNumber;
    private User userObj;
    private Double price;



    public MovieTicket(MovieScreening movieScreening, int seatNumber,User userObj,Double price) {
        this.seatNumber = seatNumber;
        this.userObj = userObj;
        this.movieScreening = movieScreening;
        this.price = price;
    }

    public Cinema getLocation(){
        return movieScreening.getMovieScreeningLocation();
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
        return this.movieScreening;
    }

    public void setMovieScreening(MovieScreening movieScreeningToSet){
        this.movieScreening = movieScreeningToSet;
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
        return sortArrListOfBookings(movieTicketsHistory);

    }

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

    public static void updateMovieTicketWithMovieScreening(MovieScreening movieScreeningThatHasBeenChanged) throws Exception{
        String movieTitleOfMovieScreeningChanged = movieScreeningThatHasBeenChanged.getMovieObj().getMovieTitle();
        LocalDateTime mydateOfMovieScreeningChanged = movieScreeningThatHasBeenChanged.getMydate();
        String myCineplexOfMovieScreeningChanged = movieScreeningThatHasBeenChanged.getMovieScreeningLocation().getCineplexName();

        ArrayList<MovieTicket> listOfMovieTix = fileio.readMovieTicketData();
        String movieTitle = null;
        LocalDateTime mydate = null;
        String myCineplex = null;
        for(int i =0;i<listOfMovieTix.size();i++){
            movieTitle = listOfMovieTix.get(i).getMovieScreening().getMovieObj().getMovieTitle();
            mydate = listOfMovieTix.get(i).getMovieScreening().getMydate();
            myCineplex = listOfMovieTix.get(i).getMovieScreening().getMovieScreeningLocation().getCineplexName();
            if(movieTitle.equals(movieTitleOfMovieScreeningChanged) && mydate.equals(mydateOfMovieScreeningChanged) && myCineplex.equals(myCineplexOfMovieScreeningChanged)){
                listOfMovieTix.get(i).setMovieScreening(movieScreeningThatHasBeenChanged);
            }
        }

        fileio.writeMovieTicketData(listOfMovieTix);
    }
    
}



//===============================================================================
// Movie screening should have movie object and then in our movie ticket we can has - a movie screening only then can
// we reference the cinema type, public holiday or not. Then in our movie

//===============================================================================
// ///
