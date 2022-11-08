package controller;
import java.util.Scanner;

import FileInOut;

import java.util.ArrayList;
import java.io.Serializable;
import database.*;
import models.*;

public class StaffController{
    /**
     * User interface after a staff member logged into MOBLIMA
     * <p>
     * Provides staff member with the option to create, update and remove
     * movies from the system. Also allows staff members to create, update
     * and remove a movie showtime from the system. Note that 'remove' does
     * not delete the entry from the system, but instead makes the user unable
     * to see the movie entry or movie showtime entry when searched for it
     * <p>
     * Staff members can also configure the base price of the movie and 
     * declare a certain day as public holiday, which will affect the pricing.
     * Staff members can also display the Top 5 movies in the system based on
     * rating or sale volume.
     * @param   useremail Email of the staff logged into the system
     * @see     Movie
     * @see     MovieScreening
     * @see     Configurables
     * @throws  Exception
     */
    public static void loggedin(String useremail) throws Exception{
        // User interface after a STAFF has logged in

        // Firstly, fetch details from staff.txt for use in later functions
        Staff sessionUser = fetchDetails(useremail);

        // Staff main menu
        Scanner input = new Scanner(System.in);
        int choice = 0;
        while(choice != 9){
            System.out.println("\nWelcome STAFF " + sessionUser.getName() + " !");
            System.out.println("What would you like to do today?");
            System.out.println("1. Create a movie listing");
            System.out.println("2. Update a movie listing");
            System.out.println("3. Remove a movie listing");
            System.out.println("4. Create a cinema showtime for one movie");
            System.out.println("5. Update a cinema showtime for one movie");
            System.out.println("6. Remove a cinema showtime for one movie");
            System.out.println("7. Configure system settings");
            System.out.println("8. List Top 5 movies");
            System.out.println("9. Logout");
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
                    MovieController.createMovie();
                    break;
                case 2:
                    MovieController.updateMovie();
                    break;
                case 3:
                    System.out.println("Here are the list of movies available on the system.");
                    ArrayList<Movie> movieList = MovieController.showMovieList();
                    System.out.print("Enter the number of the movie to be deleted: ");
                    int movienum;
                    try{
                        movienum = Integer.parseInt(input.nextLine());
                    } catch(NumberFormatException e){
                        System.out.println("Please input a valid number!");
                        System.out.println("Returning to staff menu...\n");
                        break;
                    }
                    String movieTitle = movieList.get(movienum-1).getMovieTitle();
                    MovieController.removeMovie(movieTitle);
                    break;
                case 4:
                    MovieScreeningController.createMovieScreening();
                    break;
                case 5:
                    MovieScreeningController.updateMovieScreening();
                    break;
                case 6:
                    MovieScreeningController.removeMovieScreening();
                    break;
                case 7:
                    ConfigurablesController.configure();;
                    break;
                case 8:
                    MovieController.sortMovie();
                    break;
                case 9:
                    System.out.println("Logging out as staff...");
                    System.out.println("Returning to main menu...\n");
                    break;
                default:
                    System.out.println("Wrong choice. Try again!");
                    break;
            }
        }
    }
    /**
     * Returns the current {@code Staff} instance.
     * <p>
     * Scans the email against the database of staff accounts, and then
     * create a new entry for this {@code Staff} user with all of the staff 
     * attributes inside the instance
     * @param useremail Email of staff member used to log into the system
     * @return {@code Staff} object with the corresponding email address
     * @throws Exception
     */
    private static Staff fetchDetails(String useremail) throws Exception{
        FileInOut<Staff> staffio = new FileInOut<Staff>();
        ArrayList<Staff> staffList = staffio.readData(new Staff());
        for(int i=0; i<staffList.size(); i++)
            if(useremail.equals(staffList.get(i).getEmail()))
                return staffList.get(i);

        // shouldn't happen, but just for compilation
        System.out.println("No record found!");
        return null;
    }
    
}
