package models;
import java.util.ArrayList;
import java.io.Serializable;
import java.time.LocalDateTime;
import database.Developer;


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