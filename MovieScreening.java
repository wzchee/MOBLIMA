import java.util.ArrayList;
import java.util.Date;



public class MovieScreening {
    private Movie movieObj;
    private Cinema movieScreeningLocation;
//    private Date time;
    private Seat[] seatArr;
    private ArrayList<MovieTicket> ticketsSold;
    //NOT SURE IF WANT THIS?
    private boolean isPublicHoliday;
    public int numOfOccupiedSeats;


    public MovieScreening(Movie movieObj,Cinema movieScreeningLocation,boolean isPublicHoliday){
        this.movieObj = movieObj;
        this.movieScreeningLocation = movieScreeningLocation;
        this.isPublicHoliday = isPublicHoliday;
        for(int i=0;i<movieScreeningLocation.getNumOfSeats();i++){
            seatArr[i] = new Seat(i,false);
        }
        this.numOfOccupiedSeats = 0;
    }   

    public void incrementOccupiedSeat(){
        this.numOfOccupiedSeats++;
    }

    public void displayLayout(){
        for(int i=0;i<movieScreeningLocation.getNumOfSeats()/10;i++){
            for(int j=0;j<10;j++){
                if(this.seatArr[i*10 + j].getIsOccupied()){
                    System.out.print("[" + "x" + "] ");
                }else{
                    System.out.print("[" + this.seatArr[i*10 + j].getId() + "] ");
                }
                
            }
            System.out.println();

        }
        

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