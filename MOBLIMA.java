import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.*;
import java.nio.BufferUnderflowException;
import java.nio.CharBuffer;

public class MOBLIMA {

    public static void main(String[] args) {
        System.out.println("Welcome to MOBLIMA! Please login to use our services.");
        System.out.println("Are you logging in as USER or STAFF?");
        System.out.println("1. User");
        System.out.println("2. Staff");
        System.out.println("3. Create a new account");
        System.out.print("Please enter your choice here: ");
        
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        
        String email, password;
        CharBuffer inputbuf = CharBuffer.allocate(1000); //User input converted into CharBuffer
        switch(choice){
            case 1:
                System.out.println("\nWelcome STAFF!");
                System.out.print("Enter your email address: ");
                email = input.next();
                System.out.print("Enter your password: ");
                password = input.next();

                // the following code will use the CharBuffer object, which simplifies comparison between String objects
                InputStreamReader staffin = new FileReader("staff.txt"); //allows reading from the file staff.txt
                CharBuffer rawstafftxt = CharBuffer.allocate(100000); //CharBuffer for reading from staff.txt
                CharBuffer stafftxtcomp = CharBuffer.allocate(1000); //substring of rawstafftxt used for comparison
                ArrayList<String> stafflist = new ArrayList<String>(); //to store staff data
                int buffersize = staffin.read(rawstafftxt); // read the file into the CharBuffer, return size of buffer

                // based on the formatting of staff.txt, email is the first entry followed by password, separated by commas
                // each new line in staff.txt indicates a different user
                // hence, will continuously fetch whole lines of code from '\n'+1 to the second ','-1
                // and store into stafftxtcomp
                // and use it to compare against inputbuf
                // until a user match is found (send charAt into interface) OR
                // until end of document is reached

                while(rawstafftxt.position() < buffersize){ //while haven't reached end of buffer
                    stafftxtcomp.clear(); //clear the comparison buffer from previous iteration

                    /*
                     * Methodology:
                     * 1. Read the email of the current entry
                     * 2. Compare against email inputted by user
                     * 3. If passed, move on the password comparison
                     * 4. If failed, move to next user entry
                     * 5. Repeat above steps until reached end of file
                     */

                    // at the end of do-while loop, stafftxtcomp should contain email of current user entry
                    char c = rawstafftxt.get();
                    do{
                        stafftxtcomp.append(c); //append individual characters into comparison buffer
                        c = rawstafftxt.get();
                    }while(c != ','); //until reached the end of the email element

                    // convert user inputted email into CharBuffer
                    inputbuf.put(email);

                    if(stafftxtcomp.compareTo(inputbuf) == 0){
                        // there is an email match
                        // move on the password screening section

                        // convert user inputted password into CharBuffer
                        inputbuf.clear();
                        inputbuf.put(password);
                        if(passwordMatch(rawstafftxt, inputbuf)){
                            // send charAt into the interface
                            // NOT DONE YET
                            Staff.main();
                        } else {
                            System.out.println("Wrong password!");
                        }

                        // at the end, break out of the current loop
                        // as there won't be anymore duplicate email
                        break;
                    }

                }

                if(email.equals("admin") && password.equals("admin")){
                    Staff.main(["admin"]);
                }
                break;
            case 2:
                System.out.println("\nWelcome USER!");
                System.out.print("Enter your email address: ");
                email = input.next();
                System.out.print("Enter your password: ");
                password = input.next();
                
                InputStreamReader userin = new FileReader("user.txt");
                ArrayList<String> userlist = new ArrayList<String>(); //to store user data
                break;
            case 3:
                
                break;
            default:
        }
        
    }

    /*
    private boolean bufferLimitReached(CharBuffer buffer){
        /*
         * Check if CharBuffer reached the end
         * Return true if yes, false otherwise
         * 
         * Method: CharBuffer.get() will reach the current character
         * Then increment the position of the buffer
         * If, on reading the character, get() detects that the position of the buffer is beyond the limit
        
        try{
            char c = buffer.get();
        }catch(BufferUnderflowException e){
            // buffer went beyond the limit
            return true;
        }
        // buffer is still within limit
        return false;
    }*/

    private static boolean passwordMatch(CharBuffer txtbuffer, CharBuffer inputbuf){
        // read the password of the current entry
        // compare against password stated
        // if passed, enter user interface
        // if failed, tell user to reenter password
        // in both scenarios need to break out of while loop

        CharBuffer stafftxtcomp = CharBuffer.allocate(1000); //substring of rawstafftxt used for comparison

        // at the end of do-while loop, stafftxtcomp should contain password of current user entry
        char c = txtbuffer.get();
        do{
            stafftxtcomp.append(c); //append individual characters into comparison buffer
            c = txtbuffer.get();
        }while(c != ','); //until reached the end of the password element

        if(stafftxtcomp.compareTo(inputbuf) == 0){
            return true;
        } else return false;
    }
}
