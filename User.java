import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.lang.module.FindException;


public class User implements Serializable{
   
    public static void loggedin(String useremail) throws Exception{
        // User interface after a USER has logged in

        // Firstly, fetch details from user.txt for use in later functions
        User sessionUser = fetchDetails(useremail);

        // User main menu
        int choice = 0;
        while(choice != 6){
            System.out.println("Welcome USER " + sessionUser.name + " !");
            System.out.println("What would you like to do today?");
            System.out.println("1. Search for movie and view movie details");
            System.out.println("2. Check seat availability");
            System.out.println("3. Make a booking");
            System.out.println("4. View booking history");
            System.out.println("5. Review a movie");
            System.out.println("6. Logout");
            System.out.print("Please enter your choice here: ");

            Scanner input = new Scanner(System.in);
            choice = input.nextInt();

            switch(choice){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    // ArrayList<MovieTicket> movieTicketArrList = null;
                    // movieTicketArrList = fileio.readMovieTicketData();
                    // MovieTicket movieTicketToAdd = null;
    //=========================================================================================================
                    usercreateBooking(sessionUser);
                    
                    break;
                case 4:
                    MovieTicket.displayBookings(sessionUser);
                    break;
                    
                case 5:
                    Review.writeReview(sessionUser);
                    break;
                case 6:
                    System.out.println("Logging out as user...");
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Wrong choice. Try again!");
                    break;
            }
        }
    }

    public User(String email, String password, int age, String name, String mobileNumber){
        // constructor
        this.email = email;
        this.password = password;
        this.age = age;
        this.name = name;
        this.mobileNumber = mobileNumber;
    }

    private String email;
    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}

    private String password;
    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

    private int age;
    public int getAge(){return age;}
    public void setAge(int age){this.age = age;}

    private String name;
    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    private String mobileNumber;
    public String getMobileNumber(){return mobileNumber;}
    public void setMobileNumber(String mobileNumber){this.mobileNumber = mobileNumber;}

    private static User fetchDetails(String useremail) throws Exception{
        ArrayList<User> userList = fileio.readUserData();
        for(int i=0; i<userList.size(); i++)
            if(useremail == userList.get(i).getEmail())
                return userList.get(i);

        // shouldn't happen, but just for compilation
        System.out.println("In User.java, no user found");
        return null;
    }

    public static void usercreateBooking(User sessionUser) throws Exception {
        Scanner input = new Scanner(System.in);
        ArrayList<Cineplex> cineplexlist = fileio.readCineplexData();
        System.out.println("Which cineplex would you like to go to?");
        int cineplexcount = 0;
        for(int i=0; i<cineplexlist.size(); i++){
            System.out.println(++cineplexcount + ". " + cineplexlist.get(i).getCineplexName());
        }
        System.out.print("Enter the number corresponding to the cineplex: ");
        int cineplexnum = input.nextInt();
        Cineplex cineplexchosen = cineplexlist.get(cineplexnum-1);

        ArrayList<Movie> movielist = fileio.readMovieData();
        System.out.println("Here are the list of movies to choose from: ");
        int moviecount = 0;
        for(int i=0; i<movielist.size(); i++){
            System.out.println(++moviecount + ". " + movielist.get(i).getMovieTitle());
        }
        System.out.print("Enter the number corresponding to the movie you would like to watch: ");
        int movienum = input.nextInt(); 
        Movie movieObjChosen = movielist.get(movienum-1);
        String movie = movieObjChosen.getMovieTitle();

        ArrayList<MovieScreening> screeningList = MovieScreening.giveScreenTimes(movie);
        System.out.println("Here are the list of showtimes for the movie");
        // Display list of showtimes, pass in movie title
        System.out.println("Movie = " + movie);
        for(int i=0; i<screeningList.size(); i++){
            System.out.print(i+1);
            System.out.print(".\t");
            System.out.print(screeningList.get(i).getMydate().getDayOfMonth());
            System.out.print(" ");
            System.out.print(screeningList.get(i).getMydate().getMonth().toString());
            System.out.print(" ");
            System.out.print(screeningList.get(i).getMydate().getDayOfWeek().toString());
            System.out.print("\n");
            System.out.print("Location: " + screeningList.get(i).getMovieScreeningLocation().getCineplexName());
            System.out.print("\n\n");
        }
        System.out.print("Pick a showtime. Enter the number here: ");
        int screeningnum = input.nextInt();
        MovieScreening screeningchosen = screeningList.get(screeningnum-1);

        // MovieScreening screeningchosen = MovieScreening.retrieveMovieScreening(movie, showtimechosen, cineplexchosen.getCineplexName());
        //MovieScreening screeningchosen = null;
        //screeningchosen = MovieScreening.retrieveMovieScreening(movie, showtimechosen, cineplexchosen.getCineplexName());

        
        // for(int i=0; i<screeninglist.size(); i++){
        //     Boolean bool1 = screeninglist.get(i).getMovieObj().getMovieTitle().equals(movie);
        //     Boolean bool2 = screeninglist.get(i).getMydate().equals(showtimechosen);
        //     Boolean bool3 = screeninglist.get(i).getMovieScreeningLocation().getCineplexName().equals(cineplexchosen.getCineplexName());
        //     //screen cineplex

        //     if(bool1 && bool2 && bool3){
        //         screeningchosen = screeninglist.get(i);
        //         break;
        //     }
        // }

        System.out.println("Here is the cinema layout for the showtime you selected");
        // Display layout of cinema
        screeningchosen.displayLayout();
        System.out.print("Please pick a vacant seat: ");
        String seatIDStr = input.next();
        

        Double computedPrice = screeningchosen.calcPrice(sessionUser);
        // Seat screening
        // Multiple tickets?

        System.out.println("Your seat is secured!");
        System.out.println("Ticket price = $" + computedPrice);
        System.out.print("Proceed (Y/N) ?");
        String option = input.next();

        if(option == "Y"){
            System.out.println("Ticket purchase successful!");
            System.out.println("Here is your ticket ID (TID)");
        }

        //System.out.print("Please enter your Movie Title: ");
        //String movieTitleToFetch = input.next();
        //Movie toFetchMovie = Movie.fetchDetails(movieTitleToFetch);
        //String movieTitleToConcat = toFetchMovie.getMovieTitle();
        
        // System.out.print("Please enter your Cinema Name: ");
        // String cinemaNameToFetch = input.next();
        // Cinema toFetchCinema = Cinema.fetchDetails(cinemaNameToFetch);
        // String cinemaNameToConcat = toFetchCinema.getCinemaName();

//=========================================================================================================

        //PSA: TAKE IN INPUT TO ASSIGN VARIABLES TO THESE SO I CAN CREATE MOVIESCREENING
        int seatId = Integer.parseInt(seatIDStr);
        

//=========================================================================================================
        movieObjChosen.incrementSaleVolume();
        //after movie object incremented we will bubble up to change the
        MovieScreening.updateMovieScreeningWithMovie(movieObjChosen);
        ArrayList<MovieScreening> screeninglist = fileio.readMovieScreeningData();
        
        String movieTitleOfScreeningToChange = null;
        LocalDateTime dateTimeOfScreeningToChange = null;
        String cineplexNameScreeningToChange = null;
        MovieScreening traverser = null;
        
        for(int i = 0 ;i<screeninglist.size();i++){
            traverser =screeninglist.get(i);
            movieTitleOfScreeningToChange = traverser.getMovieObj().getMovieTitle();
            dateTimeOfScreeningToChange = traverser.getMydate();
            cineplexNameScreeningToChange = traverser.getMovieScreeningLocation().getCineplexName();

            if(movieTitleOfScreeningToChange.equals(movie) && dateTimeOfScreeningToChange.equals(screeningchosen.getMydate()) && cineplexNameScreeningToChange.equals(cineplexchosen.getCineplexName())){
                screeningchosen = traverser;
                break;
            }
        }
        screeningchosen.setSeatOccupied(seatId);
        
        fileio.writeMovieData(movielist);
        fileio.writeMovieScreeningData(screeninglist);

        MovieTicket.createBooking(screeningchosen, seatId, sessionUser, computedPrice);
                    

    }




}
