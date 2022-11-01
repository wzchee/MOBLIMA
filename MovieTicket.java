public class MovieTicket {
    private MovieScreening aMovieScreening;
    private Seat mySeat;
    private User userObj;



    public MovieTicket(Seat mySeat,User userObj) {
        this.mySeat = mySeat;
        this.userObj = userObj;
        this.aMovieScreening = aMovieScreening;
    }

    public Cinema getLocation(){
        return aMovieScreening.getCinema();
    }

    public Seat getSeat(){
        return this.mySeat;
    }

   public User getUser(){
       return this.userObj;
   }


    public int calcPrice() {
        //if this.aMovieScreening.getCinema.getCinemaClass{

        //if this.aMovieScreening.movieObj.movietype{

        //if userBuying.getAge ....

        //if this.aMovieScreening.isPublicHoliday...
        return 1;
    }
}



//===============================================================================
// Movie screening should have movie object and then in our movie ticket we can has - a movie screening only then can
// we reference the cinema type, public holiday or not. Then in our movie

//===============================================================================
// ///
