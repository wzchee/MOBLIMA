import java.io.Serializable;

public class Cineplex implements Serializable{
    private String cineplexName;
    private Cinema[] cinemas = new Cinema[3];

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

    public void addCinema(int index, String cinemaName, boolean platinumSuites){
        this.cinemas[index].setCinemaName(cinemaName);
        this.cinemas[index].setPlatinumSuite(platinumSuites);
    }

    public Cinema getCinema(int index){
        return cinemas[index];
    }
}
