import java.io.Serializable;

/*enum cinemaStandard {
    Platinum_Suites, Standard;
}*/

public class Cinema implements Serializable{
    private String cinemaName;
    private Cineplex cineplex;
    private boolean platinumSuites;
    private String[] movieList = new String[100];
    private String[] movieScreening = new String[24];
    private int numOfSeats;


    public Cinema(String cinemaName, Cineplex cineplex, boolean platinumSuites, int numOfSeats) {
        this.cinemaName = cinemaName;
        this.cineplex.setCineplexName(cineplex.getCineplexName());
        this.platinumSuites = platinumSuites;
        this.numOfSeats = numOfSeats;

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

    public void setCineplexName(String cineplexName) {
        this.cineplex.setCineplexName(cineplexName);
    }

    public boolean isPlatinumSuite() {
        return this.platinumSuites;
    }
    public int getNumOfSeats(){
        return this.numOfSeats;
    }

    public void setPlatinumSuite(boolean isPlatinumSuites) {
        this.platinumSuites = isPlatinumSuites;
    }

    public String getMovie(int index) {
        return this.movieList[index];
    }

    public void setMovie(int index, String movieName) {
        this.movieList[index] = movieName;
    }

    public void setNumOfSeats(int numOfSeats){
        this.numOfSeats = numOfSeats;
    }

    public String getMovieTime(int index) {
        return this.movieScreening[index];
    }

    public void setMovieTime(int index, String movie) {
        this.movieScreening[index] = movie;
    }

}