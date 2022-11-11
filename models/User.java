package models;
import java.io.Serializable;

/**
 * Java class representing a movie-goer in MOBLIMA
 * @author  Chee Wen Zhan
 * @version 1.0
 * @since   2022-7-11
 */
public class User implements Serializable{

    /**
     * Constructor to create a {@code User} instance with all of the attributes instantiated
     * @param email         Email address of movie-goer, uniquely identifies the user
     * @param password      Password used by movie-goer to log into the system
     * @param age           Age of movie-goer, used to calculate ticket pricing
     * @param name          Name of movie-goer, displayed in the movie ticket
     * @param mobileNumber  Mobile number of movie-goer, displayed in the movie ticket
     */
    public User(String email, String password, int age, String name, String mobileNumber){
        // constructor
        this.email = email;
        this.password = password;
        this.age = age;
        this.name = name;
        this.mobileNumber = mobileNumber;
    }

    public User(){
        
    }

    /**
     * Email address of the movie-goer
     */
    private String email;
    /**
     * Retrieve the email address of the movie-goer (current {@code User})
     * @return  Email address of the movie-goer
     */
    public String getEmail(){
        return email;
    }
    /**
     * Set the new email address of the movie-goer (current {@code User})
     * @param email New email address to replace the current email attribute
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Password of movie-goer used to log into the system
     */
    private String password;
    /**
     * Retrieve the password of the movie-goer (current {@code User})
     * @return  Password of the movie-goer
     */
    public String getPassword(){
        return password;
    }
    /**
     * Set the new password of the movie-goer (current {@code User})
     * @param password New password of movie-goer used for this system
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Age of the movie-goer
     */
    private int age;
    /**
     * Retrieve the age of the movie-goer (current {@code User})
     * @return  Age of the movie-goer
     */
    public int getAge(){
        return age;
    }
    /**
     * Set the new age of the movie-goer (current {@code User})
     * @param age New age of movie-goer
     */
    public void setAge(int age){
        this.age = age;
    }

    /**
     * Name of movie-goer
     */
    private String name;
    /**
     * Retrieve the name of the movie-goer (current {@code User})
     * @return Name of movie-goer
     */
    public String getName(){
        return name;
    }
    /**
     * Set the new name of the movie-goer (current {@code User})
     * @param name New name of movie-goer
     */
    public void setName(String name){this.name = name;}

    /**
     * Mobile number of movie-goer
     */
    private String mobileNumber;
    /**
     * Retrieve the mobile number of the movie-goer (current {@code User})
     * @return Mobile number of movie-goer
     */
    public String getMobileNumber(){
        return mobileNumber;
    }
    /**
     * Set the new mobile number of the movie-goer (current {@code User})
     * @param mobileNumber New mobile number of movie-goer
     */
    public void setMobileNumber(String mobileNumber){this.mobileNumber = mobileNumber;}
}