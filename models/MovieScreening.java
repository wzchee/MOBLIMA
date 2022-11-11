package models;
import java.util.ArrayList;
import java.io.Serializable;

import java.util.Scanner;

import database.FileInOut;

import java.time.LocalDateTime;
/**
 Represents the MovieScreening object which is a session of a Movie
 @author Oliver Low
 @version 1.0
 @since 2022-11-7
*/

public class MovieScreening implements Serializable{
    /** 
     * movie object that the screening has
     *
     */
    private Movie movieObj;
    /** 
     * cinema object that the screening has
     *
     */
    private Cinema movieScreeningLocation;
    /** 
     * datetime that the screening has
     *
     */
    private LocalDateTime mydate;
    /** 
     * integer array to denote seat availability so it will be an array of 1s and 0s depending on availability
     *
     */
    private int[] seatArr;
    /** 
     * boolean to see if it is a public holiday
     *
     */
    private boolean isPublicHoliday;
    /** 
     * integer to see the numOfOccupiedSeats
     *
     */

    private int numOfOccupiedSeats;

    /** 
     * boolean to see if the screening has been completed
     *
     */
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

    
    /** 
     * getter method for hasCompleted
     * 
     * @return boolean for whether the movie has been completed or not
     * 
     * 
     */
    public boolean hasCompleted(){
        return this.hasCompleted;
    }

    
    /** 
     * setter method for hasCompleted
     * @param hasCompleted Is the boolean that will be changed to
     */
    public void setHasCompleted(boolean hasCompleted){
        this.hasCompleted = hasCompleted;
    }

    
    /** 
     * getter method for the movie
     * 
     * @return Movie which is the movie object that this movie screening object has
     */
    public Movie getMovieObj() {
        return movieObj;
    }

    
    /** 
     * setter method for the Movie
     * 
     * @param movieObj Which is the movie object to set the attribute to
     */
    public void setMovieObj(Movie movieObj) {
        this.movieObj = movieObj;
    }

    
    /** 
     * getter method for Cinema
     * 
     * @return Cinema which is the Cinema that this movie screening object has as attribute
     */
    public Cinema getMovieScreeningLocation() {
        return movieScreeningLocation;
    }

    
    /** 
     * setter method for Cinema
     * 
     * @param movieScreeningLocation which is the Cinema object
     */
    public void setMovieScreeningLocation(Cinema movieScreeningLocation) {
        this.movieScreeningLocation = movieScreeningLocation;
    }

    
    /** 
     * getter method for datetime
     * 
     * @return LocalDateTime which is the date time for when this moviescreening will occur
     */
    public LocalDateTime getMydate() {
        return mydate;
    }

    
    /** 
     * setter method for datetime
     * @param mydate which is the datetime to set to
     */
    public void setMydate(LocalDateTime mydate) {
        this.mydate = mydate;
    }

    
    /** 
     * getter method for integer array for the seats
     * @return int[] Which is the integer array for the seats
     */
    public int[] getSeatArr() {
        return seatArr;
    }

    
    /** 
     * setter method for the integer array representing the seats
     * 
     * @param seatArr Which is the integer array representing the seats
     */
    public void setSeatArr(int[] seatArr) {
        this.seatArr = seatArr;
    }

    
    /** 
     * getter method for public holiday
     * 
     * @return boolean Which is whether it is a public holiday or not
     */
    public boolean isPublicHoliday() {
        return isPublicHoliday;
    }

    
    /** 
     * setter method for public holiday that sets the public holiday
     * 
     * @param publicHoliday Which is whether it is a public holiday or not
     */
    public void setPublicHoliday(boolean publicHoliday) {
        isPublicHoliday = publicHoliday;
    }

    
    /** 
     * getter method for the number of occupied seats
     * 
     * @return int for the number of occupied seats
     */
    public int getNumOfOccupiedSeats() {
        return numOfOccupiedSeats;
    }

    
    /** 
     * setter method for the number of occupied seats
     * 
     * @param numOfOccupiedSeats which takes in the number of occupied seats
     */
    public void setNumOfOccupiedSeats(int numOfOccupiedSeats) {
        this.numOfOccupiedSeats = numOfOccupiedSeats;
    }




    
    /** 
     * setter method that will take in a integer that represents the seat of choice and set it as occupied
     * 
     * @param seatId is the seat number that we will set the index on the int[] to be 1 indicating the occupancy of the seat
     */
    public void setSeatOccupied(int seatId){
        seatArr[seatId] = 1;
        this.numOfOccupiedSeats++;
    }


    /** 
     * to display the seat layout and also indicating which seats has been taken
     * 
     * @param seatId is the seat number that we will set the index on the int[] to be 1 indicating the occupancy of the seat
     */
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

    



    
    /** 
     * takes in seatNumber and we'll check whether the seat is available
     * 
     * @param seatNumber which is the seat that the user has opted for
     * @return boolean that represents whether that seat has been occupied or not
     */
    public boolean getAvailabilityOfSeats(int seatNumber){
        if(this.seatArr[seatNumber]==0){
            return true;
        }else{
            return false;
        }
    }

    
    /** 
     * takes in user information and together with the MovieScreening attributes, we'll compute a price
     * 
     * @param user is the user object that contains information like age that will affect the ticket price
     * @return double
     * @throws Exception
     */
    public double calcPrice(User user) throws Exception{

        FileInOut<Configurables> configinout = new FileInOut<Configurables>();
        ArrayList<Configurables> configList = configinout.readData(new Configurables());
        Configurables config = configList.get(0);

        
        double price = config.getBasePrice();
        System.out.println("\nBase price of a movie ticket: SGD" + String.format("%.2f", price));

        String day = this.mydate.getDayOfWeek().toString();
        if(day=="SATURDAY" || day=="SUNDAY"){
            System.out.println("Weekend Pricing: +SGD 2.00");

            price+=2;
        }


        if(this.movieScreeningLocation.isPlatinumSuite()){
            System.out.println("Platinum Suite Pricing: +SGD 10.00");
            price += 10;
        }

        if(this.getMovieObj().getBlockbuster()){
            System.out.println("Blockbuster Pricing: +SGD 2.00");
            price+=2;
        }

        if(config.holidayMatch(mydate)){
            System.out.println("Holiday Pricing: +SGD 3.00");
            price+=3;
        }

        if(user.getAge()<12){
            System.out.println("Student Pricing (25% discount): -SGD" + String.format("%.2f", price * 0.25));
            price = price * 0.75;
        } else if(user.getAge()>55){
            System.out.println("Senior Citizen Pricing (25% discount): -SGD" + String.format("%.2f", price * 0.25));
            price = price * 0.75;
        }

        System.out.println("======================================");
        
        return price;
    }

    
    /** 
     * when there is a change in the movie object, since this moviescreening object has-a movie, we'll have to change all movie screenings with this movie object to ensure data integrity
     * 
     * @param movieToBeChanged is the movie object that has been changed
     * @throws Exception
     */
    public static void updateMovieScreeningWithMovie(Movie movieToBeChanged) throws Exception{
        ArrayList<MovieScreening> listOfMovieScreening = null;

        FileInOut<MovieScreening> screeninginout = new FileInOut<MovieScreening>();
        listOfMovieScreening = screeninginout.readData(new MovieScreening());
        for(int i=0;i<listOfMovieScreening.size();i++){
            if(listOfMovieScreening.get(i).getMovieObj().getMovieTitle().equalsIgnoreCase(movieToBeChanged.getMovieTitle())){
                listOfMovieScreening.get(i).setMovieObj(movieToBeChanged);
                MovieTicket.updateMovieTicketWithMovieScreening(listOfMovieScreening.get(i));
            }
        }
        screeninginout.writeData(listOfMovieScreening, new MovieScreening());
    } 

    
    /** 
     * when there is a removal of movie object, since this moviescreening object has-a movie, we'll have to remove this remove screening by setting hasCompleted to true
     * @param movieToRemove is the movie object that has been removed
     * @throws Exception
     */
    public static void removeMovieScreeningWithMovie(Movie movieToRemove) throws Exception{
        ArrayList<MovieScreening> listOfMovieScreening = null;
        FileInOut<MovieScreening> movieScreeninginout = new FileInOut<MovieScreening>();
        listOfMovieScreening = movieScreeninginout.readData(new MovieScreening());
        

        
        for(int i=0;i<listOfMovieScreening.size();i++){
            if(listOfMovieScreening.get(i).getMovieObj().getMovieTitle().equalsIgnoreCase(movieToRemove.getMovieTitle())){
                // listOfMovieScreening.remove(i);
                listOfMovieScreening.get(i).setMovieObj(movieToRemove);
                listOfMovieScreening.get(i).setHasCompleted(true);
                MovieTicket.updateMovieTicketWithMovieScreening(listOfMovieScreening.get(i));


            }
        }
        movieScreeninginout.writeData(listOfMovieScreening, new MovieScreening());
    } 

    
    /** 
     * We will show the list of movies and the staff will choose which movie and pass in relevant fields to create a MovieScreening object
     * 
     * @throws Exception
     */
    public static void createMovieScreening() throws Exception{

        Scanner input = new Scanner(System.in);
        
        FileInOut<MovieScreening> movieScreeninginout = new FileInOut<MovieScreening>();
        ArrayList<MovieScreening> myMovieScreeningList = movieScreeninginout.readData(new MovieScreening());
        
        MovieScreening movieScreeningToAdd = null;


        // We will take in movie title and use it as a keyID to fetchDetail that spits out Movie Object
        FileInOut<Movie> movieinout = new FileInOut<Movie>();
        ArrayList<Movie> myMovieList = movieinout.readData(new Movie());
        if(myMovieList.size()==0){
            System.out.println("No available movies");
            System.out.println("Returning to staff menu...\n");
            return;
        }
        System.out.println("Here is the full list of movies");
        for(int i=0; i<myMovieList.size(); i++){
            if(!myMovieList.get(i).getMovieStatus().equals("End_of_Showing"))
                System.out.println(i+1 + ". " + myMovieList.get(i).getMovieTitle());
        }
        System.out.print("Enter the number corresponding to the movie: ");
        int movienum;
        try{
            movienum = Integer.parseInt(input.nextLine());
        } catch(NumberFormatException e){
            System.out.println("Please input a valid number!");
            System.out.println("Returning to staff menu...\n");
            return;
        }
        
        Movie movieToFetch = myMovieList.get(movienum-1);
        
        FileInOut<Cineplex> cineplexio = new FileInOut<Cineplex>();
        ArrayList<Cineplex> cineplexList = cineplexio.readData(new Cineplex());
        System.out.println("Please choose from an existing list of Cineplexes: \n");
        for(int i=0; i<cineplexList.size(); i++){
            System.out.println(i+1 + ". " + cineplexList.get(i).getCineplexName());
        }
        System.out.print("Enter the number corresponding to the cineplex: ");
        int cineplexnum;
        try{
            cineplexnum = Integer.parseInt(input.nextLine());
        } catch(NumberFormatException e){
            System.out.println("Please input a valid number!");
            System.out.println("Returning to staff menu...\n");
            return;
        }
        String cineplexNameToFetch = cineplexList.get(cineplexnum-1).getCineplexName();
        

        FileInOut<Cinema> cinemaio = new FileInOut<Cinema>();
        ArrayList<Cinema> cinemaList = cinemaio.readData(new Cinema());
        System.out.print("Please choose from an existing list of Cinemas: \n");
        int cinemacount = 0;
        ArrayList<Integer> indexlist = new ArrayList<Integer>();
        for(int i=0; i<cinemaList.size(); i++){
            if(cinemaList.get(i).getCineplexName().equalsIgnoreCase(cineplexNameToFetch)){
                System.out.println(++cinemacount + ". " + cinemaList.get(i).getCinemaName());
                indexlist.add(i);
            }

        }
        System.out.print("Enter the number corresponding to the cinema: ");
        int cinemanum;
        try{
            cinemanum = Integer.parseInt(input.nextLine());
        } catch(NumberFormatException e){
            System.out.println("Please input a valid number!");
            System.out.println("Returning to staff menu...\n");
            return;
        }
        Cinema cinemaToFetch = cinemaList.get(indexlist.get(cinemanum-1));

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

        FileInOut<Configurables> configinout = new FileInOut<Configurables>();
        ArrayList<Configurables> configList = configinout.readData(new Configurables());
        Configurables config = configList.get(0);

        movieScreeningToAdd = new MovieScreening(movieToFetch, cinemaToFetch, myDate, myArr, config.holidayMatch(myDate),0,false);
        myMovieScreeningList.add(movieScreeningToAdd);
        movieScreeninginout.writeData(myMovieScreeningList, new MovieScreening());
    }

    
    
    /** 
     * When updating or removing a movie screening, we will ask the staff which movie and then show them the listings. They will chose from the listings and this returns the MovieScreening object that they want to change.
     * 
     * @return MovieScreening
     * @throws Exception
     */
    public static MovieScreening movieScreeningToChange() throws Exception{
        Scanner input = new Scanner(System.in);
        FileInOut<Movie> movieinout = new FileInOut<Movie>();
        ArrayList<Movie> myMovieList = movieinout.readData(new Movie());
        ArrayList<Integer> indexList = new ArrayList<Integer>();
        System.out.println("Here is the full list of movies");
        for(int i=0; i<myMovieList.size(); i++){
            if(!myMovieList.get(i).getMovieStatus().equals("End_of_Showing")){
                System.out.println(i+1 + ". " + myMovieList.get(i).getMovieTitle());
                indexList.add(i);
            }      
        }
        // We will take in movie title and use it as a keyID to fetchDetail that spits out Movie Object

        System.out.print("Enter the number corresponding to the movie: ");
        int movienum;
        try{
            movienum = Integer.parseInt(input.nextLine());
        } catch(NumberFormatException e){
            System.out.println("Please input a valid number!");
            return null;
        }
        Movie movieToFetch = myMovieList.get(indexList.get(movienum-1));


        FileInOut<MovieScreening> movieScreeninginout = new FileInOut<MovieScreening>();
        ArrayList<MovieScreening> myMovieScreeningList = movieScreeninginout.readData(new MovieScreening());
        ArrayList<Integer> indexList2 = new ArrayList<Integer>();
        int indexCount =0;
        for(int i =0;i<myMovieScreeningList.size();i++){
            if(myMovieScreeningList.get(i).getMovieObj().getMovieTitle().equals(movieToFetch.getMovieTitle())){
                indexList2.add(i);
                System.out.print(++indexCount);
                System.out.print(".\t");
                System.out.print(myMovieScreeningList.get(i).getMydate().getDayOfMonth());
                System.out.print(" ");
                System.out.print(myMovieScreeningList.get(i).getMydate().getMonth().toString());
                System.out.print(" ");
                System.out.print(myMovieScreeningList.get(i).getMydate().getDayOfWeek().toString());
                System.out.print("\n");
                System.out.print("Location: " + myMovieScreeningList.get(i).getMovieScreeningLocation().getCineplexName());
                System.out.print("\n\n");
            }
        }
        if(indexCount==0){
            return null;
        }

        System.out.print("Enter the number corresponding to the movie screening: ");

        int choice;
        try{
            choice = Integer.parseInt(input.nextLine());
        } catch(NumberFormatException e){
            System.out.println("Please input a valid number!");
            System.out.println("Returning to staff menu...\n");
            return null;
        }
        MovieScreening movieScreeningToChange = myMovieScreeningList.get(indexList2.get(choice-1));
        return movieScreeningToChange;


    }

    
    /** 
     * Calls movieScreeningToChange() that will allow staff to put in input so we know which movie screening is to be removed
     * 
     * 
     * @throws Exception
     */
    public static void removeMovieScreening() throws Exception{
        System.out.println("Remove MovieScreening: ");
        ArrayList<MovieScreening> myMovieScreeningList = null;
        FileInOut<MovieScreening> movieScreeninginout = new FileInOut<MovieScreening>();
        myMovieScreeningList = movieScreeninginout.readData(new MovieScreening());

        MovieScreening retrievedScreening = movieScreeningToChange();
        if(retrievedScreening == null){
            System.out.println("No screenings to remove");
            System.out.println("Returning to staff menu...\n");
            return;
        }
        String mymovieTitle = retrievedScreening.getMovieObj().getMovieTitle();
        String myDate = retrievedScreening.getMydate().toString();
        String mycineplexname = retrievedScreening.getMovieScreeningLocation().getCineplexName();
        
        MovieScreening screeningTraverser = null;
        MovieScreening screeningToBeRemoved = null;
        
        for(int i=0;i<myMovieScreeningList.size();i++){
            screeningTraverser = myMovieScreeningList.get(i);
            if(screeningTraverser.getMovieObj().getMovieTitle().equals(mymovieTitle) && screeningTraverser.getMydate().toString().equals(myDate) && screeningTraverser.getMovieScreeningLocation().getCineplexName().equals(mycineplexname)){
                screeningToBeRemoved = screeningTraverser;
            }
        }
        screeningToBeRemoved.setHasCompleted(true);

        System.out.println("Movie Screening Successfully deleted");
        MovieTicket.updateMovieTicketWithMovieScreening(screeningToBeRemoved);
        movieScreeninginout.writeData(myMovieScreeningList, new MovieScreening());
        
    }
    
    
    /** 
     * Calls movieScreeningToChange() that will allow staff to put in input so we know which movie screening is to be updated
     * 
     * @throws Exception
     */
    public static void updateMovieScreening() throws Exception{
        Scanner input = new Scanner(System.in);
        FileInOut<MovieScreening> movieScreeninginout = new FileInOut<MovieScreening>();
        ArrayList<MovieScreening> myMovieScreeningList = movieScreeninginout.readData(new MovieScreening());
        MovieScreening retrievedScreening = movieScreeningToChange();
        if(retrievedScreening==null){
            System.out.println("No screenings to update");
            System.out.println("Returning to staff menu...\n");
            return;
        }

        String mymovieTitle = retrievedScreening.getMovieObj().getMovieTitle();
        String myDate = retrievedScreening.getMydate().toString();
        String mycineplexname = retrievedScreening.getMovieScreeningLocation().getCineplexName();
        
        MovieScreening screeningTraverser = null;
        MovieScreening screeningToBeUpdated = null;
        
        for(int i=0;i<myMovieScreeningList.size();i++){
            screeningTraverser = myMovieScreeningList.get(i);
            if(screeningTraverser.getMovieObj().getMovieTitle().equals(mymovieTitle) && screeningTraverser.getMydate().toString().equals(myDate) && screeningTraverser.getMovieScreeningLocation().getCineplexName().equals(mycineplexname)){
                screeningToBeUpdated = screeningTraverser;
            }
        }

        System.out.println("Please Enter New Date and Time  [YYYY,MM,DD,HH,MIN]");
        String date = input.nextLine();
        String[] arrOfString = date.split(",");
        int year = Integer.parseInt(arrOfString[0]);
        int month = Integer.parseInt(arrOfString[1]);
        int day = Integer.parseInt(arrOfString[2]);
        int hour = Integer.parseInt(arrOfString[3]);
        int minute = Integer.parseInt(arrOfString[4]);
        LocalDateTime myDate2 = LocalDateTime.of(year, month, day, hour, minute, 0);
        screeningToBeUpdated.setMydate(myDate2);


        FileInOut<MovieTicket> movieTicketinout = new FileInOut<MovieTicket>();
        ArrayList<MovieTicket> myMovieTicketList = movieTicketinout.readData(new MovieTicket());

        MovieTicket myTicketTraverser = null;
        for(int i = 0;i<myMovieTicketList.size();i++){
            myTicketTraverser = myMovieTicketList.get(i);
            if(myTicketTraverser.getMovieScreening().getMovieObj().getMovieTitle().equals(mymovieTitle) && myTicketTraverser.getMovieScreening().getMydate().toString().equals(myDate) && myTicketTraverser.getMovieScreening().getMovieScreeningLocation().getCineplexName().equals(mycineplexname)){
                myMovieTicketList.get(i).setMovieScreening(screeningToBeUpdated);
            }
        }

        movieScreeninginout.writeData(myMovieScreeningList, new MovieScreening());
        movieTicketinout.writeData(myMovieTicketList, new MovieTicket());


        
    }

    
    /** 
     * Takes movie title and cineplex name and returns an arraylist of moviescreening timees that is available and it is different from movieScreeningToChange because this one is specific to cineplex
     * 
     * @param movieTitle is the movie title that the user has chosen
     * @param cineplexChosen is the cineplex name that the user has chosen
     * @return ArrayList<MovieScreening> contains the list of available movie screenings
     * @throws Exception
     */
    public static ArrayList<MovieScreening> giveScreenTimes(String movieTitle, Cineplex cineplexChosen) throws Exception{
        ArrayList<MovieScreening> toRetur = new ArrayList<MovieScreening>();
        FileInOut<MovieScreening> movieScreeninginout = new FileInOut<MovieScreening>();
        ArrayList<MovieScreening> mylis = movieScreeninginout.readData(new MovieScreening());
        for(int i=0;i<mylis.size();i++){
            if(mylis.get(i).getMovieObj().getMovieTitle().equalsIgnoreCase(movieTitle) && !mylis.get(i).hasCompleted() &&
               mylis.get(i).getMovieScreeningLocation().getCineplexName().equalsIgnoreCase(cineplexChosen.getCineplexName())){
                toRetur.add(mylis.get(i));
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