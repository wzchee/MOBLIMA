import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * Java class representing a movie-goer in MOBLIMA
 * @author  Chee Wen Zhan
 * @version 1.0
 * @since   2022-7-11
 * @see     Movie
 * @see     MovieScreening
 * @see     MovieTicket
 * @see     Review
 */
public class User implements Serializable{
   
    /**
     * User Interface after a movie-goer logged into MOBLIMA
     * <p>
     * Provides them with the option of seeing movie details, check seat
     * availability and make a booking. Also allows them to write reviews
     * and check their booking history.
     * @param   useremail   Email of the user logged into the system
     * @throws  Exception
     */
    public static void loggedin(String useremail) throws Exception{
        // User interface after a USER has logged in

        // Firstly, fetch details from user.txt for use in later functions
        User sessionUser = fetchDetails(useremail);

        // User main menu
        Scanner input = new Scanner(System.in);
        int choice = 0;
        while(choice != 6){
            System.out.println("\nWelcome USER " + sessionUser.name + " !");
            System.out.println("What would you like to do today?");
            System.out.println("1. Search for movie and view movie details");
            System.out.println("2. Check seat availability");
            System.out.println("3. Make a booking");
            System.out.println("4. View booking history");
            System.out.println("5. Review a movie");
            System.out.println("6. Logout");
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
                    ArrayList<Movie> movieList = Movie.showMovieList();
                    System.out.print("Enter the number corresponding to the movie here: ");
                    int movienum;
                    try{
                        movienum = Integer.parseInt(input.nextLine());
                    } catch(NumberFormatException e){
                        System.out.println("PLease input a valid number!");
                        System.out.println("Returning to user menu...\n");
                        break;
                    }
                    String movieSearch = movieList.get(movienum-1).getMovieTitle();
                    Movie.showMovieDetail(movieSearch);
                    break;
                case 2:
                    showLayoutOnly();
                    break;
                case 3:
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
                    System.out.println("Returning to main menu...\n");
                    break;
                default:
                    System.out.println("Wrong choice. Try again!");
                    break;
            }
        }
    }

    /**
     * Constructor to create a User instance with all of the attributes instantiated
     * @param email         Email address of movie-goer, uniquely identifies the user
     * @param password      Password used by movie-goer to log into the system
     * @param age           Age of movie-goer, used to calculate ticket pricing
     * @param name          Name of movie-goer, displayed in the movie ticket
     * @param mobileNumber  Mobile number of movie-goer, displayed in the movie ticket
     */
    public User(String email, String password, int age, String name, String mobileNumber){
        // constructor
        this.email = email;
        this.password = password;
        this.age = age;
        this.name = name;
        this.mobileNumber = mobileNumber;
    }

    public User(){
        
    }

    /**
     * Email address of the movie-goer
     */
    private String email;
    /**
     * Retrieve the email address of the movie-goer (current User)
     * @return  Email address of the movie-goer
     */
    public String getEmail(){
        return email;
    }
    /**
     * Set the new email address of the movie-goer (current User)
     * @param email New email address to replace the current email attribute
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Password of movie-goer used to log into the system
     */
    private String password;
    /**
     * Retrieve the password of the movie-goer (current User)
     * @return  Password of the movie-goer
     */
    public String getPassword(){
        return password;
    }
    /**
     * Set the new password of the movie-goer (current User)
     * @param password New password of movie-goer used for this system
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Age of the movie-goer
     */
    private int age;
    /**
     * Retrieve the age of the movie-goer (current User)
     * @return  Age of the movie-goer
     */
    public int getAge(){
        return age;
    }
    /**
     * Set the new age of the movie-goer (current User)
     * @param age New age of movie-goer
     */
    public void setAge(int age){
        this.age = age;
    }

    /**
     * Name of movie-goer
     */
    private String name;
    /**
     * Retrieve the name of the movie-goer (current User)
     * @return Name of movie-goer
     */
    public String getName(){
        return name;
    }
    /**
     * Set the new name of the movie-goer (current User)
     * @param name New name of movie-goer
     */
    public void setName(String name){this.name = name;}

    /**
     * Mobile number of movie-goer
     */
    private String mobileNumber;
    /**
     * Retrieve the mobile number of the movie-goer (current User)
     * @return Mobile number of movie-goer
     */
    public String getMobileNumber(){
        return mobileNumber;
    }
    /**
     * Set the new mobile number of the movie-goer (current User)
     * @param mobileNumber New mobile number of movie-goer
     */
    public void setMobileNumber(String mobileNumber){this.mobileNumber = mobileNumber;}

    /**
     * Returns the current User instance
     * <p>
     * Scans the email against the database of user accounts, and then create
     * a new User instance with the correct entry and attributes of the User
     * @param useremail Email of movie-goer passed from the login page
     * @return User object with the corresponding email address and all other attributes
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
        ArrayList<Movie> movieList = Movie.getAvailableMovieList(allMovieList);
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
        ArrayList<MovieScreening> screeningList = MovieScreening.giveScreenTimes(movie, cineplexchosen);
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
        ArrayList<Movie> movieList = Movie.getAvailableMovieList(allMovieList);
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
        ArrayList<MovieScreening> screeningList = MovieScreening.giveScreenTimes(movie, cineplexchosen);
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
        MovieScreening.updateMovieScreeningWithMovie(movieObjChosen);

        // identify the seat taken and set seat to occupied
        // String movieTitleOfScreeningToChange = null;
        // LocalDateTime dateTimeOfScreeningToChange = null;
        // String cineplexNameScreeningToChange = null;
        // MovieScreening traverser = null;

        // for(int i = 0 ;i<screeningList.size();i++){
        //     traverser =screeningList.get(i);
        //     movieTitleOfScreeningToChange = traverser.getMovieObj().getMovieTitle();
        //     dateTimeOfScreeningToChange = traverser.getMydate();
        //     cineplexNameScreeningToChange = traverser.getMovieScreeningLocation().getCineplexName();

        //     if(movieTitleOfScreeningToChange.equalsIgnoreCase(movie) && dateTimeOfScreeningToChange.equals(screeningchosen.getMydate()) && cineplexNameScreeningToChange.equalsIgnoreCase(cineplexchosen.getCineplexName())){
        //         screeningchosen = traverser;
        //         break;
        //     }
        // }
        screeningchosen.setSeatOccupied(seatId);

        // write into Movie and Movie Screening text documents
        movieio.writeData(movieList, new Movie());
        FileInOut<MovieScreening> screeningio = new FileInOut<MovieScreening>();
        screeningio.writeData(screeningList, new MovieScreening());

        MovieTicket.createBooking(screeningchosen, seatId, sessionUser, computedPrice,nowDate,TID);


        // send user back to user menu
        System.out.println("Thank you for using our booking services!");
        System.out.println("Returning to user menu...\n");
    }


}



    // public static void userCreateReview(User sessionUser){
    //     Scanner in = new Scanner(System.in);
        
    //     ArrayList<MovieTicket> movieTicketList = fileio.readMovieTicketData();
    //     ArrayList<MovieTicket> movieTicketOfUser = new ArrayList<MovieTicket>();
    //     for(int i =0;i<movieTicketList.size()){
    //         if(movieTicketList.get(i).getUser().equals(sessionUser) && movieTicketList.get(i).getMovieScreening().hasCompleted()){
    //             movieTicketOfUser.add(movieTicketList.get(i));
    //         }
    //     }

    //     List<String> movieNames = new ArrayList<String>();
    //     String movieNameTraverser = null;
    //     for(int i =0;i<movieTicketOfUser.size();i++){
    //         movieNameTraverser = movieTicketOfUser.get(i).getMovieScreening().getMovieObj().getMovieTitle();
    //         if(!movieNames.contains(movieNameTraverser)){
    //             movieNames.add(movieNameTraverser);
    //         }
            
    //     }

    //     ArrayList<Review> arrOfReviews = new ArrayList<Review>();

    //     // display the list of movies and whether the user reviewed them or not
    //     if(movieNames.isEmpty()){
    //         System.out.println("Based on our records, you have not watched any reviewable movies");
    //         System.out.println("Returning to user menu...\n");
    //         return;
    //     }else{
    //         for(int i=0;i<movieNames.size();i++){
    //             System.out.println((i+1) + movieNames.get(i));
    //         }
    //         System.out.println("Please enter a choice of movie to review");
    //         String choice = null;
    //         choice = in.nextLine();

            

    //         System.out.print("Please give a rating out of 10: ");
    //         int movieRating = input.nextInt();

    //         System.out.println("Please type in your review in full below:");
    //         String movieReview = input.nextLine();

    //         ArrayList<Review> reviewList = fileio.readReviewData();
    //         Review reviewObjToAdd = new Review(movieRating,movieReview, sessionUser);
    //         reviewList.add(null);
    //         fileio.writeReviewData(reviewList);
            
    //         Movie movieToChange = null;
    //         ArrayList<Movie> movieList = fileio.readMovieData();
    //         for(int i = 0;i<movieList.size();i++){
    //             if(movieList.get(i).equals(movieNames.get(Integer.parseInt(choice)-1))){
    //                 movieToChange = movieList.get(i);
    //             }
    //         }

    //         movieToChange.addReview(reviewObjToAdd);
    //         MovieScreening.updateMovieScreeningWithMovie(movieToChange);
    //         fileio.writeMovieData(movieList);

    //     }