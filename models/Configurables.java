package models;
import java.util.ArrayList;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Scanner;

import database.Developer;
import database.FileInOut;

import java.util.InputMismatchException;


/**
 * Java class that store system-wide variables not associated to 
 * an instance of any object
 * <p>
 * The system-wide variables are the base price of movie and
 * list of public holidays. Both attributes are used to calculate
 * ticket price, but the values are changed for the entire system 
 * instead of for the individual tickets themselves
 * <p>
 * There should only be one Configurables object throughout the entire system.
 * All ticket issuance will refer to this Configurables object to calculate
 * pricing. This object is initialized inside {@code Developer#Initializer()},
 * with base price set to 7 and Christmas 2022 declared as public holiday.
 * @author  Chee Wen Zhan
 * @version 1.0
 * @since   2022-8-11
 * @see     Developer#Initializer()
 */
public class Configurables implements Serializable{
    // class is specific for attributes that staff can configure

    /**
     * Change the values of system-wide variables not associated 
     * to an instance of any object
     * <p>
     * Contains a User Interface that asks for whether the base price
     * should be changed, or whether a public holiday will be declared
     * by the system
     * <p>
     * The system-wide variables are the base price of movie and
     * list of public holidays. Both attributes are used to calculate
     * ticket price, but the values are changed for the entire system 
     * instead of for the individual tickets themselves
     * <p>
     * As all {@code MovieTicket} objects stored in the system are 
     * already completed transactions, the values changed via this method 
     * will only affect subsequent tickets issued. No option to remove
     * public holidays is provided because this will lead to unfairness
     * in consideration of the tickets already issued using public
     * holiday pricing.
     * @throws Exception
     */
    public static void configure() throws Exception{
        Scanner input = new Scanner(System.in);

        System.out.println("\nPlease choose a setting to configure");
        System.out.println("1. Change base price of movie");
        System.out.println("2. Add a public holiday");
        //remove a public holiday?
        System.out.print("Enter your choice here: ");
        int choice;
        try{
            choice = Integer.parseInt(input.nextLine());
        } catch(NumberFormatException e){
            System.out.println("Please input a valid number!");
            System.out.println("Returning to staff menu...\n");
            return;
        }
        

        // configurables arraylist only has one element but this is done to comply with FileInOut.java
        FileInOut<Configurables> configinout = new FileInOut<Configurables>();
        ArrayList<Configurables> configList = configinout.readData(new Configurables());
        Configurables config = configList.get(0);

        switch(choice){
            case 1:
                System.out.println("\nThe old base price for a movie is $" + config.getBasePrice());
                System.out.print("What would be the new base price? $");
                double price = config.getBasePrice();
                try{
                    price = input.nextDouble();
                    input.nextLine();
                } catch(InputMismatchException e){
                    System.out.println("Your input is not a valid number!");
                    System.out.println("Returning to main menu...\n");
                    break;
                }
                
                config.setBasePrice(price);
                configList.set(0, config);
                configinout.writeData(configList, new Configurables());

                System.out.println("Base price for a movie successfully changed!");
                System.out.println("Returning to main menu...\n");
                break;
            case 2:
                System.out.println("\nWhich date would you like to declare as a public holiday?");
                System.out.println("Please enter the date in the format [YYYY,MM,DD]");
                String date = input.nextLine();
                String[] arrOfString = date.split(",");

                String correct = "N";
                int year = 2022, month = 1, day = 1;
                while(!correct.equals("Y")){
                    // higher chance of wrong input
                    // will allow multiple reattempts for unsatisfactory inputs
                    try{
                        year = Integer.parseInt(arrOfString[0]);
                        month = Integer.parseInt(arrOfString[1]);
                        day = Integer.parseInt(arrOfString[2]);
                    } catch(InputMismatchException e){
                        System.out.println("Your input cannot be read. Either it is invalid, or you did not follow the format given.");
                        System.out.println("Returning to main menu...\n");
                        break;
                    }
                    
                    LocalDateTime holiday = LocalDateTime.of(year, month, day, 0, 0, 0);

                    System.out.println("Declare " + holiday.getDayOfMonth() + " "
                                        + holiday.getMonth().toString() + " " + holiday.getYear() + " a holiday?");
                    System.out.print("Input your answer (Y/N): ");
                    correct = input.nextLine();
                }

                // change the public holiday entry
                ArrayList<LocalDateTime> publicHolidayList = config.getPublicHolidays();
                publicHolidayList.add(LocalDateTime.of(year, month, day, 0, 0));
                config.setPublicHolidays(publicHolidayList);

                // overwrite existing file
                configList.set(0, config);
                configinout.writeData(configList, new Configurables());
        }
    }

    /**
     * Constructor of the Configurables Class.
     * <p>
     * Initializes the base price of a movie ticket, which can later be changed using
     * {@code configure()} method. Also initializes at least one public holiday by
     * taking in input of year, month and day of the public holiday, and create a new
     * {@code LocalDateTime} object with minute = 0 and second = 0
     * @param   year
     * @param   month
     * @param   day
     * @param   basePrice
     * @see     Configurables#configure()
     * @see     LocalDateTime
     */
    public Configurables(int year, int month, int day, double basePrice){
        publicHolidays = new ArrayList<LocalDateTime>();
        publicHolidays.add(LocalDateTime.of(year, month, day, 0, 0));
        this.basePrice = basePrice;
    }

    public Configurables(){

    }

    /**
     * Contains a list of dates to refer to as public holidays as declared in this system
     */
    private ArrayList<LocalDateTime> publicHolidays;
    /**
     * Retrieve the list of public holidays as declared in this system in the format of
     * {@code ArrayList<LocalDateTime>}
     * @return List of public holidays
     * @see ArrayList
     */
    public ArrayList<LocalDateTime> getPublicHolidays(){
        return publicHolidays;
    }
    /**
     * Set the new list of public holidays
     * @param publicHolidays New list of public holidays
     * @see ArrayList
     */
    public void setPublicHolidays(ArrayList<LocalDateTime> publicHolidays){this.publicHolidays = publicHolidays;}

    /**
     * Contains the base price of a movie ticket
     */
    private double basePrice;
    /**
     * Retrieve the base price of a movie ticket as stored in this system
     * @return Base price of a ticket
     */
    public double getBasePrice(){
        return basePrice;
    }
    /**
     * Set the new base price for a movie ticket
     * @param basePrice New base price for a movie ticket
     */
    public void setBasePrice(double basePrice){
        this.basePrice = basePrice;
    }

    /**
     * Checks if the date passed as argument is in fact a public holiday as declared
     * in this system
     * @param inputDateTime {@code LocalDateTime} object, but only the date is used to
     * check for public holiday status
     * @return true if the date is a public holiday, false otherwise
     */
    public boolean holidayMatch(LocalDateTime inputDateTime){
        for(int i=0; i<publicHolidays.size(); i++){
            Boolean boolyear = inputDateTime.getYear() == publicHolidays.get(i).getYear();
            Boolean boolmonth = inputDateTime.getMonthValue() == publicHolidays.get(i).getMonthValue();;
            Boolean boolday = inputDateTime.getDayOfMonth() == publicHolidays.get(i).getDayOfMonth();

            if(boolyear && boolmonth && boolday) return true;
        }

        // no matching records
        return false;
    }
}