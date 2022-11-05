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
    private int numOfOccupiedSeats;
    private boolean hasCompleted;


    public MovieScreening(Movie movieObj,Cinema movieScreeningLocation, LocalDateTime mydate,int[] seatArr,boolean isPublicHoliday,int numOfOccupiedSeats, boolean hasCompleted){
        this.movieObj = movieObj;
        this.mydate = mydate;
        this.movieScreeningLocation = movieScreeningLocation;
        this.isPublicHoliday = isPublicHoliday;
        this.seatArr = seatArr;
        this.numOfOccupiedSeats = numOfOccupiedSeats;
        this.hasCompleted = hasCompleted;

    }   

    public MovieScreening(){

    }

    public boolean hasCompleted(){
        return this.hasCompleted;
    }

    public void setHasCompleted(boolean hasCompleted){
        this.hasCompleted = hasCompleted;
    }

    public Movie getMovieObj() {
        return movieObj;
    }

    public void setMovieObj(Movie movieObj) {
        this.movieObj = movieObj;
    }

    public Cinema getMovieScreeningLocation() {
        return movieScreeningLocation;
    }

    public void setMovieScreeningLocation(Cinema movieScreeningLocation) {
        this.movieScreeningLocation = movieScreeningLocation;
    }

    public LocalDateTime getMydate() {
        return mydate;
    }

    public void setMydate(LocalDateTime mydate) {
        this.mydate = mydate;
    }

    public int[] getSeatArr() {
        return seatArr;
    }

    public void setSeatArr(int[] seatArr) {
        this.seatArr = seatArr;
    }

    public boolean isPublicHoliday() {
        return isPublicHoliday;
    }

    public void setPublicHoliday(boolean publicHoliday) {
        isPublicHoliday = publicHoliday;
    }

    public int getNumOfOccupiedSeats() {
        return numOfOccupiedSeats;
    }

    public void setNumOfOccupiedSeats(int numOfOccupiedSeats) {
        this.numOfOccupiedSeats = numOfOccupiedSeats;
    }




    public void setSeatOccupied(int seatId){
        seatArr[seatId] = 1;
        this.numOfOccupiedSeats++;
    }

    public void displayLayout(){
        for (int i = 0; i < 11; i++) {
            if (i == 5) System.out.print("       <ENT>");
            else System.out.print("     ");
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            if (i == 0) System.out.print("     ");
            System.out.print("<_" + i + "> ");
            if (i == 1 || i == 7) System.out.print("    ");
        }
        System.out.println();
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if (j == 0) System.out.print("<" + i + "_> ");
                if(this.seatArr[i*10 + j]==1){
                    System.out.print("[" + " x" + "] ");
                }else{
                    if (i == 0) System.out.print("[0"+ (i*10 + j) + "] ");
                    else System.out.print("[" + (i*10 + j) + "] ");
                }
                if (j == 1 || j == 7) System.out.print("    ");
            }
            System.out.println();
    
        }
        System.out.println("<EXIT>                         SCREEN");
    }

    



    public boolean getAvailabilityOfSeats(int seatNumber){
        if(this.seatArr[seatNumber]==0){
            return true;
        }else{
            return false;
        }
    }

    public double calcPrice(User user) throws Exception{

        FileInOut<Configurables> configinout = new FileInOut<Configurables>();
        ArrayList<Configurables> configList = configinout.readData(new Configurables());
        Configurables config = configList.get(0);
        
        double price = config.getBasePrice();
        if(this.movieScreeningLocation.isPlatinumSuite()){
            price += 10;
        }

        if(this.getMovieObj().getBlockbuster()){
            price+=2;
        }

        if(config.holidayMatch(mydate)){
            price+=2;
        }

        if(user.getAge()<12 || user.getAge()>55){
            price = price * 0.75;
        }


        
        return price;
    }

    public static void updateMovieScreeningWithMovie(Movie movieToBeChanged) throws Exception{
        ArrayList<MovieScreening> listOfMovieScreening = null;

        FileInOut<MovieScreening> screeninginout = new FileInOut<MovieScreening>();
        listOfMovieScreening = screeninginout.readData(new MovieScreening());

        //listOfMovieScreening = fileio.readMovieScreeningData();
        for(int i=0;i<listOfMovieScreening.size();i++){
            if(listOfMovieScreening.get(i).getMovieObj().getMovieTitle().equals(movieToBeChanged.getMovieTitle())){
                listOfMovieScreening.get(i).setMovieObj(movieToBeChanged);
                MovieTicket.updateMovieTicketWithMovieScreening(listOfMovieScreening.get(i));
            }
        }
        screeninginout.writeData(listOfMovieScreening, new MovieScreening());
    } 

    public static void removeMovieScreeningWithMovie(Movie movieToRemove) throws Exception{
        ArrayList<MovieScreening> listOfMovieScreening = null;
        FileInOut<MovieScreening> movieScreeninginout = new FileInOut<MovieScreening>();
        listOfMovieScreening = movieScreeninginout.readData(new MovieScreening());
        

        
        for(int i=0;i<listOfMovieScreening.size();i++){
            if(listOfMovieScreening.get(i).getMovieObj().getMovieTitle().equals(movieToRemove.getMovieTitle())){
                // listOfMovieScreening.remove(i);
                listOfMovieScreening.get(i).setMovieObj(movieToRemove);
                listOfMovieScreening.get(i).setHasCompleted(true);
                MovieTicket.updateMovieTicketWithMovieScreening(listOfMovieScreening.get(i));


            }
        }
        movieScreeninginout.writeData(listOfMovieScreening, new MovieScreening());
    } 

    public static void createMovieScreening() throws Exception{
        

        
        
        Scanner input = new Scanner(System.in);
        
        FileInOut<MovieScreening> movieScreeninginout = new FileInOut<MovieScreening>();
        ArrayList<MovieScreening> myMovieScreeningList = movieScreeninginout.readData(new MovieScreening());
        
        MovieScreening movieScreeningToAdd = null;


        // We will take in movie title and use it as a keyID to fetchDetail that spits out Movie Object
        FileInOut<Movie> movieinout = new FileInOut<Movie>();
        ArrayList<Movie> myMovieList = movieinout.readData(new Movie());
        System.out.println("Here is the full list of movies");
        for(int i=0; i<myMovieList.size(); i++){
            if(!myMovieList.get(i).getMovieStatus().equals("End_of_Showing"))
                System.out.println(i+1 + ". " + myMovieList.get(i).getMovieTitle());
        }
        System.out.print("Enter the number corresponding to the movie: ");
        int movienum = input.nextInt();
        String dump = input.nextLine();
        
        Movie movieToFetch = myMovieList.get(movienum-1);
        
        FileInOut<Cineplex> cineplexio = new FileInOut<Cineplex>();
        ArrayList<Cineplex> cineplexList = cineplexio.readData(new Cineplex());
        System.out.println("Please choose from an existing list of Cineplexes: \n");
        for(int i=0; i<cineplexList.size(); i++){
            System.out.println(i+1 + ". " + cineplexList.get(i).getCineplexName());
        }
        System.out.print("Enter the number corresponding to the cineplex: ");
        int cineplexnum = input.nextInt();
        dump = input.nextLine();
        String cineplexNameToFetch = cineplexList.get(cineplexnum-1).getCineplexName();

        FileInOut<Cinema> cinemaio = new FileInOut<Cinema>();
        ArrayList<Cinema> cinemaList = cinemaio.readData(new Cinema());
        System.out.print("Please choose from an existing list of Cinemas: \n");
        int cinemacount = 0;
        ArrayList<Integer> indexlist = new ArrayList<Integer>();
        for(int i=0; i<cinemaList.size(); i++){
            if(cinemaList.get(i).getCineplexName().equals(cineplexNameToFetch))
                System.out.println(++cinemacount + ". " + cinemaList.get(i).getCinemaName());
                indexlist.add(i);
        }
        System.out.print("Enter the number corresponding to the cinema: ");
        int cinemanum = input.nextInt();
        dump = input.nextLine();
        Cinema cinemaToFetch = cinemaList.get(cinemanum-1);
        
        




        // We will ask for date time in this format and call toString to get string representation 
        // and next time with the string we can call ParseDateTime to reverse the string back to an actual LocalDateTime object
        System.out.println("Please Enter Date and Time  [YYYY,MM,DD,HH,MIN]");
        String date = input.nextLine();
        String[] arrOfString = date.split(",");
        int year = Integer.parseInt(arrOfString[0]);
        int month = Integer.parseInt(arrOfString[1]);
        int day = Integer.parseInt(arrOfString[2]);
        int hour = Integer.parseInt(arrOfString[3]);
        int minute = Integer.parseInt(arrOfString[4]);
        LocalDateTime myDate = LocalDateTime.of(year, month, day, hour, minute, 0);
        

        
        
        int[] myArr = new int[100];
        for(int j=0;j<100;j++){
            myArr[j] = 0;
        }

        System.out.print("Is it a public holiday: [y/n]");
        String isPublicHolidayInput = input.nextLine();
        boolean isPublicHoliday = true;
        //ask staff if it is public holiday
        if(isPublicHolidayInput=="n"){
            isPublicHoliday = false;
        }

        movieScreeningToAdd = new MovieScreening(movieToFetch, cinemaToFetch, myDate, myArr, isPublicHoliday,0,false);
        myMovieScreeningList.add(movieScreeningToAdd);
        movieScreeninginout.writeData(myMovieScreeningList, new MovieScreening());
    }

    
    public static MovieScreening movieScreeningToChange(ArrayList<MovieScreening> listOfMovieScreenings) throws Exception{
        Scanner input = new Scanner(System.in);
        
        // We will take in movie title and use it as a keyID to fetchDetail that spits out Movie Object
        FileInOut<Movie> movieinout = new FileInOut<Movie>();
        ArrayList<Movie> myMovieList = movieinout.readData(new Movie());
        System.out.println("Here is the full list of movies");
        for(int i=0; i<myMovieList.size(); i++){
            if(!myMovieList.get(i).getMovieStatus().equals("End_of_Showing"))
                System.out.println(i+1 + ". " + myMovieList.get(i).getMovieTitle());
        }
        System.out.print("Enter the number corresponding to the movie: ");
        int movienum = input.nextInt();
        String dump = input.nextLine();
        
        Movie movieToFetch = myMovieList.get(movienum-1);
        String movieTitle = movieToFetch.getMovieTitle();
        
        FileInOut<Cineplex> cineplexio = new FileInOut<Cineplex>();
        ArrayList<Cineplex> cineplexList = cineplexio.readData(new Cineplex());
        System.out.println("Please choose from an existing list of Cineplexes: \n");
        for(int i=0; i<cineplexList.size(); i++){
            System.out.println(i+1 + ". " + cineplexList.get(i).getCineplexName());
        }
        System.out.print("Enter the number corresponding to the cineplex: ");
        int cineplexnum = input.nextInt();
        dump = input.nextLine();
        String cineplexTitle = cineplexList.get(cineplexnum-1).getCineplexName();

        FileInOut<Cinema> cinemaio = new FileInOut<Cinema>();
        ArrayList<Cinema> cinemaList = cinemaio.readData(new Cinema());
        System.out.print("Please choose from an existing list of Cinemas: \n");
        int cinemacount = 0;
        ArrayList<Integer> indexlist = new ArrayList<Integer>();
        for(int i=0; i<cinemaList.size(); i++){
            if(cinemaList.get(i).getCineplexName().equals(cineplexTitle))
                System.out.println(++cinemacount + ". " + cinemaList.get(i).getCinemaName());
                indexlist.add(i);
        }
        System.out.print("Enter the number corresponding to the cinema: ");
        int cinemanum = input.nextInt();
        dump = input.nextLine();
        Cinema cinemaToFetch = cinemaList.get(cinemanum-1);
        String cinemaTitle = cinemaToFetch.getCinemaName();
        
        System.out.println("Enter Movie Screening Time ");
        System.out.println("Please Enter Date and Time  [YYYY,MM,DD,HH,MIN]");
        String date = input.next();
        String[] arrOfString = date.split(",");
        int year = Integer.parseInt(arrOfString[0]);
        int month = Integer.parseInt(arrOfString[1]);
        int day = Integer.parseInt(arrOfString[2]);
        int hour = Integer.parseInt(arrOfString[3]);
        int minute = Integer.parseInt(arrOfString[4]);
        LocalDateTime myDate = LocalDateTime.of(year, month, day, hour, minute, 0);

        MovieScreening toChange = null;
        MovieScreening traverser = null;
        
        for(int i=0;i<listOfMovieScreenings.size();i++){
            traverser = listOfMovieScreenings.get(i);
            if(traverser.getMovieObj().getMovieTitle().equals(movieTitle) && 
               traverser.getMydate().equals(myDate) && 
               traverser.getMovieScreeningLocation().getCinemaName().equals(cinemaTitle) && 
               traverser.getMovieScreeningLocation().getCineplexName().equals(cineplexTitle)){
                toChange = traverser;
                break;
            }
        }

        return toChange;



    }

    public static void removeMovieScreening() throws Exception{
        System.out.println("Remove MovieScreening: ");
        ArrayList<MovieScreening> myMovieScreeningList = null;
        FileInOut<MovieScreening> movieScreeninginout = new FileInOut<MovieScreening>();
        myMovieScreeningList = movieScreeninginout.readData(new MovieScreening());
        MovieScreening toBeRemove = movieScreeningToChange(myMovieScreeningList);
        toBeRemove.setHasCompleted(true);
        MovieTicket.updateMovieTicketWithMovieScreening(toBeRemove);
        movieScreeninginout.writeData(myMovieScreeningList, new MovieScreening());;
        
    }
    
    // public static void updateMovieScreening() throws Exception{
    //     ArrayList<Cineplex> cineplexlist = fileio.readCineplexData();
    //     System.out.println("Which cineplex is the movie Screening in?");
    //     int cineplexcount = 0;
    //     for(int i=0; i<cineplexlist.size(); i++){
    //         System.out.println(++cineplexcount + ". " + cineplexlist.get(i).getCineplexName());
    //     }

    //     System.out.print("Enter the number corresponding to the cineplex: ");
    //     int cineplexnum = input.nextInt();
        
        
    //     String cineplexchosen = cineplexlist.get(cineplexnum-1).getCineplexName();

    //     ArrayList<Movie> movielist = fileio.readMovieData();
    //     System.out.println("Here are the list of movies to choose from: ");
    //     int moviecount = 0;
    //     for(int i=0; i<movielist.size(); i++){
    //         System.out.println(++moviecount + ". " + movielist.get(i).getMovieTitle());
    //     }
    //     System.out.print("Enter the number corresponding to the movie you would like to change: ");
    //     int movienum = input.nextInt(); 
    //     Movie movieObjChosen = movielist.get(movienum-1);
        
        
    //     String movie = movieObjChosen.getMovieTitle();

    //     ArrayList<LocalDateTime> screeningtimelist = MovieScreening.giveScreenTimes(movie);
    //     System.out.println("Here are the list of showtimes for the movie");
    //     // Display list of showtimes, pass in movie title
    //     System.out.println("Movie = " + movie);
    //     for(int i=0; i<screeningtimelist.size(); i++){
    //         System.out.print(i+1);
    //         System.out.print(".\t");
    //         System.out.print(screeningtimelist.get(i).getDayOfMonth());
    //         System.out.print(" ");
    //         System.out.print(screeningtimelist.get(i).getMonth().toString());
    //         System.out.print(" ");
    //         System.out.print(screeningtimelist.get(i).getDayOfWeek().toString());
    //         System.out.print("\n");
    //     }
    //     System.out.print("Pick a showtime. Enter the number here: ");
    //     int showtimenum = input.nextInt();


    //     LocalDateTime showtimechosen = screeningtimelist.get(showtimenum-1);



    //     ArrayList<MovieScreening> screenings = fileio.readMovieScreeningData();
    //     String screeningMovieTitle = null;
    //     LocalDateTime screeningDateTime = null;
    //     String screeningCineplex = null;
    //     for(int i=0;i<screenings.size();i++){
    //         screeningMovieTitle = screenings.get(i).getMovieObj().getMovieTitle();
    //         screeningDateTime = screenings.get(i).getMydate();
    //         screeningMovieTitle = screenings.get(i).getMovieScreeningLocation().getCineplexName();
    //         if()
    //     }


    //     // ArrayList<MovieScreening> listOfMovieScreenings = fileio.readMovieScreeningData();
    //     // MovieScreening toBeChanged = movieScreeningToChange(listOfMovieScreenings);
    //     // System.out.println("Please Enter Date and Time  [YYYY,MM,DD,HH,MIN]");
    //     // String date = input.next();
    //     // String[] arrOfString = date.split(",");
    //     // int year = Integer.parseInt(arrOfString[0]);
    //     // int month = Integer.parseInt(arrOfString[1]);
    //     // int day = Integer.parseInt(arrOfString[2]);
    //     // int hour = Integer.parseInt(arrOfString[3]);
    //     // int minute = Integer.parseInt(arrOfString[4]);
    //     // LocalDateTime myDate = LocalDateTime.of(year, month, day, hour, minute, 0);

    //     // toBeChanged.setMydate(myDate);
    //     // fileio.writeMovieScreeningData(listOfMovieScreenings);
        
    // }

    public static ArrayList<MovieScreening> giveScreenTimes(String movieTitle) throws Exception{
        ArrayList<MovieScreening> toRetur = new ArrayList<MovieScreening>();
        FileInOut<MovieScreening> movieScreeninginout = new FileInOut<MovieScreening>();
        ArrayList<MovieScreening> mylis = movieScreeninginout.readData(new MovieScreening());
        for(int i=0;i<mylis.size();i++){
            if(mylis.get(i).getMovieObj().getMovieTitle().equals(movieTitle) && !mylis.get(i).hasCompleted()){
                toRetur.add(mylis.get(i));
            }
        }

        return toRetur;
    }

    
    public static MovieScreening retrieveMovieScreening(String movieTitleOfMovieScreening,LocalDateTime mydateOfMovieScreening,String myCineplexOfMovieScreening) throws Exception{
       
        FileInOut<MovieScreening> movieScreeninginout = new FileInOut<MovieScreening>();
        ArrayList<MovieScreening> listOfMovieScreening = movieScreeninginout.readData(new MovieScreening());
        String movieTitle = null;
        LocalDateTime mydate = null;
        String myCineplex = null;
        MovieScreening toRetur = null;
        for(int i =0;i<listOfMovieScreening.size();i++){
            movieTitle = listOfMovieScreening.get(i).getMovieObj().getMovieTitle();
            mydate = listOfMovieScreening.get(i).getMydate();
            myCineplex = listOfMovieScreening.get(i).getMovieScreeningLocation().getCineplexName();
            if(movieTitle.equals(movieTitleOfMovieScreening) && mydate.equals(mydateOfMovieScreening) && myCineplex.equals(myCineplexOfMovieScreening)){
                toRetur = listOfMovieScreening.get(i);
            }
        }

        return toRetur;
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

    // public static MovieScreening fetchDetails(String keyID) throws FileNotFoundException, IOException{
    //     // read from staff.txt
    //     InputStreamReader MovieScreening = new FileReader(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\moblima\\staff.txt");
    //     CharBuffer rawtxt = CharBuffer.allocate(10000);
    //     int buffersize = MovieScreening.read(rawtxt); // read the file into the CharBuffer, return size of buffer
    //     MovieScreening.close();
    //     rawtxt.rewind();

    //     // search for the matching Key id
    //     CharBuffer inputbuf = CharBuffer.allocate(1000);
    //     //Strings to store after we cast buffer into Strings
    //     String movieTitleStr = null; //initialize attribute variables other than email
    //     String cinemaNameStr = null;
    //     String dateTimeStr = null;
    //     String seatArrStr = null;
    //     String isPublicHolidayStr = null;
    //     String numOfOccupiedSeatstr = null;



    //     //buffer to take in the characters

    //     CharBuffer movieTitleToFetch = CharBuffer.allocate(1000); //initialize corresponding CharBuffer attribute
    //     CharBuffer cinemaNameToFetch = CharBuffer.allocate(1000);
    //     CharBuffer dateTimeToFetch = CharBuffer.allocate(1000);
    //     CharBuffer seatArrToFetch = CharBuffer.allocate(1000);
    //     CharBuffer isPublicHolidayToFetch = CharBuffer.allocate(1000);
    //     CharBuffer numOfOccupiedSeatsToFetch = CharBuffer.allocate(1000);


    //     while(rawtxt.position() < buffersize){
    //         // convert inputted KeyID into CharBuffer for comparison
    //         inputbuf.clear();
    //         inputbuf.put(keyID);

    //         // compare the emails and obtain the match results
    //         BufferMatchReturn result = MOBLIMA.charBufferMatch(rawtxt, inputbuf);
    //         rawtxt = result.getBuffer();
    //         if(result.getMatch()){
    //             // MovieScreening Keyid matched. read ALL corresponding records

    //             // reading Movie Title
    //             char c = rawtxt.get();
    //             do{
    //                 movieTitleToFetch.append(c); //append individual characters into comparison buffer
    //                 c = rawtxt.get();
    //             }while(c != ','); //until reached the end of the password element
    //             c = rawtxt.get(); //move to the next attribute

    //             // reading Cinema Name
    //             c = rawtxt.get();
    //             do{
    //                 cinemaNameToFetch.append(c); //append individual characters into comparison buffer
    //                 c = rawtxt.get();
    //             }while(c != ','); //until reached the end of the name element
    //             c = rawtxt.get(); //move to the next attribute

    //             // reading date
    //             c = rawtxt.get();
    //             do{
    //                 dateTimeToFetch.append(c); //append individual characters into comparison buffer
    //                 c = rawtxt.get();
    //             }while(c != ','); //until reached the end of the name element
    //             c = rawtxt.get(); //move to the next attribute

    //             // reading seat array
    //             c = rawtxt.get();
    //             do{
    //                 seatArrToFetch.append(c); //append individual characters into comparison buffer
    //                 c = rawtxt.get();
    //             }while(c != ','); //until reached the end of the name element
    //             c = rawtxt.get(); //move to the next attribute

    //             // reading publicHoliday
    //             c = rawtxt.get();
    //             do{
    //                 isPublicHolidayToFetch.append(c); //append individual characters into comparison buffer
    //                 c = rawtxt.get();
    //             }while(c != ','); //until reached the end of the name element
    //             c = rawtxt.get(); //move to the next attribute


    //             // reading numOfOccupiedSeats
    //             c = rawtxt.get();
    //             do{
    //                 numOfOccupiedSeatsToFetch.append(c); //append individual characters into comparison buffer
    //                 c = rawtxt.get();
    //             }while(c != ','); //until reached the end of the name element
    //             c = rawtxt.get(); //move to the next attribute

    //         } else {
    //             // move cursor until start of next user entry
    //             char c;
    //             do{
    //                 c = rawtxt.get();
    //             }while(c != '\n');
    //         }
    //     }
    //     //convert movieTitle buffer to string and fetchDetail to get the movie object
    //     movieTitleStr = movieTitleToFetch.toString();
    //     Movie movieObj = Movie.fetchDetails(movieTitleStr);
        
    //     //convert Cinema name buffer to string and fetchDetail to get the cinema object

    //     cinemaNameStr = cinemaNameToFetch.toString();
    //     Cinema cinemaObj = Cinema.fetchDetails(cinemaNameStr);

    //     //convert dateTime buffer to string and call parse to get the Datetime object

    //     dateTimeStr = dateTimeToFetch.toString();
    //     LocalDateTime myDateTime = LocalDateTime.parse(dateTimeStr);


    //     // convert the string to a string array and convert string array to int array
    //     seatArrStr = seatArrToFetch.toString();
    //     seatArrStr = seatArrStr.substring(1, seatArrStr.length() - 1);
    //     String[] strSeatArr = seatArrStr.split(",");
    //     int[] mySeatArr = new int[strSeatArr.length];

    //     for (int i = 0; i < strSeatArr.length; i++) {
    //         mySeatArr[i] = Integer.valueOf(strSeatArr[i]);
    //     }



    //     isPublicHolidayStr = isPublicHolidayToFetch.toString();
    //     boolean myisPublicHoliday = (isPublicHolidayStr=="true") ? true:false;

    //     numOfOccupiedSeatstr = numOfOccupiedSeatsToFetch.toString();
    //     int numOfOccupiedSeatsInte = Integer.parseInt(numOfOccupiedSeatstr);



        

        

    //     return new MovieScreening(movieObj,cinemaObj,myDateTime,mySeatArr,myisPublicHoliday, numOfOccupiedSeatsInte);
    // }