import java.util.Scanner;
import java.io.*;
import java.nio.CharBuffer;

public class Staff {
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

    public static void loggedin(String useremail){
        // User interface after a STAFF has logged in

        // Firstly, fetch details from staff.txt for use in later functions
        Staff sessionUser = fetchDetails(useremail);

        // Staff main menu
        int choice = 0;
        while(choice != 10){
            System.out.println("Welcome STAFF " + this.name + " !");
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
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
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

    private String password; //don't think a get and set applies here

    private String name;
    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    private Cineplex workplace;
    public Cineplex getWorkplace(){return workplace;}
    public void setWorkplace(Cineplex workplace){this.workplace = workplace;}

    private static Staff fetchDetails(String useremail) throws FileNotFoundException, IOException{
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

        return new Staff(useremail, strpassword, strname, null);
    }
}
