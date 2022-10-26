import java.util.*;

enum status{
   Coming_Soon,Preview,Now_Showing,End_Of_Showing
  }
enum dimension{
  TwoD,ThreeD
}
public class Movie {
  private String movieTitle;
  private String movieSypnosis;
  private String director;
  private String[] cast;
  private int rating;
  private String[] pastReviews;
  private dimension dims;
  private status movieStatus;
  private boolean blockbuster;
  private int saleVolume;
  public Movie(String movieTitle, int saleVolume,dimension dims, status movieStatus,boolean blockbuster, String movieSypnosis,String director,String[] cast,int rating,String[] pastReviews)
{
  this.movieTitle = movieTitle;
  this.movieStatus = movieStatus;
  this.movieSypnosis = movieSypnosis;
  this.director = director;
  this.cast = cast;
  this.rating = rating;
  this.pastReviews = pastReviews;
  this.dims = dims;
  this.movieStatus = movieStatus;
  this.blockbuster = blockbuster;
  this.saleVolume = saleVolume;
}
public Movie(String movieTitle){
  this.movieTitle = movieTitle;
}
public Movie(){

}
public String getMovieTitle(){
  return this.movieTitle;
}
public String getMovieStatus(){
  return this.movieStatus.toString();
}
public boolean getBlockbuster(){
  return this.blockbuster;
}
public String getMovieSypnosis(){
  return this.movieSypnosis;
}
public String getMovieDirector(){
  return this.director;
}
public String[] getMovieCast(){
  return this.cast;
}
public int getMovieRating(){
  return this.rating;
}
public String[] getPastReviews(){
  return this.pastReviews;
}
public int getSaleVolume(){
  return this.saleVolume;
}
public void setBlockbuster(boolean blockbuster){
  this.blockbuster = blockbuster;
}

public void setMovieTitle(String movieTitle){
  this.movieTitle = movieTitle ;
}
public void setMovieStatus(status movieStatus){
  this.movieStatus = movieStatus;

}
public void setMovieSypnosis(String movieSypnosis){
  this.movieSypnosis = movieSypnosis;
}
public void setMovieDirector(String director){
  this.director = director;
}
public void setMovieCast(String[] cast){
  this.cast = cast;
}
public void  setMovieRating(int rating){
  this.rating = rating;
}
public void setPastReviews(String[] pastReviews){
  this.pastReviews = pastReviews;
}
public void editMovie(){

}




}
