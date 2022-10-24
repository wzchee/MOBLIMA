import java.util.*;
public class Movie {
  private String movieTitle;
  private String movieStatus;
  private String movieSypnosis;
  private String director;
  private String[] cast;
  private int rating;
  private String[] pastReviews;
  private movieTypes movieType;
  public Movie(String movieTitle, movieTypes movieType, String movieStatus, String movieSypnosis,String director,String[] cast,int rating,String[] pastReviews)
{
  this.movieTitle = movieTitle;
  this.movieStatus = movieStatus;
  this.movieSypnosis = movieSypnosis;
  this.director = director;
  this.cast = cast;
  this.rating = rating;
  this.pastReviews = pastReviews;
  this.movieType = movieType;
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
  return this.movieStatus;
}
public String getMovieType(){
  return this.movieType.toString();
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


public void setMovieTitle(String movieTitle){
  this.movieTitle = movieTitle ;
}
public void setMovieStatus(String movieStatus){
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
public void setMovieType(movieTypes movietype){
  this.movieType = movietype;
}


}
