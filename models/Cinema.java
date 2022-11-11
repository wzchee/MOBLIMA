package models;
import java.io.Serializable;

import database.FileInOut;

/**
 * Java class that contains basic information on what a cinema holds. Cinema Object
 * @author  Bernard Chiang
 * @version 1.0
 * @since   2022-11-8
 */

public class Cinema implements Serializable{
    /**
     * Name of <code>Cinema</code> Object
     */
    private String cinemaName;
    /**
     * Name of <code>Cineplex</code> that the <code>Cinema</code> Object is housed under
     */
    private Cineplex cineplex;
    /**
     * Whether the <code>Cinema</code> Object is Platinum Suites or not
     * @see Cineplex.java
     */
    private boolean platinumSuites;
    /**
     * The unique code to the <code>Cinema</code> Object
     */
    private String cinemaCode;
    /**
     * Total number of seats the <code>Cinema</code> Objects houses
     */
    private int numOfSeats;

    /**
     * Default <code>Cinema</code> Constructor
     * @param cinemaName - Name of <code>Cinema</code> Object
     * @param cineplex - Name of Cineplex that <code>Cinema</code> Object is housed under
     * @param platinumSuites - Determines the class of <code>Cinema</code> Object to be Platinum Suite or not
     * @param numOfSeats - Total number of seats in <code>Cinema</code> Object
     * @param cinemaCode - Unique code to identify <code>Cinema</code> Object
     */
    public Cinema(String cinemaName, Cineplex cineplex, boolean platinumSuites, int numOfSeats, String cinemaCode) {
        this.cinemaName = cinemaName;
        this.cineplex = cineplex;
        this.platinumSuites = platinumSuites;
        this.numOfSeats = numOfSeats;
        this.cinemaCode = cinemaCode;
    }

    /**
     * Empty <code>Cinema</code> Constructor used for reading and writing files
     * @see FileInOut.java
     */
    public Cinema() {
    }

    /**
     * Getter method to return <code>Cinema</code> name
     * @return <code>Cinema</code> name
     */
    public String getCinemaName() {
        return this.cinemaName;
    }

    /**
     * Setter method to set <code>Cinema</code> name
     * @param cinemaName - Name of <code>Cinema</code> Object
     */
    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    /**
     * Getter method to return <code>Cineplex</code> name that the <code>Cinema</code> Object is housed under
     * @return <code>Cineplex</code> name
     */
    public String getCineplexName() {
        return this.cineplex.getCineplexName();
    }

    /**
     * Getter method to return boolean of whether <code>Cinema</code> Object is Platinum Suite
     * @return <code>platinumSuites</code> 
     */
    public boolean isPlatinumSuite() {
        return this.platinumSuites;
    }

    /**
     * Setter method to set the Platinum Suites of <code>Cinema</code> Object to true or false
     * @param isPlatinumSuites - Whether the <code>Cinema</code> Object is Platinum Suites or not
     */
    public void setPlatinumSuite(boolean isPlatinumSuites) {
        this.platinumSuites = isPlatinumSuites;
    }

    /**
     * Getter method to return the total number of seats housed by <code>Cinema</code> Object
     * @return <code>numOfSeats</code> 
     */
    public int getNumOfSeats(){
        return this.numOfSeats;
    }

    /**
     * Setter method to set the total number of seats <code>Cinema</code> Object can house
     * @param numOfSeats -Total number of seats the <code>Cinema</code> Objects houses
     */
    public void setNumOfSeats(int numOfSeats){
        this.numOfSeats = numOfSeats;
    }

    /**
     * Getter method to return the code of <code>Cinema</code> Objcet
     * @return <code>cinemaCode</code> 
     */
    public String getCinemaCode() {
        return this.cinemaCode;
    }

    /**
     * Setter method to set the code of <code>Cinema</code> Object
     * @param cinemaCode -The unique code to the <code>Cinema</code> Object
     */
    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }

}