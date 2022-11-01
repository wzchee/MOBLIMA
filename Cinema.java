/*enum cinemaStandard {
    Platinum_Suites, Standard;
}*/

public class Cinema {
    private String cinemaName;
    private boolean platinumSuites;
    private String[] movieList = new String[100];
    private String[] movieScreening = new String[24];


    public Cinema(String cinemaName, boolean platinumSuites){
        this.cinemaName = cinemaName;
        this.platinumSuites = platinumSuites;

    }

    public Cinema() {}

    public String getCinemaName() {
        return this.cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public boolean isPlatinumSuite() {
        return this.platinumSuites;
    }

    public void setPlatinumSuite(boolean isPlatinumSuites){
        this.platinumSuites = isPlatinumSuites;
    }

    public String getMovie(int index) {
        return this.movieList[index];
    }

    public void setMovie(int index, String movieName) {
        this.movieList[index] = movieName;
    }

    public String getMovieTime(int index){
        return this.movieScreening[index];
    }

    public void setMovieTime(int index, String movie){
        this.movieScreening[index] = movie;
    }


}