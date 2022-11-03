import java.util.Scanner;
import java.io.*;
import java.nio.CharBuffer;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class updatedstaff {
    /*
     * Associated storage document: staff.txt in the same directory
     * Formatting using string concatenation notation:
     * email + "," + password + "," + name + "," + workplace_string + "\n"
     * 
     * NOTE! the code will only work if
     * 1. staff.txt exists in the same directory
     * 2. There is at least one entry in staff.txt
     * 3. The document MUST end with a '\n'
     * 4. The first element of the entry must be email
     * 5. The second element of the entry must be password
     */

    public static void loggedin(String useremail) throws FileNotFoundException, IOException, Exception{
        // User interface after a STAFF has logged in

        // Firstly, fetch details from staff.txt for use in later functions
        updatedstaff sessionUser = fetchDetails(useremail);

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
            CharBuffer inputbuf = CharBuffer.allocate(1000); //User input converted into CharBuffer
            CharBuffer rawtxt = CharBuffer.allocate(100000); //CharBuffer for reading from .txt file
            int buffersize = 0; //size of CharBuffer that was read into


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

    


    public updatedstaff(String email, String password, String name, Cineplex workplace){
        this.email = email;
        this.password = password;
        this.name = name;
        this.workplace = workplace;
    }

    private String email;
    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}

    private String password; //don't think a get and set applies here

    private String name;
    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    private Cineplex workplace;
    public Cineplex getWorkplace(){return workplace;}
    public void setWorkplace(Cineplex workplace){this.workplace = workplace;}

    private static updatedstaff fetchDetails(String useremail) throws FileNotFoundException, IOException{
        // read from staff.txt
        InputStreamReader staffin = new FileReader(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\moblima\\staff.txt");
        CharBuffer rawtxt = CharBuffer.allocate(10000);
        int buffersize = staffin.read(rawtxt); // read the file into the CharBuffer, return size of buffer
        staffin.close();
        rawtxt.rewind();

        // search for the matching email record
        CharBuffer inputbuf = CharBuffer.allocate(1000);
        String strpassword = null; //initialize attribute variables other than email
        String strname = null;
        CharBuffer txtpassword = CharBuffer.allocate(1000); //initialize corresponding CharBuffer attribute
        CharBuffer txtname = CharBuffer.allocate(1000);
        while(rawtxt.position() < buffersize){
            // convert user inputted email into CharBuffer for comparison
            inputbuf.clear();
            inputbuf.put(useremail);

            // compare the emails and obtain the match results
            BufferMatchReturn result = MOBLIMA.charBufferMatch(rawtxt, inputbuf);
            rawtxt = result.getBuffer();
            if(result.getMatch()){
                // email matched. read ALL corresponding records

                // reading password
                char c = rawtxt.get();
                do{
                    txtpassword.append(c); //append individual characters into comparison buffer
                    c = rawtxt.get();
                }while(c != ','); //until reached the end of the password element
                c = rawtxt.get(); //move to the next attribute

                // reading name
                c = rawtxt.get();
                do{
                    txtname.append(c); //append individual characters into comparison buffer
                    c = rawtxt.get();
                }while(c != ','); //until reached the end of the name element
                c = rawtxt.get(); //move to the next attribute

                // reading workplace
                CharBuffer txtworkplace = CharBuffer.allocate(1000);
                /*
                 * do while
                 * switch(txtworkplace)
                 */

                break;
            } else {
                // move cursor until start of next user entry
                char c;
                do{
                    c = rawtxt.get();
                }while(c != '\n');
            }
        }
        strpassword = txtpassword.toString();
        strname = txtname.toString();

        return new updatedstaff(useremail, strpassword, strname, null);
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

    public static void createMovieScreening(){
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
        System.out.println("Enter Movie Title");
        String movieTitle = input.next();
        System.out.println("Enter Cinema Name"));
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
    
    public static void updateMovieScreening(){
        System.out.println("Enter Movie Ti");
    }

    


}
