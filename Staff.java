import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.lang.module.FindException;


public class Staff implements Serializable{

    public static void loggedin(String useremail) throws Exception{
        // User interface after a STAFF has logged in

        // Firstly, fetch details from staff.txt for use in later functions
        Staff sessionUser = fetchDetails(useremail);

        // Staff main menu
        int choice = 0;
        while(choice != 10){
            System.out.println("Welcome STAFF " + sessionUser.name + " !");
            System.out.println("What would you like to do today?");
            System.out.println("1. Create a movie listing");
            System.out.println("2. Update a movie listing");
            System.out.println("3. Remove a movie listing");
            System.out.println("4. Create a cinema showtime for one movie");
            System.out.println("5. Update a cinema showtime for one movie");
            System.out.println("6. Remove a cinema showtime for one movie");
            System.out.println("7. Configure system settings");
            System.out.println("8. List Top 5 movies by ticket sales");
            System.out.println("9. List Top 5 movies by overall reviewer's rating");
            System.out.println("10. Logout");
            System.out.print("Please enter your choice here: ");

            Scanner input = new Scanner(System.in);
            choice = input.nextInt();

            switch(choice){
                case 1:
                    Movie.createMovie();
                    break;
                case 2:
                    String status = Movie.updateMovie();
                    if (status == "End_Of_Showing"){
                        Movie.removeMovie();
                    }
                    break;
                case 3:
                    Movie.removeMovie();
                    break;
                case 4:
                    MovieScreening.createMovieScreening();
                    break;

                case 5:
                    // MovieScreening.updateMovieScreening();
                    break;
                case 6:
                    MovieScreening.removeMovieScreening();
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    System.out.println("Logging out as staff...");
                    System.out.println("Returning to main page...");
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
        ArrayList<Staff> staffList = fileio.readStaffData();
        for(int i=0; i<staffList.size(); i++)
            if(useremail == staffList.get(i).getEmail())
                return staffList.get(i);

        // shouldn't happen, but just for compilation
        System.out.println("In Staff.java, no Staff found");
        return null;
    }




}
