import java.util.ArrayList;
import java.io.Serializable;

import java.util.Scanner;
import java.io.*;
import java.nio.CharBuffer;
import java.time.LocalDateTime;


public class MovieScreening implements Serializable{
    private Movie movieObj;
    private Cinema movieScreeningLocation;
    private LocalDateTime mydate;
    private int[] seatArr;
    //seatArr will contain array of 0,1 depending on occupancy
    //NOT SURE IF WANT THIS?
    private boolean isPublicHoliday;
    public int numOfOccupiedSeats;


    public MovieScreening(Movie movieObj,Cinema movieScreeningLocation, LocalDateTime mydate,int[] seatArr,boolean isPublicHoliday,int numOfOccupiedSeats){
        this.movieObj = movieObj;
        this.mydate = mydate;
        this.movieScreeningLocation = movieScreeningLocation;
        this.isPublicHoliday = isPublicHoliday;
        this.seatArr = seatArr;
        this.numOfOccupiedSeats = numOfOccupiedSeats;

    }   

    public Movie getMovieObj(){
        return this.movieObj;
    }

    public boolean getisPublicHoliday(){
        return isPublicHoliday;
    }

    public void setSeatOccupied(int seatId){
        seatArr[seatId] = 1;
        this.numOfOccupiedSeats++;
    }

    public void displayLayout(){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(this.seatArr[i*10 + j]==1){
                    System.out.print("[" + "x" + "] ");
                }else{
                    System.out.print("[" + (i*10 + j) + "] ");
                }
                
            }
            System.out.println();

        }
        

    }


   public Cinema getCinema(){
       return movieScreeningLocation;
   }
    

    public double calcPrice(User user) {
        double price = 7;
        if(this.movieScreeningLocation.isPlatinumSuite()){
            price += 10;
        }

        if(this.getMovieObj().getBlockbuster()){
            price+=2;
        }

        if(this.getisPublicHoliday()){
            price+=2;
        }

        if(user.getAge()<12 || user.getAge()>55){
            price = price * 0.75;
        }


        
        return price;
    }

    public boolean getAvailabilityOfSeats(int seatNumber){
        if(this.seatArr[seatNumber]==0){
            return true;
        }else{
            return false;
        }
    }

    public MovieTicket createBooking()

    public static MovieScreening fetchDetails(String keyID) throws FileNotFoundException, IOException{
        // read from staff.txt
        InputStreamReader MovieScreening = new FileReader(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\moblima\\staff.txt");
        CharBuffer rawtxt = CharBuffer.allocate(10000);
        int buffersize = MovieScreening.read(rawtxt); // read the file into the CharBuffer, return size of buffer
        MovieScreening.close();
        rawtxt.rewind();

        // search for the matching Key id
        CharBuffer inputbuf = CharBuffer.allocate(1000);
        //Strings to store after we cast buffer into Strings
        String movieTitleStr = null; //initialize attribute variables other than email
        String cinemaNameStr = null;
        String dateTimeStr = null;
        String seatArrStr = null;
        String isPublicHolidayStr = null;
        String numOfOccupiedSeatstr = null;



        //buffer to take in the characters

        CharBuffer movieTitleToFetch = CharBuffer.allocate(1000); //initialize corresponding CharBuffer attribute
        CharBuffer cinemaNameToFetch = CharBuffer.allocate(1000);
        CharBuffer dateTimeToFetch = CharBuffer.allocate(1000);
        CharBuffer seatArrToFetch = CharBuffer.allocate(1000);
        CharBuffer isPublicHolidayToFetch = CharBuffer.allocate(1000);
        CharBuffer numOfOccupiedSeatsToFetch = CharBuffer.allocate(1000);


        while(rawtxt.position() < buffersize){
            // convert inputted KeyID into CharBuffer for comparison
            inputbuf.clear();
            inputbuf.put(keyID);

            // compare the emails and obtain the match results
            BufferMatchReturn result = MOBLIMA.charBufferMatch(rawtxt, inputbuf);
            rawtxt = result.getBuffer();
            if(result.getMatch()){
                // MovieScreening Keyid matched. read ALL corresponding records

                // reading Movie Title
                char c = rawtxt.get();
                do{
                    movieTitleToFetch.append(c); //append individual characters into comparison buffer
                    c = rawtxt.get();
                }while(c != ','); //until reached the end of the password element
                c = rawtxt.get(); //move to the next attribute

                // reading Cinema Name
                c = rawtxt.get();
                do{
                    cinemaNameToFetch.append(c); //append individual characters into comparison buffer
                    c = rawtxt.get();
                }while(c != ','); //until reached the end of the name element
                c = rawtxt.get(); //move to the next attribute

                // reading date
                c = rawtxt.get();
                do{
                    dateTimeToFetch.append(c); //append individual characters into comparison buffer
                    c = rawtxt.get();
                }while(c != ','); //until reached the end of the name element
                c = rawtxt.get(); //move to the next attribute

                // reading seat array
                c = rawtxt.get();
                do{
                    seatArrToFetch.append(c); //append individual characters into comparison buffer
                    c = rawtxt.get();
                }while(c != ','); //until reached the end of the name element
                c = rawtxt.get(); //move to the next attribute

                // reading publicHoliday
                c = rawtxt.get();
                do{
                    isPublicHolidayToFetch.append(c); //append individual characters into comparison buffer
                    c = rawtxt.get();
                }while(c != ','); //until reached the end of the name element
                c = rawtxt.get(); //move to the next attribute


                // reading numOfOccupiedSeats
                c = rawtxt.get();
                do{
                    numOfOccupiedSeatsToFetch.append(c); //append individual characters into comparison buffer
                    c = rawtxt.get();
                }while(c != ','); //until reached the end of the name element
                c = rawtxt.get(); //move to the next attribute

            } else {
                // move cursor until start of next user entry
                char c;
                do{
                    c = rawtxt.get();
                }while(c != '\n');
            }
        }
        //convert movieTitle buffer to string and fetchDetail to get the movie object
        movieTitleStr = movieTitleToFetch.toString();
        Movie movieObj = Movie.fetchDetails(movieTitleStr);
        
        //convert Cinema name buffer to string and fetchDetail to get the cinema object

        cinemaNameStr = cinemaNameToFetch.toString();
        Cinema cinemaObj = Cinema.fetchDetails(cinemaNameStr);

        //convert dateTime buffer to string and call parse to get the Datetime object

        dateTimeStr = dateTimeToFetch.toString();
        LocalDateTime myDateTime = LocalDateTime.parse(dateTimeStr);


        // convert the string to a string array and convert string array to int array
        seatArrStr = seatArrToFetch.toString();
        seatArrStr = seatArrStr.substring(1, seatArrStr.length() - 1);
        String[] strSeatArr = seatArrStr.split(",");
        int[] mySeatArr = new int[strSeatArr.length];

        for (int i = 0; i < strSeatArr.length; i++) {
            mySeatArr[i] = Integer.valueOf(strSeatArr[i]);
        }



        isPublicHolidayStr = isPublicHolidayToFetch.toString();
        boolean myisPublicHoliday = (isPublicHolidayStr=="true") ? true:false;

        numOfOccupiedSeatstr = numOfOccupiedSeatsToFetch.toString();
        int numOfOccupiedSeatsInte = Integer.parseInt(numOfOccupiedSeatstr);



        

        

        return new MovieScreening(movieObj,cinemaObj,myDateTime,mySeatArr,myisPublicHoliday, numOfOccupiedSeatsInte);
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