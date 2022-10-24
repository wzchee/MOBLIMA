public class Cineplex {
    private Cinema[] cinemas = new Cinema[3];

    public Cineplex() {
    }

    public void addCinema(int index, String cinemaName){
        this.cinemas[index].cinemaName =  cinemaName;
    }

    public Cinema getCinema(int index){
        return cinemas[index];
    }

    public void replaceCinema(int index, String cinemaName){
        this.cinemas[index].cinemaName = cinemaName;
    }
}
