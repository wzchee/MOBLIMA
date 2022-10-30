import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.*;
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
        switch(choice){
            case 1:
                System.out.println("\nWelcome STAFF!");
                System.out.print("Enter your email address: ");
                email = input.next();
                System.out.print("Enter your password: ");
                password = input.next();

                // the following code will use the CharBuffer object, which simplifies comparison between String objects
                InputStreamReader staffin = new FileReader("staff.txt"); //allows reading from the file staff.txt
                CharBuffer stafftxt = CharBuffer.allocate(1000); //CharBuffer for reading from staff.txt
                CharBuffer inputbuf = CharBuffer.allocate(1000); //User input converted into CharBuffer
                ArrayList<String> stafflist = new ArrayList<String>(); //to store staff data
                staffin.read(stafftxt);
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
}
