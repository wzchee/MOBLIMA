import java.util.ArrayList;
import java.util.Date;

public class MovieScreening {
    private Movie movieObj;
    //private Cinema movieScreeningLocation;
    private Date time;
    private ArrayList<MovieTicket> ticketsSold;

    public MovieScreening(){

    }

    public MovieTicket createMovieTicket(){
        MovieTicket movTix = new MovieTicket();
        ticketsSold.add(movTix);
        return movTix;
    }


//    public Cinema getCinema(){
//        return movieScreeningLocation;
//    }

}
