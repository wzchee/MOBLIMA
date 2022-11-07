package Models;
import java.util.Scanner;

import FileInOut;

import java.util.ArrayList;
import java.io.Serializable;


public class Staff implements Serializable{

    public static void loggedin(String useremail) throws Exception{
        // User interface after a STAFF has logged in

        // Firstly, fetch details from staff.txt for use in later functions
        Staff sessionUser = fetchDetails(useremail);

        // Staff main menu
        Scanner input = new Scanner(System.in);
        int choice = 0;
        while(choice != 9){
            System.out.println("\nWelcome STAFF " + sessionUser.name + " !");
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
                    Movie.createMovie();
                    break;
                case 2:
                    Movie.updateMovie();
                    break;
                case 3:
                    System.out.println("Here are the list of movies available on the system.");
                    ArrayList<Movie> movieList = Movie.showMovieList();
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
                    Movie.removeMovie(movieTitle);
                    break;
                case 4:
                    MovieScreening.createMovieScreening();
                    break;
                case 5:
                    MovieScreening.updateMovieScreening();
                    break;
                case 6:
                    MovieScreening.removeMovieScreening();
                    break;
                case 7:
                    Configurables.configure();;
                    break;
                case 8:
                    Movie.sortMovie();
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

    public Staff(String email, String password, String name, Cineplex workplace){
        this.email = email;
        this.password = password;
        this.name = name;
        this.workplace = workplace;
    }

    public Staff(){

    }

    private String email;
    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}

    private String password;
    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

    private String name;
    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    private Cineplex workplace;
    public Cineplex getWorkplace(){return workplace;}
    public void setWorkplace(Cineplex workplace){this.workplace = workplace;}

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