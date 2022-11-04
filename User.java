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
                    //ArrayList<MovieTicket> movieTicketArrList = null;
                    //movieTicketArrList = fileio.readMovieTicketData();
                    //MovieTicket movieTicketToAdd = null;
    //=========================================================================================================

                    System.out.println("Here are the list of movies to choose from: ");
                    // Display list of movies
                    System.out.print("Which movie would you like to watch? ");
                    String movie = input.next(); 

                    ArrayList<LocalDateTime> screeningtimelist = MovieScreening.giveScreenTimes(movie);
                    System.out.println("Here are the list of showtimes for the movie");
                    // Display list of showtimes, pass in movie title
                    System.out.println("Movie = " + movie);
                    for(int i=0; i<screeningtimelist.size(); i++){
                        System.out.print(i+1);
                        System.out.print(".\t");
                        System.out.print(screeningtimelist.get(i).getDayOfMonth());
                        System.out.print(" ");
                        System.out.print(screeningtimelist.get(i).getMonth().toString());
                        System.out.print(" ");
                        System.out.print(screeningtimelist.get(i).getDayOfWeek().toString());
                        System.out.print("\n");
                    }
                    System.out.print("Pick a showtime. Enter the number here: ");
                    int showtimenum = input.nextInt();

                    ArrayList<MovieScreening> screeninglist = fileio.readMovieScreeningData();
                    //

                    System.out.println("Here is the cinema layout for the showtime you selected");
                    // Display layout of cinema
                    System.out.print("Please pick a vacant seat: ");
                    String seatID = input.next();

                    // Seat screening
                    // Multiple tickets?

                    System.out.println("Your seat is secured!");
                    System.out.println("Ticket price = $9999999");
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
                    MovieScreening movieScreeningOfChoice = null;
                    int seatId = -1;
                    Double price = movieScreeningOfChoice.calcPrice(sessionUser);

    //=========================================================================================================

                    movieTicketToAdd = createBooking(movieScreeningOfChoice, seatId, sessionUser, price);
                    movieTicketArrList.add(movieTicketToAdd);
                    fileio.writeMovieTicketData(movieTicketArrList);

                    break;
                case 4:
                    MovieTicket.displayBookings(sessionUser);
                    break;
                    
                case 5:
                    Review.writeReview(sessionUser);
                    break;
                case 6:
                    System.out.println("Logging out as staff...");
                    System.out.println("Returning to main page...");
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

    public static MovieTicket createBooking(MovieScreening movieScreeningOfChoice,int seatId,User userBooking,Double price){
        movieScreeningOfChoice.getMovieObj().incrementSaleVolume();
        MovieTicket createdMovieTicket = new MovieTicket(movieScreeningOfChoice, seatId, userBooking, price);
        movieScreeningOfChoice.setSeatOccupied(seatId);
        return createdMovieTicket;
    }


}
