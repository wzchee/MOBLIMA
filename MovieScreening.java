import java.util.ArrayList;
import java.time.*;



public class MovieScreening {
    private Movie movieObj;
    private Cinema movieScreeningLocation;
    private LocalDate mydate;
    private Seat[] seatArr;
    //NOT SURE IF WANT THIS?
    private boolean isPublicHoliday;
    public int numOfOccupiedSeats;


    public MovieScreening(Movie movieObj,LocalDate mydate,Cinema movieScreeningLocation,boolean isPublicHoliday){
        this.movieObj = movieObj;
        this.mydate = mydate;
        this.movieScreeningLocation = movieScreeningLocation;
        this.isPublicHoliday = isPublicHoliday;
        for(int i=0;i<100;i++){
            seatArr[i] = new Seat(i,false);
        }
        this.numOfOccupiedSeats = 0;

    }   

    public void incrementOccupiedSeat(){
        this.numOfOccupiedSeats++;
    }

    public void displayLayout(){
        for(int i=0;i<10;i++){
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


   public Cinema getCinema(){
       return movieScreeningLocation;
   }
                            //identify user
    public void createBooking(string useremail){
        //user will pass in the movie title, cinema hall name,date time in the UI and we will concatenate to
        //fetchDetail for movieScreening (ALL THESE IN THE UI, not in this class)
        
        
        
        //create an entry on the txt with attributes (concatenated)aMovieScreening and useremail 
    }

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