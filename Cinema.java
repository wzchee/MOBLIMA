import java.io.Serializable;

/*enum cinemaStandard {
    Platinum_Suites, Standard;
}*/

public class Cinema implements Serializable{
    private String cinemaName;
    private Cineplex cineplex;
    private boolean platinumSuites;
    private String cinemaCode;
    //private String[] movieList = new String[100];
    //private String[] movieScreening = new String[24];
    private int numOfSeats;


    public Cinema(String cinemaName, Cineplex cineplex, boolean platinumSuites, int numOfSeats, String cinemaCode) {
        this.cinemaName = cinemaName;
        this.cineplex = cineplex;
        this.platinumSuites = platinumSuites;
        this.numOfSeats = numOfSeats;
        this.cinemaCode = cinemaCode;
    }

    public Cinema() {
    }

    public String getCinemaName() {
        return this.cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getCineplexName() {
        return this.cineplex.getCineplexName();
    }

    public boolean isPlatinumSuite() {
        return this.platinumSuites;
    }
    public int getNumOfSeats(){
        return this.numOfSeats;
    }

    public String getCinemaCode() {
        return this.cinemaCode;
    }

    public void setPlatinumSuite(boolean isPlatinumSuites) {
        this.platinumSuites = isPlatinumSuites;
    }

    public void setNumOfSeats(int numOfSeats){
        this.numOfSeats = numOfSeats;
    }

    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }

    /*public int getMovieTime(String movieName) {
        int i;
        for (i = 0; i < 24; i++) {
            if (this.movieScreening[i] == movieName) break;
        }
        return i;
    }

    public void setMovieTime(int index, String movie) {
        this.movieScreening[index] = movie;
    }*/

}