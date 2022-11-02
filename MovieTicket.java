public class MovieTicket {
    private MovieScreening aMovieScreening;
    private int seatNumber;
    private User userObj;
    private int price;



    public MovieTicket(int seatNumber,User userObj,int price) {
        this.seatNumber = seatNumber;
        this.userObj = userObj;
        this.aMovieScreening = aMovieScreening;
        this.price = price;
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

   public Cinema getPrice(){
    return this.getPrice();
    }
    
}



//===============================================================================
// Movie screening should have movie object and then in our movie ticket we can has - a movie screening only then can
// we reference the cinema type, public holiday or not. Then in our movie

//===============================================================================
// ///
