import java.util.Scanner;
import java.io.*;
import java.nio.CharBuffer;

public class User {
    /*
     * Associated storage document: user.txt in the same directory
     * Formatting using string concatenation notation:
     * email + "," + password + "," + age + "," + name + "," + mobilenumber + "\n"
     * 
     * NOTE! the code will only work if
     * 1. user.txt exists in the same directory
     * 2. There is at least one entry in user.txt
     * 3. The document MUST end with a '\n'
     * 4. The first element of the entry must be email
     * 5. The second element of the entry must be password
     */
    
    public static void loggedin(String useremail) throws FileNotFoundException, IOException{
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

    private String password; //don't think a get and set applies here

    private int age;
    public int getAge(){return age;}
    public void setAge(int age){this.age = age;}

    private String name;
    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    private String mobileNumber;
    public String getMobileNumber(){return mobileNumber;}
    public void setMobileNumber(String mobileNumber){this.mobileNumber = mobileNumber;}

    private static User fetchDetails(String useremail) throws FileNotFoundException, IOException{
        // read from user.txt
        InputStreamReader userin = new FileReader(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\moblima\\user.txt");
        CharBuffer rawtxt = CharBuffer.allocate(10000);
        int buffersize = userin.read(rawtxt); // read the file into the CharBuffer, return size of buffer
        userin.close();
        rawtxt.rewind();

        // search for the matching email record
        CharBuffer inputbuf = CharBuffer.allocate(1000);
        String strpassword = null; //initialize attribute variables other than email
        int intage = 0;
        String strname = null;
        String strmobilenumber = null;
        CharBuffer txtpassword = CharBuffer.allocate(1000); //initialize corresponding CharBuffer attribute
        CharBuffer txtage = CharBuffer.allocate(3);
        CharBuffer txtname = CharBuffer.allocate(1000);
        CharBuffer txtmobilenumber = CharBuffer.allocate(20);
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

                // reading age
                c = rawtxt.get();
                do{
                    txtage.append(c); //append individual characters into comparison buffer
                    c = rawtxt.get();
                }while(c != ','); //until reached the end of the age element
                c = rawtxt.get(); //move to the next attribute

                // reading name
                c = rawtxt.get();
                do{
                    txtname.append(c); //append individual characters into comparison buffer
                    c = rawtxt.get();
                }while(c != ','); //until reached the end of the name element
                c = rawtxt.get(); //move to the next attribute

                // reading mobilenumber
                c = rawtxt.get();
                do{
                    txtmobilenumber.append(c); //append individual characters into comparison buffer
                    c = rawtxt.get();
                }while(c != ','); //until reached the end of the mobilenumber element
                c = rawtxt.get(); //move to the next attribute

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
        String strage = txtage.toString();
        intage = Integer.valueOf(strage);
        strname = txtname.toString();
        strmobilenumber = txtmobilenumber.toString();

        return new User(useremail, strpassword, intage, strname, strmobilenumber);
    }
}
