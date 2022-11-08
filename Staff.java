import java.util.Scanner;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Java class representing a staff member in MOBLIMA
 * @author  Chee Wen Zhan
 * @version 1.0
 * @since   2022-7-11
 * @see     Movie
 * @see     MovieScreening
 * @see     Configurables
 */
public class Staff implements Serializable{

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

    /**
     * Constructor to create a {@code Staff} object with all of its attributes initialized
     * @param email         Email of staff member used to log into this system
     * @param password      Password of staff member used to log into this system
     * @param name          Name of staff member
     * @param workplace     The cineplex associated to the staff member. He is still able to access all staff features of MOBLIMA
     */
    public Staff(String email, String password, String name, Cineplex workplace){
        this.email = email;
        this.password = password;
        this.name = name;
        this.workplace = workplace;
    }

    public Staff(){

    }

    /**
     * Email of staff member used to log into this system
     */
    private String email;
    /**
     * Retrieve the email of the staff member
     * @return Email of staff member
     */
    public String getEmail(){
        return email;
    }
    /**
     * Set the new email of this staff member
     * @param email New email of staff member
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Password of staff member used to log into the system
     */
    private String password;
    /**
     * Retrieve the password of the staff member
     * @return Password of staff member
     */
    public String getPassword(){
        return password;
    }
    /**
     * Set the new password of the staff member
     * @param password New password of staff member
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Name of staff member
     */
    private String name;
    /**
     * Retrieve the name of the staff member
     * @return Name of staff member
     */
    public String getName(){
        return name;
    }
    /**
     * Set the new name of the staff member
     * @param name New name of staff member
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Cineplex where the staff member goes to work.
     * However, this does not restrict the staff member from all of the functionalities
     * in this system.
     */
    private Cineplex workplace;
    /**
     * Retrieve the cineplex of work of this staff member
     * @return Cineplex of work of this staff member
     */
    public Cineplex getWorkplace(){
        return workplace;
    }
    /**
     * Set the new cineplex of work for this staff member
     * @param workplace New cineplex of work for this staff member
     */
    public void setWorkplace(Cineplex workplace){
        this.workplace = workplace;
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