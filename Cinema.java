public class Cinema {
    protected String cinemaName;
    private String[] movieList = new String[100];
    private String[] movieScreening = new String[24];
    private Seat[][] cinemaSeats;

    public Cinema(String cinemaName){
        this.cinemaName = cinemaName;
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
        return Seat[row][col];
    }

    public void reserveSeat(int row, int col, ) {
        Seat[row][col] = ;
    }
}