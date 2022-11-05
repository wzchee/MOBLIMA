import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class MOBLIMA {
    public static void main(String[] args) throws Exception {

        // iniitialize everything
        FileInOut<Cineplex> cineplexinout = new FileInOut<Cineplex>();
        ArrayList<Cineplex> myCineplexList = cineplexinout.readData(new Cineplex());
        //ArrayList<Cineplex> myCineplexList = fileio.readCineplexData();
        if (myCineplexList.isEmpty()) {
            myCineplexList.add(new Cineplex("Cathay Cineplex AMK HUB"));
            myCineplexList.add(new Cineplex("Cathay Cineplex JEM"));
            myCineplexList.add(new Cineplex("Cathay Cineplex Cineleisure"));
            // overwrite the file
            cineplexinout.writeData(myCineplexList, new Cineplex());
            //fileio.writeCineplexData(myCineplexList);
        }
        // FileInOut<Cineplex> cineplexinout = new FileInOut<Cineplex>();
        // ArrayList<Cineplex> myCineplexList = cineplexinout.readData(new Cineplex());
        // cineplexinout.writeData(myCineplexList, new Cineplex());

        FileInOut<Cinema> cinemainout = new FileInOut<Cinema>();
        ArrayList<Cinema> myCinemaList = cinemainout.readData(new Cinema());
        //ArrayList<Cinema> myCinemaList = fileio.readCinemaData();
        if (myCinemaList.isEmpty()) {
            myCinemaList.add(new Cinema("Standard 1", myCineplexList.get(0), false, 100));
            myCinemaList.add(new Cinema("Standard 2", myCineplexList.get(0), false, 100));
            myCinemaList.add(new Cinema("Standard 3", myCineplexList.get(0), false, 100));
            myCinemaList.add(new Cinema("Platinum Movie Suites", myCineplexList.get(1), true, 100));
            myCinemaList.add(new Cinema("Standard 1", myCineplexList.get(1), false, 100));
            myCinemaList.add(new Cinema("Standard 2", myCineplexList.get(1), false, 100));
            myCinemaList.add(new Cinema("Standard 1" ,myCineplexList.get(2), false, 100));
            myCinemaList.add(new Cinema("Standard 2" ,myCineplexList.get(2), false, 100));
            myCinemaList.add(new Cinema("Standard 3" ,myCineplexList.get(2), false, 100));
            // overwrite the file
            cinemainout.writeData(myCinemaList, new Cinema());
            //fileio.writeCinemaData(myCinemaList);
        }

        FileInOut<User> userinout = new FileInOut<User>();
        ArrayList<User> userList = userinout.readData(new User());
        //ArrayList<User> userList = fileio.readUserData();
        if (userList.isEmpty()) {
            userList.add(new User("wz@email.com", "wz", 20, "Wen Zhan", "81234567"));
            userList.add(new User("oliver@email.com", "oliver", 20, "Oliver Low", "81234568"));
            userList.add(new User("bernard@email.com", "bernard", 20, "Bernard", "81234569"));
            userList.add(new User("jiarong@email.com", "jiarong", 20, "Jia Rong", "812345675"));
            // overwrite the file
            userinout.writeData(userList, new User());
            //fileio.writeUserData(userList);
        }

        FileInOut<Staff> staffinout = new FileInOut<Staff>();
        ArrayList<Staff> staffList = staffinout.readData(new Staff());
        //ArrayList<Staff> staffList = fileio.readStaffData();
        if (staffList.isEmpty()) {
            staffList.add(new Staff("admin@admin.com", "admin", "Wen Zhan", myCineplexList.get(0)));
            // overwrite the file
            staffinout.writeData(staffList, new Staff());
            //fileio.writeUserData(userList);
        }
        


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
            
            choice = input.nextInt();
            
            String email, password;
            switch (choice) {
                case 1:
                case 2:
                    // Either user or staff is logging in
                    // Similar code
                    if(choice == 2) System.out.println("\nWelcome STAFF!");
                    else System.out.println("\nWelcome USER!");

                    System.out.print("Enter your email address: ");
                    email = input.next();
                    System.out.print("Enter your password: ");
                    password = input.next();
                    
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
                    email = input.next();

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
                    password = input.next();

                    // name has no wrong format, no validation needed
                    System.out.print("What is your name? ");
                    String name = input.next();

                    // catch if user inputs something other than an Integer
                    int age = 0;
                    System.out.print("What is your age? ");
                    try{
                        age = input.nextInt();
                    } catch(InputMismatchException e){
                        System.out.println("Your input is not a valid number!");
                        System.out.println("Account creation failed");
                        System.out.println("Returning to main menu...\n");
                        break;
                    }
                    
                    // catch if user inputs something other than an Integer
                    String mobileNumber = null;
                    System.out.println("What is your mobile number?");
                    try{
                        mobileNumber = input.next();
                        int numericcheck = java.lang.Integer.parseInt(mobileNumber);
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
                    //fileio.writeUserData(userList);
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