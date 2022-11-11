package models;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * Java class representing a movie in MOBLIMA
 * @author  Cheong Jia Rong
 * @version 1.0
 * @since   2022-7-11
 * @see     Review
 * @see     MovieScreening
 */


public class Movie implements Serializable{
  /**
   * Movie Title
   */
  private String movieTitle;
  /**
   * Movie Runtime
   */
  private int movieRuntime;
  /**
   * Movie Sypnosis
   */
  private String movieSypnosis;
  /**
   * Name of the Movie Director
   */
  private String director;
  /**
   * Name of the casts
   * Only stores the male lead and female lead
   */
  private String[] cast = new String[2]; 
  /**
   * 1D Array to store the count of the coresponding rating from 1-5 of the movie
   */
  private int[] rating = new int[6];
  /**
   * ArrayList of type String to store the reviews of the movie
   */
  private ArrayList<String> pastReviews = new ArrayList<String>();
  /**
   * Movie dimensions using enum {@code dimensions}
   */
  private dimension dims;
  /**
   * Movie Status using enum {@code status}
   */
  private status movieStatus;
  /**
   * Boolean to check if the movie is a blockbuster
   */
  private boolean blockbuster;
  /**
   * Sale volume of the movie based on number of tickets sold
   */
  private int saleVolume;
  /**
   * Movie Rating(PG,PG13, M18, R21)
   */
  private String movieRating;
  /**
   * Average rating of the movie
   */
  private double averageRating;

  /**
   * Movie status to be assigned
   * @see #Coming_Soon
   * @see #Preview
   * @see #Now_Showing
   * @see #End_Of_Showing
   */
  public enum status{
    /**
     * Coming soon
     */
    Coming_Soon,
    /**
     * Preview
     */
    Preview,
    /**
     * Now Showing 
     */
    Now_Showing,
    /**
     * End of Showing
     */
    End_Of_Showing
    }

  /**
   * Dimensions of the movie
   * @see #TwoD
   * @see #ThreeD
   */
  public enum dimension{
    /**
     * 2D
     */
    TwoD,
    /** 
     * 3D
     */
    ThreeD
  }


  /**
   * Constructor to create a {@code Movie} object with its attributes initialized
   * @param movieTitle    Title of movie to be created
   * @param movieRuntime  Runtime of movie to be created
   * @param dims          Dimension of movie to be created
   * @param movieStatus   Status of movie to be created
   * @param blockbuster   Check whether the movie to be created is a blockbuster 
   * @param movieSypnosis Sypnosis of movie to be created
   * @param director      Name of director of the movie to be created
   * @param cast          Names of the male and female lead of the movie to be created
   * @param movieRating   Rating of the movie to be created (PG, PG13, M18, R21)
   */
  public Movie(String movieTitle, int movieRuntime,dimension dims, status movieStatus,boolean blockbuster, String movieSypnosis,String director,String[] cast, String movieRating)
{
  this.movieTitle = movieTitle;
  this.movieRuntime = movieRuntime;
  this.movieStatus = movieStatus;
  this.movieSypnosis = movieSypnosis;
  this.director = director;
  this.cast = cast;
  /**
   * Initializing 1D array of rating of movie to be created of size 6 to be {0,0,0,0,0,0}
   */
  this.rating = new int[6];
  /**
   * Initializing ArrayList of pastReviews of movie to be created to be an empty Arraylist of type string
   */
  this.pastReviews = new ArrayList<String>();
  this.dims = dims;
  this.movieStatus = movieStatus;
  this.blockbuster = blockbuster;
  /**
   * Initializing sale volume of movie to be created to be 0 since no tickets are sold yet
   */
  this.saleVolume = 0;
  this.movieRating = movieRating;
}

/**
 * Alternative constructor to create a {@code Movie} with 0 parameters
 * Initialize rating of movie to be created to be a 1D array of size 6 .
 * Initialize past reviews of movie to be created to be an empty ArrayList of type string.
 * Intialize average rating of movie to be created to be 0.
 */
public Movie(){
  this.rating = new int[6];
  for (int i = 0; i<6;i++) this.rating[i] = 0;
  this.pastReviews = new ArrayList<String>();
  this.averageRating = 0.0;
}

/** 
 * Getter method to retrieve title of movie.
 * @return String name of movie title.
 */
public String getMovieTitle(){
  return this.movieTitle;
}

/** 
 * Getter method to retrieve runtime of movie.
 * @return int Runtime of movie.
 */
public int getMovieRuntime() {
  return this.movieRuntime;
}

/** 
 * Getter method to retrieve movie status.
 * @return String Status of movie.
 */
public String getMovieStatus(){
  return this.movieStatus.toString();
}

/** 
 * getter method to retrieve movie dimensions.
 * @return String Dimensions of movie.
 */
public String getMovieDims(){
  return this.dims.toString();
}

/** 
 * Getter method to retrieve blockbuster status of movie.
 * @return boolean true for blockbuster; false for non blockbuster.
 */
public boolean getBlockbuster(){
  return this.blockbuster;
}

/** 
 * Getter method to retrieve sypnosis of movie.
 * @return String Sypnosis of movie.
 */
public String getMovieSypnosis(){
  return this.movieSypnosis;
}

/** 
 * Getter method to retrieve name of movie director.
 * @return String Name of movie director.
 */
public String getMovieDirector(){
  return this.director;
}

/** 
 * Getter method to retrieve name of the casts in the movie.
 * @return String[] Name of casts.
 */
public String[] getMovieCast(){
  return this.cast;
}

/** 
 * Getter method to retrieve the 1D array of movie ratings.
 * @return int[] Ratings of movie.
 */
public int[] getMovieRating(){
  return this.rating;
}

/** 
 * Getter method to get the Movie Rating(PG, PG13, M18, R21).
 * @return String Rating of movie.
 */
public String getRating(){
  return this.movieRating;
}

/** 
 * Getter method to get average rating of the movie.
 * @param rating Rating of movie.
 * @return double Average rating from the 1D array passed in.
 */
public double getMovieAverageRating(int[] rating){
  double sum = 0;
  double numberOfRatings = 0;
  double average = 0;
  for (int i = 1; i < rating.length; i++) {
    //numerator which is the sum of all the ratings
    sum+=(rating[i]*i);
    //denominator which is the number of ratings
    numberOfRatings+=rating[i];
  }
  //Movie details will show NA if there are less than 2 ratings
  if(numberOfRatings<2){
    return 0.0;
  }
  average = sum/numberOfRatings;
  return average;

}


/** 
 * Getter method to retrieve the past reviews of the movie.
 * @return ArrayList<String>  Past reviews of movie.
 */
public ArrayList<String> getPastReviews(){
  return this.pastReviews;
}

/** 
 * Getter method to retrieve sale volume of movie based on tickets sold.
 * @return int Sale volume of movie.
 */
public int getSaleVolume(){
  return this.saleVolume;
}

/** 
 * Setter method to set movie sale volume.
 * @param saleVolume Sale volume of the movie to be set to
 */
public void setSaleVolume(int saleVolume){
  this.saleVolume = saleVolume;
}

/** 
 * Setter method to set blockbuster status
 * @param blockbuster Blockbuster status to be set to
 */
public void setBlockbuster(boolean blockbuster){
  this.blockbuster = blockbuster;
}


/** 
 * Setter method to set movie dimensions.
 * @param dims Dimension of the movie to be set to
 */
public void setMovieDims(dimension dims){
  this.dims = dims;
}


/** 
 * Setter method to set movie title.
 * @param movieTitle Title of movie to be set to
 */
public void setMovieTitle(String movieTitle){
  this.movieTitle = movieTitle ;
}


/** 
 * Setter method to set movie run time.
 * @param movieRuntime Runtime of Movie to be set to
 */
public void setMovieRuntime(int movieRuntime) {
  this.movieRuntime = movieRuntime;
}

/** 
 * Setter method to set movie status.
 * @param movieStatus Status of movie to be set to
 */
public void setMovieStatus(status movieStatus){
  this.movieStatus = movieStatus;

}

/** 
 * Setter method to set movie sypnosis.
 * @param movieSypnosis Movie sypnosis to be set to
 */
public void setMovieSypnosis(String movieSypnosis){
  this.movieSypnosis = movieSypnosis;
}

/** 
 * Setter method to set name of movie director.
 * @param director Name of director to be set to
 */
public void setMovieDirector(String director){
  this.director = director;
}

/** 
 * Setter method to set name of casts
 * @param cast Names of cast to be set to
 */
public void setMovieCast(String[] cast){
  this.cast = cast;
}

/** 
 * Setter method to set movie rating by user.
 * @param rating Movie rating given by user
 */
public void  setMovieRating(int rating){
  this.rating[rating] += 1;
}

/** Setter method to decrement movie rating for overwriting reviews {@link Review#writeReview(User)}.
 * @param rating Old movie rating gieven by user
 */
public void  setMovieRatingDown(int rating){
  this.rating[rating] -= 1;
}

/** 
 * Setter method to set movie rating by staff.
 * @param movieRating Movie rating(PG, PG13, M18, R21)
 */
public void setRating(String movieRating){
  this.movieRating = movieRating;
}

/** 
 * Setter method to set movie past review by user.
 * @param reviewString Review from user.
 */
public void addToPastReview(String reviewString) {
  this.pastReviews.add(reviewString);
}

/**
 * Method to increment sale volume of movie after every ticket is bought {@link User#usercreateBooking(User)}.
 */
public void incrementSaleVolume(){
  this.saleVolume += 1;
}

/** 
 * Setter method to set the past reviews of the movie.
 * @param pastReviews  Past reviews of movie
 */
public void setPastReviews(ArrayList<String> pastReviews){
  this.pastReviews = pastReviews;
}

}

