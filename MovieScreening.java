import java.util.ArrayList;
import java.util.Date;



public class MovieScreening {
    private Movie movieObj;
    private Cinema movieScreeningLocation;
//    private Date time;
    private Seat[] mySeatArr;
    private ArrayList<MovieTicket> ticketsSold;
    //NOT SURE IF WANT THIS?
    private boolean isPublicHoliday;
    public MovieScreening(Movie movieObj,Cinema movieScreeningLocation,boolean isPublicHoliday){
        this.movieObj = movieObj;
        this.movieScreeningLocation = movieScreeningLocation;
        this.isPublicHoliday = isPublicHoliday;

    }




//    public Cinema getCinema(){
//        return movieScreeningLocation;
//    }

}

//===============================================================================
//    private ArrayList<MovieTicket> ticketsSold; NOT SURE IF WANT THIS?
//===============================================================================

//    public MovieTicket createMovieTicket(){
//        MovieTicket movTix = new MovieTicket();
//        ticketsSold.add(movTix);
//        return movTix;
//    }

//===============================================================================