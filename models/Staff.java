package models;
import java.io.Serializable;

/**
 * Java class representing a staff member in MOBLIMA
 * @author  Chee Wen Zhan
 * @version 1.0
 * @since   2022-7-11
 * @see     Movie
 * @see     MovieScreening
 * @see     Configurables
 */
public class Staff implements Serializable{

    /**
     * Constructor to create a {@code Staff} object with all of its attributes initialized
     * @param email         Email of staff member used to log into this system
     * @param password      Password of staff member used to log into this system
     * @param name          Name of staff member
     * @param workplace     The cineplex associated to the staff member. He is still able to access all staff features of MOBLIMA
     */
    public Staff(String email, String password, String name, Cineplex workplace){
        this.email = email;
        this.password = password;
        this.name = name;
        this.workplace = workplace;
    }

    public Staff(){

    }

    /**
     * Email of staff member used to log into this system
     */
    private String email;
    /**
     * Retrieve the email of the staff member
     * @return Email of staff member
     */
    public String getEmail(){
        return email;
    }
    /**
     * Set the new email of this staff member
     * @param email New email of staff member
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Password of staff member used to log into the system
     */
    private String password;
    /**
     * Retrieve the password of the staff member
     * @return Password of staff member
     */
    public String getPassword(){
        return password;
    }
    /**
     * Set the new password of the staff member
     * @param password New password of staff member
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Name of staff member
     */
    private String name;
    /**
     * Retrieve the name of the staff member
     * @return Name of staff member
     */
    public String getName(){
        return name;
    }
    /**
     * Set the new name of the staff member
     * @param name New name of staff member
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Cineplex where the staff member goes to work.
     * However, this does not restrict the staff member from all of the functionalities
     * in this system.
     */
    private Cineplex workplace;
    /**
     * Retrieve the cineplex of work of this staff member
     * @return Cineplex of work of this staff member
     */
    public Cineplex getWorkplace(){
        return workplace;
    }
    /**
     * Set the new cineplex of work for this staff member
     * @param workplace New cineplex of work for this staff member
     */
    public void setWorkplace(Cineplex workplace){
        this.workplace = workplace;
    }
}