/*enum cinemaStandard {
    Platinum_Suites, Standard;
}*/

public class Cinema {
    private String cinemaName;
    private boolean platinumSuites;
    private String[] movieList = new String[100];
    private String[] movieScreening = new String[24];
    private Seat[][] cinemaSeats;

    public Cinema(String cinemaName, boolean platinumSuites){
        this.cinemaName = cinemaName;
        this.platinumSuites = platinumSuites;
        for (int i = 0; i<10; i++){
            for (int j = 0; j<10; j++){
                cinemaSeats[i][j].setId(-1);
                cinemaSeats[i][j].setIsOccupied(false);
            }
        }
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

    public Seat getSeat(int row, int col) {
        return this.cinemaSeats[row][col];
    }

    public void reserveSeat(int row, int col, int id) {
        this.cinemaSeats[row][col].setId(id);
        this.cinemaSeats[row][col].setIsOccupied(false);
    }


}