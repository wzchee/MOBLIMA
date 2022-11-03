import java.util.Scanner;

import javax.swing.plaf.synth.SynthFormattedTextFieldUI;

import java.io.*;
import java.util.ArrayList;

public class MOBLIMA {
    public static void main(String[] args) throws Exception {

        // iniitialize everything
        ArrayList<Cineplex> myCineplexList = fileio.readCineplexData();
        if (myCineplexList.isEmpty()) {
            myCineplexList.add(new Cineplex("Golden Village"));
            myCineplexList.add(new Cineplex("Shaw Theatres"));
            myCineplexList.add(new Cineplex("Cathay Cineplexes"));
            fileio.writeCineplexData(myCineplexList);
        }

        ArrayList<Cinema> myCinemaList = fileio.readCinemaData();
        if (myCinemaList.isEmpty()) {
            myCinemaList.add(new Cinema("GV Bishan", "Golden Village", false, 100));
            myCinemaList.add(new Cinema("Gold Class Grand, Great World City", "Golden Village", true, 100));
            myCinemaList.add(new Cinema("GV Funan", "Golden Village", false, 100));
            myCinemaList.add(new Cinema("Shaw Theatres Premiere", "Shaw Theatres", true, 100));
            myCinemaList.add(new Cinema("Shaw Theatres Jcube", "Shaw Theatres", false, 100));
            myCinemaList.add(new Cinema("Shaw Theatres Seletar", "Shaw Theatres", false, 100));
            myCinemaList.add(new Cinema("Platinum Movie Suites JEM" ,"Cathay Cineplexes", true, 100));
            myCinemaList.add(new Cinema("Cathay Cineplex JEM" ,"Cathay Cineplexes", false, 100));
            myCinemaList.add(new Cinema("Cathay Cineplex Cineleisure" ,"Cathay Cineplexes", false, 100));
            fileio.writeCinemaData(myCinemaList);
            String cinemaName;
            boolean platinumSuites;
            for (int i = 0; i < 3; i++){
                int start = i*3;
                for (int j = start; j < start+3; j++){
                    cinemaName = myCinemaList.get(j).getCinemaName();
                    platinumSuites = myCinemaList.get(j).isPlatinumSuite();
                    myCineplexList.get(i).addCinema(i, cinemaName, platinumSuites);
                }
            }
            fileio.writeCineplexData(myCineplexList);
        }

        ArrayList<User> userList = fileio.readUserData();
        if (userList.isEmpty()) {
            userList.add(new User("wz@email.com", "wz", 20, "Wen Zhan", "81234567"));
            userList.add(new User("oliver@email.com", "oliver", 20, "Oliver Low", "81234568"));
            userList.add(new User("bernard@email.com", "bernard", 20, "Bernard", "81234569"));
            userList.add(new User("jiarong@email.com", "jiarong", 20, "Jia Rong", "812345675"));
            fileio.writeUserData(userList);
        }

        ArrayList<Staff> staffList = fileio.readStaffData();
        if (staffList.isEmpty()) {
            staffList.add(new Staff("admin@admin.com", "admin", "Wen Zhan", myCineplexList.get(0)));
            fileio.writeUserData(userList);
        }

        int choice = 0; // choice of the main menu
        while(choice != 4){
            System.out.println("Welcome to MOBLIMA! Please login to use our services.");
            System.out.println("Are you logging in as USER or STAFF?");
            System.out.println("1. User");
            System.out.println("2. Staff");
            System.out.println("3. Create a new account (only user)");
            System.out.println("4. Quit MOBLIMA");
            System.out.print("Please enter your choice here: ");
            
            Scanner input = new Scanner(System.in);
            choice = input.nextInt();
            
            String email, password;
            switch (choice) {
                case 1:
                case 2:
                    // Either user or staff is logging in
                    // Similar code
                    System.out.println("\nWelcome STAFF!");
                    System.out.print("Enter your email address: ");
                    email = input.next();
                    System.out.print("Enter your password: ");
                    password = input.next();
                    
                    if(choice == 1){
                        for(int i=0; i<userList.size(); i++){
                            // screen email first
                            if(email == userList.get(i).getEmail())
                                // if email screening successful, screen password
                                if(password == userList.get(i).getPassword()){
                                    // if password screening successful, proceed into login UI
                                    User.loggedin(email);
                                    break;
                                }else{
                                    // password screening failed
                                    System.out.println("Wrong password!");
                                    break;
                                }
                        }

                        // if reached this stage, then email screening failed
                        System.out.println("No email record exists.");
                        break;
                    }
                    if(choice == 2){
                        for(int i=0; i<staffList.size(); i++){
                            // screen email first
                            if(email == staffList.get(i).getEmail())
                                // if email screening successful, screen password
                                if(password == staffList.get(i).getPassword()){
                                    // if password screening successful, proceed into login UI
                                    Staff.loggedin(email);
                                    break;
                                }else{
                                    // password screening failed
                                    System.out.println("Wrong password!");
                                    break;
                                }
                        }

                        // if reached this stage, then email screening failed
                        System.out.println("No email record exists.");
                        break;
                    }
                case 3:
                    // a new account wants to be created by user
                    System.out.println("Welcome to MOBLIMA!");
                    System.out.print("Please enter your email: ");
                    email = input.next();

                    // check if existing email already exists
                    for(int i=0; i<userList.size(); i++){
                        if(email == userList.get(i).getEmail()){
                            System.out.println("Email already existed");
                            break;
                        }
                    }

                    // no existing email exists, input remaining fields
                    System.out.print("Please enter your password: ");
                    password = input.next();
                    System.out.print("What is your name? ");
                    String name = input.next();
                    System.out.print("What is your age? ");
                    int age = input.nextInt();
                    System.out.println("What is your mobile number?");
                    String mobileNumber = input.next();

                    // create new User object
                    User newUser = new User(email, password, age, name, mobileNumber);
                    userList.add(newUser);
                    fileio.writeUserData(userList);
                case 4:
                    System.out.println("Thank you for using MOBLIMA!");
                    break;
                default:
                System.out.println("Wrong input. Try again");
                    break;
            }

            input.close();
        }
    }
}