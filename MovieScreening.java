import java.util.ArrayList;

import java.util.Scanner;
import java.io.*;
import java.nio.CharBuffer;
import java.time.LocalDateTime;


public class MovieScreening {
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
                            //identify user
    public void createBooking(User sessionUser,int seatID)throws FileNotFoundException, IOException{
        //user will pass in the movie title, cinema hall name,date time in the UI and we will concatenate to
        //fetchDetail for movieScreening (ALL THESE IN THE UI, not in this class)
        User userCreatingBooking = sessionUser;
        Scanner input = new Scanner(System.in);
        CharBuffer inputbuf = CharBuffer.allocate(1000); //User input converted into CharBuffer
        CharBuffer rawtxt = CharBuffer.allocate(100000); //CharBuffer for reading from .txt file
        int buffersize = 0; //size of CharBuffer that was read into
        //create Movie Ticket entry
        
        //========================================================================================================
        System.out.print("Please enter your Movie Title: ");
        String movieTitleToFetch = input.next();
        Movie toFetchMovie = Movie.fetchDetails(movieTitleToFetch);
        String movieTitleToConcat = toFetchMovie.getMovieTitle();



        System.out.print("Please enter your Cinema Name: ");
        String cinemaNameToFetch = input.next();
        Cinema toFetchCinema = Cinema.fetchDetails(cinemaNameToFetch);
        String cinemaNameToConcat = toFetchCinema.getCinemaName();


        System.out.println("Please Enter Date and Time  [YYYY,MM,DD,HH,MM]");
        String date = input.next();
        String[] arrOfString = date.split(",");
        int year = Integer.parseInt(arrOfString[0]);
        int month = Integer.parseInt(arrOfString[1]);
        int day = Integer.parseInt(arrOfString[2]);
        int hour = Integer.parseInt(arrOfString[3]);
        int minute = Integer.parseInt(arrOfString[4]);
        LocalDateTime myDate = LocalDateTime.of(year, month, day, hour, minute, 0);
        String dateTimeToConcat = myDate.toString();

        
        String keyIdOfMovieScreening = movieTitleToConcat.concat(cinemaNameToConcat).concat(dateTimeToConcat);


        
        // check if existing email already exists
        // read from user.txt
        InputStreamReader userin = new FileReader(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\moblima\\movieTicket.txt");
        buffersize = userin.read(rawtxt); // read the file into the CharBuffer, return size of buffer
        rawtxt.rewind(); // return cursor to start of buffer
        
        // recursively check and match emails
        while(rawtxt.position() < buffersize){
            // convert user inputted email into CharBuffer
            inputbuf.clear();
            inputbuf.put(keyIdOfMovieScreening);
            
            // compare the emails and obtain the match results
            BufferMatchReturn result = MOBLIMA.charBufferMatch(rawtxt, inputbuf);
            rawtxt = result.getBuffer();
            if(result.getMatch()){
                System.out.println("MovieScreening already exists!");
                return;
            } else {
                // move cursor until start of next user entry
                char c;
                do{
                    c = rawtxt.get();
                }while(c != '\n');
            }
        }   // if reached this point, that means no existing email exists
        
        // write to user.txt
        // second argument to start writing from end instead of beginning

        String seatArr="";
        for(int i= 0 ;i<100;i++){
            if(i==0){
                seatArr += "[";
                seatArr += "0";
            }else{
                seatArr += ",";
                seatArr += "0";
            }
        }
        seatArr += "]";

        OutputStreamWriter userout = new FileWriter(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\moblima\\movieTicket.txt", true);
        System.out.print("Is it a public holiday: [y/n]");
        String isPublicHoliday = input.next();

        if(isPublicHoliday=="n"){
            userout.write(keyIdOfMovieScreening + "," + movieTitleToConcat + "," + cinemaNameToConcat+ "," + dateTimeToConcat+ "," + seatArr + ","+ "false" + "," + 0 + ",\n");

        }else{
            userout.write(keyIdOfMovieScreening + "," + movieTitleToConcat + "," + cinemaNameToConcat+ "," + dateTimeToConcat+ "," + seatArr + ","+ "true" + "," + 0 + ",\n");
        }
        
        userout.close();
        System.out.println("Movie Screening successfully created!");

        //========================================================================================================

        System.out.println("The total will be" + Double.toString(this.calcPrice(sessionUser)) + ", booking has been made!");
        //create an entry on the txt with attributes (concatenated)aMovieScreening and useremail for MovieTicket
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