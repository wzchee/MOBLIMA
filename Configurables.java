import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Configurables{
    // class is specific for attributes that staff can configure

    private ArrayList<LocalDateTime> publicHolidays;
    private double basePrice;

    public static void configure() throws Exception{
        Scanner input = new Scanner(System.in);

        System.out.println("Please choose a setting to configure");
        System.out.println("1. Change base price of movie");
        System.out.println("2. Add a public holiday");
        //remove a public holiday?
        System.out.print("Enter your choice here: ");
        int choice = input.nextInt();

        // configurables arraylist only has one element but this is done to comply with FileInOut.java
        FileInOut<Configurables> configinout = new FileInOut<Configurables>();
        ArrayList<Configurables> configList = configinout.readData(new Configurables());
        Configurables config = configList.get(0);
        //Configurables config = fileio.readConfigurablesData();

        switch(choice){
            case 1:
                System.out.println("The old base price for a movie is $" + config.getBasePrice());
                System.out.print("What would be the new base price? $");
                double price = config.getBasePrice();
                try{
                    price = input.nextDouble();
                } catch(InputMismatchException e){
                    System.out.println("Your input is not a valid number!");
                    System.out.println("Returning to main menu...\n");
                    break;
                }
                
                config.setBasePrice(price);
                configList.set(0, config);
                configinout.writeData(configList, new Configurables());
                //fileio.writeConfigurablesData(config);

                System.out.println("Base price for a movie successfully changed!");
                System.out.println("Returning to main menu...\n");
                break;
            case 2:
                System.out.println("Which date would you like to declare as a public holiday?");
                System.out.println("Please enter the date in the format [YYYY,MM,DD]");
                String date = input.next();
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

                    System.out.println("Declare " + holiday.getDayOfYear() + " "
                                        + holiday.getMonth().toString() + " " + holiday.getYear() + " a holiday?");
                    System.out.print("Input your answer (Y/N): ");
                    correct = input.next();
                }

                // change the public holiday entry
                ArrayList<LocalDateTime> publicHolidayList = config.getPublicHolidays();
                publicHolidayList.add(LocalDateTime.of(year, month, day, 0, 0));
                config.setPublicHolidays(publicHolidayList);

                // overwrite existing file
                configList.set(0, config);
                configinout.writeData(configList, new Configurables());
        }

        // end of application class, closing scanner
        input.close();
    }

    public Configurables(int year, int month, int day, double basePrice){
        publicHolidays = new ArrayList<LocalDateTime>();
        publicHolidays.add(LocalDateTime.of(year, month, day, 0, 0));
        this.basePrice = basePrice;
    }

    public Configurables(){

    }

    public ArrayList<LocalDateTime> getPublicHolidays(){return publicHolidays;}
    public void setPublicHolidays(ArrayList<LocalDateTime> publicHolidays){this.publicHolidays = publicHolidays;}

    public double getBasePrice(){return basePrice;}
    public void setBasePrice(double basePrice){this.basePrice = basePrice;}

    public boolean holidayMatch(LocalDateTime inputDateTime){
        for(int i=0; i<publicHolidays.size(); i++){
            Boolean boolyear = inputDateTime.getYear() == publicHolidays.get(i).getYear();
            Boolean boolmonth = inputDateTime.getMonthValue() == publicHolidays.get(i).getYear();;
            Boolean boolday = inputDateTime.getDayOfMonth() == publicHolidays.get(i).getDayOfMonth();

            if(boolyear && boolmonth && boolday) return true;
        }

        // no matching records
        return false;
    }
}