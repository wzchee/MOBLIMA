package controller;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;

import ..\FileInOut;

import java.util.InputMismatchException;
import models.*;
import database.*;


public class ConfigurablesController {
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
}
