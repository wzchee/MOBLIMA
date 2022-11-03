import java.io.Serializable;


public class MovieTicket implements Serializable{
    private MovieScreening aMovieScreening;
    private int seatNumber;
    private updatedUser userObj;
    private Double price;



    public MovieTicket(MovieScreening aMovieScreening, int seatNumber,updatedUser userObj,Double price) {
        this.seatNumber = seatNumber;
        this.userObj = userObj;
        this.aMovieScreening = aMovieScreening;
        this.price = price;
    }

    public Cinema getLocation(){
        return aMovieScreening.getMovieScreeningLocation();
    }

    public int getseatNumber(){
        return this.seatNumber;
    }

   public updatedUser getUser(){
       return this.userObj;
   }

   public Double getPrice(){
    return this.getPrice();
    }
    
}



//===============================================================================
// Movie screening should have movie object and then in our movie ticket we can has - a movie screening only then can
// we reference the cinema type, public holiday or not. Then in our movie

//===============================================================================
// ///
