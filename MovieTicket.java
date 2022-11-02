public class MovieTicket {
    private MovieScreening aMovieScreening;
    private int seatNumber;
    private User userObj;



    public MovieTicket(int seatNumber,User userObj) {
        this.seatNumber = seatNumber;
        this.userObj = userObj;
        this.aMovieScreening = aMovieScreening;
    }

    public Cinema getLocation(){
        return aMovieScreening.getCinema();
    }

    public int getseatNumber(){
        return this.seatNumber;
    }

   public User getUser(){
       return this.userObj;
   }


    
}



//===============================================================================
// Movie screening should have movie object and then in our movie ticket we can has - a movie screening only then can
// we reference the cinema type, public holiday or not. Then in our movie

//===============================================================================
// ///
