package Models;
import java.io.Serializable;

public class Cineplex implements Serializable{
    private String cineplexName;

    public Cineplex(String cineplexName) {
        this.cineplexName = cineplexName;
    }

    public Cineplex() {}

    public String getCineplexName() {
        return this.cineplexName;
    }

    public void setCineplexName(String cineplexName) {
        this.cineplexName = cineplexName;
    }
}
