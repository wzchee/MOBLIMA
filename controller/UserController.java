package controller;
import java.util.Scanner;

import FileInOut;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.io.Serializable;
import database.*;
import models.*;

public class UserController {
    /**
     * User Interface after a movie-goer logged into MOBLIMA
     * <p>
     * Provides them with the option of seeing movie details, check seat
     * availability and make a booking. Also allows them to write reviews
     * and check their booking history.
     * @param   useremail   Email of the user logged into the system
     * @see     Movie
     * @see     MovieScreening
     * @see     MovieTicket
     * @see     Review
     * @throws  Exception
     */
    public static void loggedin(String useremail) throws Exception{
        // User interface after a USER has logged in

        // Firstly, fetch details from user.txt for use in later functions
        User sessionUser = fetchDetails(useremail);

        // User main menu
        Scanner input = new Scanner(System.in);
        int choice = 0;
        while(choice != 7){
            System.out.println("\nWelcome USER " + sessionUser.getName() + " !");
            System.out.println("What would you like to do today?");
            System.out.println("1. Search for movie and view movie details");
            System.out.println("2. Check seat availability");
            System.out.println("3. Make a booking");
            System.out.println("4. View booking history");
            System.out.println("5. Review a movie");
            System.out.println("6. View top 5 movies based on (1)Rating (2)Sale Volume ");
            System.out.println("7. Logout");
            System.out.print("Please enter your choice here: ");

            try{
                choice = Integer.parseInt(input.nextLine());
            } catch(NumberFormatException e){
                System.out.println("Please input a valid number!");
                System.out.println("Returning to staff menu...\n");
                return;
            }

            switch(choice){
                case 1:
                    System.out.println("Here are the full list of movies.");
                    ArrayList<Movie> movieList = MovieController.showMovieList();
                    System.out.print("Enter the number corresponding to the movie here: ");
                    int movienum = 0;
                    try{
                        movienum = Integer.parseInt(input.nextLine());
                        while(movienum<=0 || movienum>movieList.size()){
                            System.out.println("Please input a valid option!");
                            movienum = Integer.parseInt(input.nextLine());
                        }
                        
                    } catch(NumberFormatException e){
                        System.out.println("PLease input a valid number!");
                        System.out.println("Returning to user menu...\n");
                        break;
                    }
                    String movieSearch = movieList.get(movienum-1).getMovieTitle();
                    MovieController.showMovieDetail(movieSearch);
                    break;
                case 2:
                    showLayoutOnly();
                    break;
                case 3:
                    usercreateBooking(sessionUser);
                    break;
                case 4:
                    MovieTicketController.displayBookings(sessionUser);
                    break;
                case 5:
                    ReviewController.writeReview(sessionUser);
                    break;
                case 6:
                    MovieController.sortMovie();
                    break;
                case 7:
                    System.out.println("Logging out as user...");
                    System.out.println("Returning to main menu...\n");
                    break;
                default:
                    System.out.println("Wrong choice. Try again!");
                    break;
            }
        }
    }
    /**
     * Returns the current {@code User} instance
     * <p>
     * Scans the email against the database of user accounts, and then create
     * a new {@code User} instance with the correct entry and attributes of the {@code User}
     * @param useremail Email of movie-goer passed from the login page
     * @return {@code User} object with the corresponding email address and all other attributes
     * @throws Exception
     */
    private static User fetchDetails(String useremail) throws Exception{
        FileInOut<User> userio = new FileInOut<User>();
        ArrayList<User> userList = userio.readData(new User());
        for(int i=0; i<userList.size(); i++)
            if(useremail.equals(userList.get(i).getEmail()))
                return userList.get(i);

        // shouldn't happen, but just for compilation
        System.out.println("In User.java, no user found");
        return null;
    }

    /**
     * Shows the seating layout of the movie showtime.
     * <p>
     * Contains a User Interface that asks user input for the movie, cineplex and
     * timing to nail down the exact MovieScreening object. This MovieScreening
     * object will contain an array of seats, which will be shown to the user in
     * this method.
     * @throws Exception
     * @see MovieScreening
     */
    public static void showLayoutOnly() throws Exception {
        Scanner input = new Scanner(System.in);

        // read cineplex data
        FileInOut<Cineplex> cineplexio = new FileInOut<Cineplex>();
        ArrayList<Cineplex> cineplexList = cineplexio.readData(new Cineplex());
        System.out.println("To display available seats, we would like to know which movie showtime are you looking at.");
        System.out.println("Which cineplex are you interested in?");
        int cineplexcount = 0;
        for(int i=0; i<cineplexList.size(); i++){
            System.out.println(++cineplexcount + ". " + cineplexList.get(i).getCineplexName());
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
        Cineplex cineplexchosen = cineplexList.get(cineplexnum-1);

        // show list of movies
        FileInOut<Movie> movieio = new FileInOut<Movie>();
        ArrayList<Movie> allMovieList = movieio.readData(new Movie());
        ArrayList<Movie> movieList = MovieController.getAvailableMovieList(allMovieList);
        if(movieList.isEmpty()){
            System.out.println("Sorry, there are no movies available right now. Please come back again later!");
            System.out.println("Returning to user menu...\n");
            return;
        }
        System.out.println("Here are the list of movies to choose from: ");
        int moviecount = 0;
        for(int i=0; i<movieList.size(); i++){
            System.out.println(++moviecount + ". " + movieList.get(i).getMovieTitle());
        }
        System.out.print("Enter the number corresponding to the movie you are interested in: ");
        int movienum;
        try{
            movienum = Integer.parseInt(input.nextLine());
        } catch(NumberFormatException e){
            System.out.println("Please input a valid number!");
            System.out.println("Returning to staff menu...\n");
            return;
        }
        Movie movieObjChosen = movieList.get(movienum-1);
        String movie = movieObjChosen.getMovieTitle();

        // show all available screentimes
        ArrayList<MovieScreening> screeningList = MovieScreeningController.giveScreenTimes(movie, cineplexchosen);
        if(screeningList.isEmpty()){
            System.out.println("Sorry, no showtime is available for this movie at the cineplex you have chosen.");
            System.out.println("Returning to user menu...\n");
            return;
        }
        System.out.println("Here are the list of showtimes for the movie: ");
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
        int screeningnum;
        try{
            screeningnum = Integer.parseInt(input.nextLine());
        } catch(NumberFormatException e){
            System.out.println("Please input a valid number!");
            System.out.println("Returning to staff menu...\n");
            return;
        }
        MovieScreening screeningchosen = screeningList.get(screeningnum-1);

        System.out.println("Here is the cinema layout for the showtime you selected");
        // Display layout of cinema
        screeningchosen.displayLayout();

        System.out.print("Type anything to return to menu:");
        input.nextLine();
        System.out.println("Returning to user menu...\n");
    }
    
    /**
     * Purchases a ticket and make a seat booking for a cinema showtime.
     * <p>
     * Contains a User Interface that asks user input for the movie, cineplex and
     * timing to nail down the exact MovieScreening object. This MovieScreening
     * object will contain an array of seats, which the user can choose and secure
     * the seat for themselves.
     * <p>
     * After obtaining all of the attributes above, calculate the pricing of the
     * ticket using the {@code calcPrice()} method. If successful, show the user
     * the ticket they purchased, and add this ticket to past bookings of the user.
     * @param sessionUser Current movie-goer as a User object
     * @see MovieScreening#calcPrice(User)
     * @throws Exception
     */
    public static void usercreateBooking(User sessionUser) throws Exception {
        Scanner input = new Scanner(System.in);

        // read list of cineplexes
        FileInOut<Cineplex> cineplexio = new FileInOut<Cineplex>();
        ArrayList<Cineplex> cineplexList = cineplexio.readData(new Cineplex());
        System.out.println("Which cineplex would you like to go to?");
        int cineplexcount = 0;
        for(int i=0; i<cineplexList.size(); i++){
            System.out.println(++cineplexcount + ". " + cineplexList.get(i).getCineplexName());
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
        Cineplex cineplexchosen = cineplexList.get(cineplexnum-1);

        // show all movies
        FileInOut<Movie> movieio = new FileInOut<Movie>();
        ArrayList<Movie> allMovieList = movieio.readData(new Movie());
        ArrayList<Movie> movieList = MovieController.getAvailableMovieList(allMovieList);
        if(movieList.isEmpty()){
            System.out.println("Sorry, there are no movies available right now. Please come back again later!");
            System.out.println("Returning to user menu...\n");
            return;
        }
        System.out.println("Here are the list of movies to choose from: ");

        ArrayList<Integer> myindexArray = new ArrayList<Integer>();
        int moviecount = 0;
        for(int i=0; i<movieList.size(); i++){
            if(movieList.get(i).getMovieStatus().equals("Preview") || movieList.get(i).getMovieStatus().equals("Now_Showing")){
                System.out.println(++moviecount + ". " + movieList.get(i).getMovieTitle());
                myindexArray.add(i);
            }
            
        }
        System.out.print("Enter the number corresponding to the movie you would like to watch: ");
        int movienum;
        try{
            movienum = Integer.parseInt(input.nextLine());
        } catch(NumberFormatException e){
            System.out.println("Please input a valid number!");
            System.out.println("Returning to staff menu...\n");
            return;
        }
        Movie movieObjChosen = movieList.get(myindexArray.get(movienum-1));
        String movie = movieObjChosen.getMovieTitle();
        // display list of showtimes
        ArrayList<MovieScreening> screeningList = MovieScreeningController.giveScreenTimes(movie, cineplexchosen);
        if(screeningList.isEmpty()){
            System.out.println("Sorry, no showtime is available for this movie at the cineplex you have chosen.");
            System.out.println("Returning to user menu...\n");
            return;
        }
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
            System.out.print("Location: " + screeningList.get(i).getMovieScreeningLocation().getCineplexName() + " "+screeningList.get(i).getMovieScreeningLocation().getCinemaName());
            System.out.print("\n\n");
        }
        System.out.print("Pick a showtime. Enter the number here: ");
        int screeningnum;
        try{
            screeningnum = Integer.parseInt(input.nextLine());
        } catch(NumberFormatException e){
            System.out.println("Please input a valid number!");
            System.out.println("Returning to staff menu...\n");
            return;
        }
        MovieScreening screeningchosen = screeningList.get(screeningnum-1);


        // Display layout of cinema
        System.out.println("Here is the cinema layout for the showtime you selected");
        screeningchosen.displayLayout();
        System.out.print("Please pick a vacant seat: ");
        int seatId;
        try{
            seatId = Integer.parseInt(input.nextLine());
        } catch(NumberFormatException e){
            System.out.println("Please input a valid number!");
            System.out.println("Returning to staff menu...\n");
            return;
        }

        if(!screeningchosen.getAvailabilityOfSeats(seatId)){
            System.out.println("Your seat was taken!");
            System.out.println("Booking cancelled");
            System.out.println("Returning to user menu...\n");
            return;
        }

        // price calculation
        Double computedPrice = screeningchosen.calcPrice(sessionUser);
        System.out.println("Your seat is secured!");
        System.out.println("Ticket price = SGD" + computedPrice + "(Excl of GST)");
        System.out.println("Ticket price = SGD" + String.format("%.2f",computedPrice * 1.07) + "(Incl of GST)");
        System.out.print("Proceed (Y/N) ?");
        String option = input.nextLine();
        LocalDateTime nowDate = null;
        String TID = null;
        if(option.equalsIgnoreCase("Y")){
            System.out.println("Ticket purchase successful!");
            nowDate = LocalDateTime.now();
            TID = screeningchosen.getMovieScreeningLocation().getCinemaCode() + String.format("%04d", nowDate.getYear()) + String.format("%02d", nowDate.getMonthValue()) + String.format("%02d", nowDate.getDayOfMonth())+ String.format("%02d", nowDate.getHour())+ String.format("%02d", nowDate.getMinute());
            System.out.println("======================================");
            System.out.println("Here is your ticket ID (" + TID +")");
            System.out.println("Name: " + sessionUser.getName());
            System.out.println("Email: " + sessionUser.getEmail());
            System.out.println("Mobile Number: " + sessionUser.getMobileNumber());
            System.out.println("======================================");

            // Each payment will have a transaction id (TID). The TID is of the format XXXYYYYMMDDhhmm (Y : year, M : month, D : day, h : hour, m : minutes, XXX : cinema code in letters).
        } else {
            System.out.println("Ticket purchase cancelled.");
            return;
        }

        // register the sale on the movie side
        movieObjChosen.incrementSaleVolume();
        //after movie object incremented we will bubble up to change the classes that use the object
        MovieScreeningController.updateMovieScreeningWithMovie(movieObjChosen);
        FileInOut<MovieScreening> screeningio = new FileInOut<MovieScreening>();
        ArrayList<MovieScreening> allScreeningList = screeningio.readData(new MovieScreening());
        for (int i = 0; i < allScreeningList.size(); i++) {
            if((allScreeningList.get(i).getMovieObj().getMovieTitle().equalsIgnoreCase(screeningchosen.getMovieObj().getMovieTitle())) && (allScreeningList.get(i).getMovieScreeningLocation().getCinemaName().equalsIgnoreCase(screeningchosen.getMovieScreeningLocation().getCinemaName())) && (allScreeningList.get(i).getMydate().equals(screeningchosen.getMydate()))){
                allScreeningList.get(i).setSeatOccupied(seatId);
                break;
            }
        }
        // write into Movie and Movie Screening text documents
        movieio.writeData(movieList, new Movie());
        screeningio.writeData(allScreeningList, new MovieScreening());

        MovieTicketController.createBooking(screeningchosen, seatId, sessionUser, computedPrice,nowDate,TID);


        // send user back to user menu
        System.out.println("Thank you for using our booking services!");
        System.out.println("Returning to user menu...\n");
    }
    
}
