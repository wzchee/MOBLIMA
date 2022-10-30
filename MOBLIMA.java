import java.util.Scanner;
import java.io.*;
import java.nio.CharBuffer;

public class MOBLIMA {

    public static void main(String[] args) throws FileNotFoundException, IOException {
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
        CharBuffer rawtxt = CharBuffer.allocate(100000); //CharBuffer for reading from .txt file
        int buffersize; //size of CharBuffer that was read into
        switch(choice){
            case 1:
                System.out.println("\nWelcome STAFF!");
                System.out.print("Enter your email address: ");
                email = input.next();
                System.out.print("Enter your password: ");
                password = input.next();

                // the following code will use the CharBuffer object, which simplifies comparison between String objects
                InputStreamReader staffin = new FileReader(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\moblima\\staff.txt"); //allows reading from the file staff.txt
                buffersize = staffin.read(rawtxt); // read the file into the CharBuffer, return size of buffer
                rawtxt.rewind(); // return cursor to start of buffer
                
                // based on the formatting of staff.txt, email is the first entry followed by password, separated by commas
                // each new line in staff.txt indicates a different user
                // hence, will continuously fetch whole lines of code from '\n'+1 to the second ','-1
                // and store into rawtxtcomp
                // and use it to compare against inputbuf
                // until a user match is found (send charAt into interface) OR
                // until end of document is reached

                while(rawtxt.position() < buffersize){ //while haven't reached end of buffer
                    /*
                     * Methodology:
                     * 1. Read the email of the current entry
                     * 2. Compare against email inputted by user
                     * 3. If email matching passed, move on the password comparison
                     * 4. If email matching failed, move to next user entry
                     * 5. If password matching passed, record the email and send user into the logged in UI
                     * 6. If password matching failed, terminate as there is no more possible email matches
                     * 7. Repeat above steps until reached end of file
                     */

                    // convert user inputted email into CharBuffer
                    inputbuf.clear();
                    inputbuf.put(email);

                    // compare the emails and obtain the match results
                    BufferMatchReturn result = charBufferMatch(rawtxt, inputbuf);
                    rawtxt = result.getBuffer();
                    if(result.getMatch()){
                        // there is an email match
                        // move on the password screening section

                        // convert user inputted password into CharBuffer
                        inputbuf.clear();
                        inputbuf.put(password);

                        // compare the passwords and obtain the result of the comparison
                        BufferMatchReturn result2 = charBufferMatch(rawtxt, inputbuf);
                        if(result2.getMatch()){
                            // the passwords matched
                            // send charAt into the interface
                            // NOT DONE YET
                            Staff.main(null);
                        } else {
                            // the passwords do not match
                            System.out.println("Wrong password!");
                        }

                        // at the end, break out of the current loop
                        // as there won't be anymore duplicate email
                        break;
                    } else {
                        // move cursor until start of next user entry
                        char c;
                        do{
                            c = rawtxt.get();
                        }while(c != '\n');
                    }

                }

                if(rawtxt.position() == buffersize)
                    System.out.println("No existing email record!");
                break;
            case 2:
                System.out.println("\nWelcome USER!");
                System.out.print("Enter your email address: ");
                email = input.next();
                System.out.print("Enter your password: ");
                password = input.next();
                break;
            case 3:
                
                break;
            default:
        }
        
    }

    private static BufferMatchReturn charBufferMatch(CharBuffer txtbuffer, CharBuffer inputbuf){
        /*
         * Methodology:
         * 1. Read the password of the current entry
         * 2. Compare against password inputted by user
         * 3. If passed, return match = true
         * 4. If failed, return match = false
         * 5. In both cases, also return txtbuffer to record the current position of the buffer
         * 6. BufferMatchReturn object will be used to contain the multiple return values
         * 
         * Apparently compareTo() only compares the buffer from the CURRENT position
         * So there is a need to reset the position to zero before comparing
         * By using clear() on inputbuf
         * But we need to retain the position of txtbuffer, so that one cannot clear()
         */

        CharBuffer rawtxtcomp = CharBuffer.allocate(1000); //substring of rawtxt used for comparison

        // at the end of do-while loop, rawtxtcomp should contain the comparison substring we need
        char c = txtbuffer.get();
        do{
            rawtxtcomp.append(c); //append individual characters into comparison buffer
            c = txtbuffer.get();
        }while(c != ','); //until reached the end of the password element
        
        inputbuf.clear(); //reset position to zero for comparison, if not already done
        rawtxtcomp.clear(); //reset position to zero for comparison
        if(rawtxtcomp.compareTo(inputbuf) == 0){
            BufferMatchReturn ret = new BufferMatchReturn(true, txtbuffer);
            return ret;
        } else {
            BufferMatchReturn ret = new BufferMatchReturn(false, txtbuffer);
            return ret;
        }
    }
}