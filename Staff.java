import java.util.Scanner;
import java.io.*;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.lang.module.FindException;


public class Staff implements Serializable{

    public static void loggedin(String useremail) throws FileNotFoundException, IOException{
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
            CharBuffer inputbuf = CharBuffer.allocate(1000); //User input converted into CharBuffer
            CharBuffer rawtxt = CharBuffer.allocate(100000); //CharBuffer for reading from .txt file
            int buffersize = 0; //size of CharBuffer that was read into


            switch(choice){
                case 1:

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    // We will take in movie title and use it as a keyID to fetchDetail that spits out Movie Object
                    System.out.print("Please enter your Movie Title: ");
                    String movieTitleToFetch = input.next();
                    Movie toFetchMovie = Movie.fetchDetails(movieTitleToFetch);
                    String movieTitleToConcat = toFetchMovie.getMovieTitle();


                    // We will take in cinema name and use it as a keyID to fetchDetail that spits out Movie Object

                    System.out.print("Please enter your Cinema Name: ");
                    String cinemaNameToFetch = input.next();
                    Cinema toFetchCinema = Cinema.fetchDetails(cinemaNameToFetch);
                    String cinemaNameToConcat = toFetchCinema.getCinemaName();

                    // We will ask for date time in this format and call toString to get string representation 
                    // and next time with the string we can call ParseDateTime to reverse the string back to an actual LocalDateTime object
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

                    // We will concat all these 3 information because these 3 combined will give a unique movie screening
                    String keyIdOfMovieScreening = movieTitleToConcat.concat(cinemaNameToConcat).concat(dateTimeToConcat);


                    
                    // check if existing email already exists
                    // read from user.txt
                    InputStreamReader userin = new FileReader(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\moblima\\movieScreening.txt");
                    buffersize = userin.read(rawtxt); // read the file into the CharBuffer, return size of buffer
                    rawtxt.rewind(); // return cursor to start of buffer
                    
                    // recursively check and match keyID of moviescreening
                    while(rawtxt.position() < buffersize){
                        // convert user inputted email into CharBuffer
                        inputbuf.clear();
                        inputbuf.put(keyIdOfMovieScreening);
                        
                        // compare the KeyID for moviescreening and obtain the match results
                        BufferMatchReturn result = MOBLIMA.charBufferMatch(rawtxt, inputbuf);
                        rawtxt = result.getBuffer();
                        if(result.getMatch()){
                            System.out.println("MovieScreening already exists!");
                            return;
                        } else {
                            // move cursor until start of next user entry
                            char c;
                            do{
                                c = rawtxt.get();
                            }while(c != '\n');
                        }
                    }   // if reached this point, that means no existing keyid exists
                    
                    // write to user.txt
                    // second argument to start writing from end instead of beginning

                    //create the int[] for seatArr where 0 means vacant and 1 means occupied

                    String seatArr="";
                    for(int i= 0 ;i<100;i++){
                        if(i==0){
                            seatArr += "[";
                            seatArr += "0";
                        }else{
                            seatArr += ",";
                            seatArr += "0";
                        }
                    }
                    seatArr += "]";

                    OutputStreamWriter userout = new FileWriter(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\moblima\\movieScreening.txt", true);
                    System.out.print("Is it a public holiday: [y/n]");
                    String isPublicHoliday = input.next();
                    //ask staff if it is public holiday
                    if(isPublicHoliday=="n"){
                        userout.write(keyIdOfMovieScreening + "," + movieTitleToConcat + "," + cinemaNameToConcat+ "," + dateTimeToConcat+ "," + seatArr + ","+ "false" + "," + 0 + ",\n");

                    }else{
                        userout.write(keyIdOfMovieScreening + "," + movieTitleToConcat + "," + cinemaNameToConcat+ "," + dateTimeToConcat+ "," + seatArr + ","+ "true" + "," + 0 + ",\n");
                    }
                    
                    userout.close();
                    System.out.println("Movie Screening successfully created!");
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

}
