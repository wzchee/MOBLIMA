import java.util.Scanner;
import java.util.ArrayList;


/**
 * Application class containing the main method. 
 * <p>
 * Provides users of MOBLIMA with
 * the option to login as either a movie-goer({@code User}) or staff member({@code Staff}). Also
 * provides the user with the option to create a new movie-goer({@code User}) account
 * @author  Chee Wen Zhan
 * @version 1.0
 * @since   2022-11-7
 * @see     User
 * @see     Staff
 */
public class MOBLIMA {
    
    /**
     * The first interface when user launches MOBLIMA. 
     * <p>
     * Provides users of MOBLIMA with
     * the option to login as either a movie-goer({@code User}) or staff member({@code Staff}). Also
     * provides the user with the option to create a new movie-goer({@code User}) account
     * @param   args
     * @throws  Exception
     */
    public static void main(String[] args) throws Exception {

        /*** Developer tools, comment out to disable it ***/
        //  Developer.clearAllFiles();
         Developer.peekFiles();
        Developer.Initializer(); // DO NOT COMMENT OUT THIS LINE!!

        // Import user list
        FileInOut<User> userinout = new FileInOut<User>();
        ArrayList<User> userList = userinout.readData(new User());

        // Import staff list
        FileInOut<Staff> staffinout = new FileInOut<Staff>();
        ArrayList<Staff> staffList = staffinout.readData(new Staff());

        Scanner input = new Scanner(System.in);
        int choice = 0; // choice of the main menu
        while(choice != 4){
            System.out.println("Welcome to MOBLIMA! Please login to use our services.");
            System.out.println("Are you logging in as USER or STAFF?");
            System.out.println("1. User");
            System.out.println("2. Staff");
            System.out.println("3. Create a new account (only user)");
            System.out.println("4. Quit MOBLIMA");
            System.out.print("Please enter your choice here: ");
            
            try{
                choice = Integer.parseInt(input.nextLine());
            } catch(NumberFormatException e){
                System.out.println("Please input a valid number!");
            }
            
            String email, password;
            switch (choice) {
                case 1:
                case 2:
                    // Either user or staff is logging in
                    // Similar code
                    if(choice == 2) System.out.println("\nWelcome STAFF!");
                    else System.out.println("\nWelcome USER!");

                    System.out.print("Enter your email address: ");
                    email = input.nextLine();
                    System.out.print("Enter your password: ");
                    password = input.nextLine();
                    
                    if(choice == 1){
                        for(int i=0; i<userList.size(); i++){
                            // screen email first
                            if(email.equals(userList.get(i).getEmail()))
                                // if email screening successful, screen password
                                if(password.equals(userList.get(i).getPassword())){
                                    // if password screening successful, proceed into login UI
                                    User.loggedin(email);
                                    break;
                                }else{
                                    // password screening failed
                                    System.out.println("Wrong password!");
                                    System.out.println("Returning to main menu...\n");
                                    break;
                                }
                            
                            if(i == userList.size()-1){
                                // if reached this stage, then email screening failed
                                System.out.println("No email record exists.");
                                System.out.println("Returning to main menu...\n");
                                break;
                            }
                        }
                    }
                    if(choice == 2){
                        for(int i=0; i<staffList.size(); i++){
                            // screen email first
                            if(email.equals(staffList.get(i).getEmail()))
                                // if email screening successful, screen password
                                if(password.equals(staffList.get(i).getPassword())){
                                    // if password screening successful, proceed into login UI
                                    Staff.loggedin(email);
                                    break;
                                }else{
                                    // password screening failed
                                    System.out.println("Wrong password!");
                                    System.out.println("Returning to main menu...\n");
                                    break;
                                }
                            
                            if(i == staffList.size()-1){
                                // if reached this stage, then email screening failed
                                System.out.println("No email record exists.");
                                System.out.println("Returning to main menu...\n");
                                break;
                            }
                        }
                    }
                    break;
                case 3:
                    // a new account wants to be created by user
                    System.out.println("Welcome to MOBLIMA!");
                    System.out.print("Please enter your email: ");
                    email = input.nextLine();

                    // check if existing email already exists
                    for(int i=0; i<userList.size(); i++){
                        if(email == userList.get(i).getEmail()){
                            System.out.println("Email already existed");
                            System.out.println("Returning to main menu...\n");
                            break;
                        }
                    }

                    // no existing email exists, input remaining fields
                    // password has no wrong format, no validation needed
                    System.out.print("Please enter your password: ");
                    password = input.nextLine();

                    // name has no wrong format, no validation needed
                    System.out.print("What is your name? ");
                    String name = input.nextLine();

                    // catch if user inputs something other than an Integer
                    int age = 0;
                    System.out.print("What is your age? ");
                    try{
                        age = Integer.parseInt(input.nextLine());
                    } catch(NumberFormatException e){
                        System.out.println("Your input is not a valid number!");
                        System.out.println("Account creation failed");
                        System.out.println("Returning to main menu...\n");
                        return;
                    }
                    
                    // catch if user inputs something other than an Integer
                    String mobileNumber = null;
                    System.out.println("What is your mobile number?");
                    try{
                        mobileNumber = input.nextLine();
                        Integer.parseInt(mobileNumber);
                    } catch(NumberFormatException e){
                        System.out.println("Your input is not a valid mobile number!");
                        System.out.println("Account creation failed");
                        System.out.println("Returning to main menu...\n");
                        break;
                    }


                    // create new User object
                    User newUser = new User(email, password, age, name, mobileNumber);
                    userList.add(newUser);
                    userinout.writeData(userList, new User());
                    System.out.println("User account added successfully!");
                    System.out.println("Please login again using your new account. \n");
                    break;
                case 4:
                    System.out.println("Thank you for using MOBLIMA!");
                    break;
                default:
                    System.out.println("Wrong input. Try again");
                    break;
            }
        }

        // out of application class, close scanner
        input.close();
    }
}