import java.util.Scanner;
import java.io.*;
import java.nio.CharBuffer;
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
                    System.out.print("Please enter your Movie Title: ");
                    String movieTitleToFetch = input.next();
                    Movie toFetchMovie = Movie.fetchDetails(movieTitleToFetch);
                    String movieTitleToConcat = toFetchMovie.getMovieTitle();
                    
                    System.out.print("Please enter your Cinema Name: ");
                    String cinemaNameToFetch = input.next();
                    Cinema toFetchCinema = Cinema.fetchDetails(cinemaNameToFetch);
                    String cinemaNameToConcat = toFetchCinema.getCinemaName();


                    // should be we list out the available timeslots by going through MovieScreening.txt and then they get 
                    // to choose and we fetchDetails for the movieInstance
          //======================================================================
                                           // *TEMPORARY
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
                   
            //======================================================================

                    MovieScreening chosenMovieScreening = MovieScreening.fetchDetails(keyIdOfMovieScreening);
                    chosenMovieScreening.displayLayout();
                    System.out.println("Please choose the seat number from the list of available seats");
                    String userSeatChoice = input.next();
                    while(!chosenMovieScreening.getAvailabilityOfSeats(Integer.parseInt(userSeatChoice))){
                        System.out.println("Please choose the seat number from the list of available seats");
                        userSeatChoice = input.next();
                    }
                    chosenMovieScreening.createBooking(sessionUser,Integer.parseInt(userSeatChoice));



                    break;
                case 4:
                    break;
                case 5:
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
        
    }
}
