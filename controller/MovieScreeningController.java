package controller;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.Scanner;
import java.time.LocalDateTime;
import models.*;
import database.*;

public class MovieScreeningController{

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
                MovieTicketController.updateMovieTicketWithMovieScreening(listOfMovieScreening.get(i));
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
                MovieTicketController.updateMovieTicketWithMovieScreening(listOfMovieScreening.get(i));


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
            if(!myMovieList.get(i).getMovieStatus().equals("End_Of_Showing"))
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
        } catch(IndexOutOfBoundsException i){
            System.out.println("Your choice is not a valid number!");
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
        } catch(IndexOutOfBoundsException i){
            System.out.println("Your choice is not a valid number!");
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
        } catch(IndexOutOfBoundsException i){
            System.out.println("Your choice is not a valid number!");
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
            if(!myMovieList.get(i).getMovieStatus().equals("End_Of_Showing")){
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
        } catch(IndexOutOfBoundsException i){
            System.out.println("Your choice is not a valid number!");
            System.out.println("Returning to staff menu...\n");
            return null;
        }
        Movie movieToFetch = myMovieList.get(indexList.get(movienum-1));


        FileInOut<MovieScreening> movieScreeninginout = new FileInOut<MovieScreening>();
        ArrayList<MovieScreening> myMovieScreeningList = movieScreeninginout.readData(new MovieScreening());
        ArrayList<Integer> indexList2 = new ArrayList<Integer>();
        int indexCount =0;
        for(int i =0;i<myMovieScreeningList.size();i++){
            if(myMovieScreeningList.get(i).getMovieObj().getMovieTitle().equals(movieToFetch.getMovieTitle()) && !myMovieScreeningList.get(i).hasCompleted()){
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
        } catch(IndexOutOfBoundsException i){
            System.out.println("Your choice is not a valid number!");
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
            System.out.println("Either there is no screenings to remove, or your input is invalid.");
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
        MovieTicketController.updateMovieTicketWithMovieScreening(screeningToBeRemoved);
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
            System.out.println("Either there is no screenings to update, or your input is invalid.");
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