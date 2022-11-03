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
                    createMovie();
                    break;
                case 2:
                    String status = updateMovie();
                    if (status == "End_Of_Showing"){
                        removeMovie();
                    }
                    break;
                case 3:
                    removeMovie();
                    break;
                case 4:
                    createMovieScreening();
            
                    break;

                case 5:
                    break;
                case 6:
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

    //createmovie method
    public static void createMovie()throws Exception{
        Movie newMovie = new Movie();
        Scanner in = new Scanner(System.in);
        System.out.println("Movie title: ");
        newMovie.setMovieTitle(in.next());
        System.out.println("Movie Status");
        System.out.println("1. Coming Soon");
        System.out.println("2. Preview");
        System.out.println("3. Now Showing");
        System.out.println("4. End of Showing");
        int statusChoice = in.nextInt();
        switch (statusChoice){
            case 1:
            newMovie.setMovieStatus(status.valueOf("Coming_Soon"));
            break;
            case 2:
            newMovie.setMovieStatus(status.valueOf("Preview"));
            break;
            case 3:
            newMovie.setMovieStatus(status.valueOf("Now_Showing"));
            break;
            case 4:
            newMovie.setMovieStatus(status.valueOf("End_Of_Showing"));
            break;
            
        }
        System.out.println("BlockBuster?");
        System.out.println("1. True");
        System.out.println("2. False");
        int blockbusterChoice = in.nextInt();
        if (blockbusterChoice == 1){
            newMovie.setBlockbuster(true);
        }else{
            newMovie.setBlockbuster(false);
        }
        System.out.println("MovieDimension: ");
        System.out.println("1. 2D");
        System.out.println("2. 3D");
        int dimChoice = in.nextInt();

        if (dimChoice == 1){
            newMovie.setMovieDims(dimension.valueOf("TwoD"));
        }else{
            newMovie.setMovieDims(dimension.valueOf("ThreeD"));
        }
        System.out.println("Movie Sypnosis: ");
        newMovie.setMovieSypnosis(in.next());
        System.out.println("Director: ");
        newMovie.setMovieDirector(in.next());
        System.out.println("Sale Volume: ");
        newMovie.setSaleVolume(in.nextInt());
        
        ArrayList<Movie> movieList = null;
        movieList = fileio.readMovieData();
        movieList.add(newMovie);
        fileio.writeMovieData(movieList);

    }

    public static String updateMovie() throws Exception{
        ArrayList<Movie> movieList = null;
        movieList = fileio.readMovieData();
        Movie movieToUpdate = null;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter title of movie to be updated: ");
        String movieName = in.next();
        int found = 0;
        for (int i = 0; i < movieList.size(); i++) {
            if(movieList.get(i).getMovieTitle().equals(movieName)){
                movieToUpdate = movieList.get(i);
                found = 1;
                break;
            }
        }
        if (found == 0){
            System.out.println("No such movie exists");
        }
        else{
            System.out.println("Current movie status: "+ movieToUpdate.getMovieStatus());
            String currentMovieStatus = movieToUpdate.getMovieStatus();
            switch (currentMovieStatus){
                case "Coming_Soon":
                movieToUpdate.setMovieStatus(status.Preview);
                break;
                case "Preview":
                movieToUpdate.setMovieStatus((status.Now_Showing));
                break;
                case "Now_Showing":
                movieToUpdate.setMovieStatus(status.End_Of_Showing);
            }
        }
        fileio.writeMovieData(movieList);
        updateMovieScreeningWithMovie(movieToUpdate);


        return movieToUpdate.getMovieStatus();
    }

    public static void removeMovie() throws Exception{
        ArrayList<Movie> movieList = null;
        movieList = fileio.readMovieData();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter title of movie to be deleted: ");
        String movieName = in.next();
        int found = 0;
        for (int i = 0; i < movieList.size(); i++) {
            if(movieList.get(i).getMovieTitle().equals(movieName)){
                removeMovieScreeningWithMovie(movieName);
                movieList.remove(i);
                found = 1;
                break;
            }
        }
        if (found == 0){
            System.out.println("No such movie exists");
        }


        fileio.writeMovieData(movieList);
    }

    public static void updateMovieScreeningWithMovie(Movie movieToBeChanged) throws Exception{
        ArrayList<MovieScreening> listOfMovieScreening = null;
        listOfMovieScreening = fileio.readMovieScreeningData();
        for(int i=0;i<listOfMovieScreening.size();i++){
            if(listOfMovieScreening.get(i).getMovieObj().getMovieTitle().equals(movieToBeChanged.getMovieTitle())){
                listOfMovieScreening.get(i).setMovieObj(movieToBeChanged);
            }
        }
        fileio.writeMovieScreeningData(listOfMovieScreening);
    } 

    public static void removeMovieScreeningWithMovie(String movieTitleToRemove) throws Exception{
        ArrayList<MovieScreening> listOfMovieScreening = null;
        listOfMovieScreening = fileio.readMovieScreeningData();
        for(int i=0;i<listOfMovieScreening.size();i++){
            if(listOfMovieScreening.get(i).getMovieObj().getMovieTitle().equals(movieTitleToRemove)){
                listOfMovieScreening.remove(i);
            }
        }
        fileio.writeMovieScreeningData(listOfMovieScreening);
    } 

    public static void createMovieScreening() throws Exception{
        Scanner input = new Scanner(System.in);
        ArrayList<MovieScreening> myMovieScreeningList = null;
        myMovieScreeningList = fileio.readMovieScreeningData();
        MovieScreening movieScreeningToAdd = null;


        // We will take in movie title and use it as a keyID to fetchDetail that spits out Movie Object
        System.out.print("Please enter your Movie Title: ");
        String movieTitleToFetch = input.next();
        Movie movieToFetch = null;
        ArrayList<Movie> myMovieList = fileio.readMovieData();
        for(int i=0;i<myMovieList.size();i++){
            if(myMovieList.get(i).getMovieTitle().equals(movieTitleToFetch)){
                movieToFetch = myMovieList.get(i);
                break;
            }
        }


        System.out.print("Please enter your Cinema Name: ");
        String cinemaNameToFetch = input.next();
        Cinema cinemaToFetch = null;
        ArrayList<Cinema> myCinemaList = fileio.readCinemaData();
        for(int i=0;i<myCinemaList.size();i++){
            if(myCinemaList.get(i).getCinemaName().equals(cinemaNameToFetch)){
                cinemaToFetch = myCinemaList.get(i);
                break;
            }
        }
        




        // We will ask for date time in this format and call toString to get string representation 
        // and next time with the string we can call ParseDateTime to reverse the string back to an actual LocalDateTime object
        System.out.println("Please Enter Date and Time  [YYYY,MM,DD,HH,MIN]");
        String date = input.next();
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

        System.out.print("Is it a public holiday: [y/n]");
        String isPublicHolidayInput = input.next();
        boolean isPublicHoliday = true;
        //ask staff if it is public holiday
        if(isPublicHolidayInput=="n"){
            isPublicHoliday = false;
        }

        movieScreeningToAdd = new MovieScreening(movieToFetch, cinemaToFetch, myDate, myArr, isPublicHoliday,0,false);
        myMovieScreeningList.add(movieScreeningToAdd);
        fileio.writeMovieScreeningData(myMovieScreeningList);
    }

    
    public static MovieScreening movieScreeningToChange(ArrayList<MovieScreening> listOfMovieScreenings){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Movie Title");
        String movieTitle = input.next();
        System.out.println("Enter Cinema Name");
        String cinemaTitle = input.next();
        System.out.println("Enter Movie Screening Time ");


        System.out.println("Please Enter Date and Time  [YYYY,MM,DD,HH,MIN]");
        String date = input.next();
        String[] arrOfString = date.split(",");
        int year = Integer.parseInt(arrOfString[0]);
        int month = Integer.parseInt(arrOfString[1]);
        int day = Integer.parseInt(arrOfString[2]);
        int hour = Integer.parseInt(arrOfString[3]);
        int minute = Integer.parseInt(arrOfString[4]);
        LocalDateTime myDate = LocalDateTime.of(year, month, day, hour, minute, 0);

        MovieScreening toChange = null;
        MovieScreening traverser = null;
        
        for(int i=0;i<listOfMovieScreenings.size();i++){
            traverser = listOfMovieScreenings.get(i);
            if(traverser.getMovieObj().getMovieTitle().equals(movieTitle) && traverser.getMydate().equals(myDate) && traverser.getMovieScreeningLocation().getCinemaName().equals(cinemaTitle)){
                toChange = traverser;
                break;
            }
        }

        return toChange;



    }

    public static void removeMovieScreening() throws Exception{
        System.out.println("Remove MovieScreening: ");
        ArrayList<MovieScreening> myMovieScreeningList = null;
        myMovieScreeningList = fileio.readMovieScreeningData();
        MovieScreening toBeRemove = movieScreeningToChange(myMovieScreeningList);
        toBeRemove.setHasCompleted(true);
        fileio.writeMovieScreeningData(myMovieScreeningList);
        
    }
    
    public static void updateMovieScreening() throws Exception{
        Scanner input = new Scanner(System.in);
        ArrayList<MovieScreening> listOfMovieScreenings = fileio.readMovieScreeningData();
        MovieScreening toBeChanged = movieScreeningToChange(listOfMovieScreenings);
        System.out.println("Please Enter Date and Time  [YYYY,MM,DD,HH,MIN]");
        String date = input.next();
        String[] arrOfString = date.split(",");
        int year = Integer.parseInt(arrOfString[0]);
        int month = Integer.parseInt(arrOfString[1]);
        int day = Integer.parseInt(arrOfString[2]);
        int hour = Integer.parseInt(arrOfString[3]);
        int minute = Integer.parseInt(arrOfString[4]);
        LocalDateTime myDate = LocalDateTime.of(year, month, day, hour, minute, 0);

        toBeChanged.setMydate(myDate);
        fileio.writeMovieScreeningData(listOfMovieScreenings);
        
    }


}
