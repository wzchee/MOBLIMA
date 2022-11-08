import java.io.Serializable;

/**
 * Java class that contains basic information on what a cineplex holds. Cineplex Object
 * @author  Bernard Chiang
 * @version 1.0
 * @since   2022-11-8
 */

public class Cineplex implements Serializable{
    /**
     * Name of <code>Cineplex</code> Object
     */
    private String cineplexName;

    /**
     * Default <code>Cineplex</code> Constructor 
     * @param cineplexName - Name of <code>Cineplex</code> Object
     */
    public Cineplex(String cineplexName) {
        this.cineplexName = cineplexName;
    }

    /**
     * Empty <code>Cineplex</code> Constructor 
     */
    public Cineplex() {}

    /**
     * Getter method to return the name of <code>Cineplex</code> Object
     * @return <code>cineplexName</code>
     */
    public String getCineplexName() {
        return this.cineplexName;
    }

    /**
     * Setter method to set the name of <code>Cineplex</code> Object
     * @param cineplexName - Name of <code>Cineplex</code> Object
     */
    public void setCineplexName(String cineplexName) {
        this.cineplexName = cineplexName;
    }
}
