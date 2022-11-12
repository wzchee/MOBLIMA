package controller;
import java.util.*;
import java.io.Serializable;
import java.util.Comparator;

//import MOBLIMA.FileInOut.*;

import java.util.ArrayList;
import models.*;
import database.*;


public class MovieController{
    
/** 
 * Method to add review to the current reviews of the movie{@link Review#writeReview(User)}.
 * Check if there is any movie available to add review.
 * If movie exists, check if there are any reviews from the user.
 * if the user has reviewed and rated the movie, add it into the current list of reviews and ratings for the movie.
 * @param reviewObj Review from the user
 * @throws Exception
 */
  public static void addReview(Review reviewObj) throws Exception {
    FileInOut<Movie> movieio = new FileInOut<Movie>();
    ArrayList<Movie> movieList = movieio.readData(new Movie());
  
    if(movieList == null){
      System.out.println("There is no movie available.");
      return;
    }
    for (int index = 0; index < movieList.size(); index++) {
      if (movieList.get(index).getMovieTitle().equals(reviewObj.getMovie().getMovieTitle())){
        Movie movieToReview = movieList.get(index);
        movieToReview.addToPastReview(reviewObj.getReview());
        movieToReview.setMovieRating(reviewObj.getRating());
      }
    }
    movieio.writeData(movieList,new Movie());
  }
  
  
  /** 
   * Method to update the review of the movie after user has overwritten their review {@link Review#writeReview(User)}.
   * Find the movie that user has changed their review and update accordingly by removing old reviews 
   * and add new reviews.
   * @param oldReview Old review that user wants to overwrite
   * @param oldRating Old rating given by the user
   * @param reviewToBeChanged New review and rating by the user
   * @throws Exception
   */
  public static void updateReviews(String oldReview,int oldRating,Review reviewToBeChanged) throws Exception{
    FileInOut<Movie> movieio = new FileInOut<Movie>();
    ArrayList<Movie> movieList = movieio.readData(new Movie());
  
    if(movieList == null){
      System.out.println("There is no movie available.");
      return;
    }
  
    Movie movieToReview = null;
  
    for (int index = 0; index < movieList.size(); index++) {
      if (movieList.get(index).getMovieTitle().equals(reviewToBeChanged.getMovie().getMovieTitle())){
        movieToReview = movieList.get(index);
        break;
      }
    }
  
    for(int i =0;i<movieToReview.getPastReviews().size();i++){
      if(movieToReview.getPastReviews().get(i).equalsIgnoreCase(oldReview)){
        movieToReview.getPastReviews().remove(i);
        movieToReview.addToPastReview(reviewToBeChanged.getReview());
      }
    }
  
    movieToReview.setMovieRating(reviewToBeChanged.getRating());
    movieToReview.setMovieRatingDown(oldRating);
    movieio.writeData(movieList,new Movie());
  
  }
  
  /**
   * Comparator for sorting the movies by descending sale volume.
   */
  public static Comparator<Movie> movieSalesComparator = new Comparator<Movie>(){
    public int compare(Movie m1, Movie m2){
      int saleVolume1 = m1.getSaleVolume();
      int saleVolume2 = m2.getSaleVolume();
      //Sort in descending order
      return saleVolume2 - saleVolume1;
    }
  };
  
  /**
   * Comparator for sorting the movies by descending average rating.
   */
  public static Comparator<Movie> movieRatingComparator = new Comparator<Movie>(){
    public int compare(Movie m1, Movie m2){
      return Double.compare(m2.getMovieAverageRating(m2.getMovieRating()),m1.getMovieAverageRating(m1.getMovieRating()));
    }
  };
  
  /** 
   * Method for staff to create a movie.
   * Includes an interface to ask staff for inputs.
   * Create a movie based on staff inputs.
   * @throws Exception
   */
  public static void createMovie()throws Exception{
    Movie newMovie = new Movie();
    Scanner in = new Scanner(System.in);
    String title = "";
    boolean success = false;
  
    do {
        try{
            System.out.println("Movie title: ");
            title = in.nextLine();
            success = true;
        } catch(InputMismatchException e){
            System.out.println("Your input is not a valid string!");
            System.out.println("Returning to staff menu...\n");
        }
    } while (!success);
  
    FileInOut<Movie> movieio = new FileInOut<Movie>();
    ArrayList<Movie> movieList = movieio.readData(new Movie());
  
    for (int i = 0; i < movieList.size(); i++) {
      if(movieList.get(i).getMovieTitle().equalsIgnoreCase(title)){
        System.out.println("Movie has already been created!");
        System.out.println("Returning to staff menu...\n");
        return;
      }
    }
    newMovie.setMovieTitle(title);
  
    int runtime = 0;
    success = false;
  
    do {
        try{
            System.out.println("Movie Runtime: ");
            runtime = Integer.parseInt(in.nextLine());
            success = true;
        } catch(InputMismatchException e){
            System.out.println("Your input is not a valid positive number!");
            System.out.println("Choose a new runtime\n");
            in.nextLine();
        }
    } while (!success && runtime < 0);
  
    newMovie.setMovieRuntime(runtime);
    int statusChoice = 0;
    success = false;
  
    do {
        try{
            System.out.println("Movie Status");
            System.out.println("1. Coming Soon");
            System.out.println("2. Preview");
            System.out.println("3. Now Showing");
            System.out.println("4. End of Showing");
            statusChoice = Integer.parseInt(in.nextLine());
            success = true;
        } catch (InputMismatchException e){
            System.out.println("Your input is not a valid number!");
            System.out.println("Choose a new status choice\n");
            in.nextLine();
        }
    } while (!success && (statusChoice < 1 || statusChoice > 4));
  
    switch (statusChoice){
        case 1:
        newMovie.setMovieStatus(Movie.status.valueOf("Coming_Soon"));
        break;
        case 2:
        newMovie.setMovieStatus(Movie.status.valueOf("Preview"));
        break;
        case 3:
        newMovie.setMovieStatus(Movie.status.valueOf("Now_Showing"));
        break;
        case 4:
        newMovie.setMovieStatus(Movie.status.valueOf("End_Of_Showing"));
        break;
    }
  
    int blockbusterChoice = 0;
    success = false;
  
    do {
        try{
            System.out.println("BlockBuster?");
            System.out.println("1. True");
            System.out.println("2. False");
            blockbusterChoice = Integer.parseInt(in.nextLine());
            success = true;
        } catch(InputMismatchException e){
            System.out.println("Your input is not a valid number!");
            System.out.println("Choose a new option\n");
            in.nextLine();
        }
    } while (!success && (blockbusterChoice != 1 || blockbusterChoice != 2));
  
    if (blockbusterChoice == 1){
        newMovie.setBlockbuster(true);
    }
    else{
        newMovie.setBlockbuster(false);
    }
  
    int dimChoice = 0;
    success = false;
  
    do {
        try{
            System.out.println("MovieDimension: ");
            System.out.println("1. 2D");
            System.out.println("2. 3D");
            dimChoice = Integer.parseInt(in.nextLine());
            success = true;
        } catch(InputMismatchException e){
            System.out.println("Your input is not a valid number!");
            System.out.println("Choose a new dimension\n");
            in.nextLine();
        }
    } while (!success && (blockbusterChoice != 1 || blockbusterChoice != 2));
  
    if (dimChoice == 1){
        newMovie.setMovieDims(Movie.dimension.valueOf("TwoD"));
    }
    else{
        newMovie.setMovieDims(Movie.dimension.valueOf("ThreeD"));
    }
  
    System.out.println("Movie Rating: ");
    newMovie.setRating(in.nextLine());
    System.out.println("Movie Sypnosis: ");
    newMovie.setMovieSypnosis(in.nextLine());
    System.out.println("Director: ");
    newMovie.setMovieDirector(in.nextLine());
    String[] cast = new String[2];
    System.out.println("Cast 1 name: ");
    cast[0] = in.nextLine();
    System.out.println("Cast 2 name:");
    cast[1] = in.nextLine();
    newMovie.setMovieCast(cast);
    newMovie.setSaleVolume(0);
    movieList.add(newMovie);
    movieio.writeData(movieList, new Movie());
  
  }
  
      
  /** 
   * Method for the staff to update the status of the movie.
   * Check for available movies, if there is a matching movie, change the corresponding movie status.
   * Update the list of movies and available screenings.
   * @return String status of the updated movie
   * @throws Exception
   */
  public static void updateMovie() throws Exception{
      FileInOut<Movie> movieio = new FileInOut<Movie>();
      ArrayList<Movie> movieList = movieio.readData(new Movie());
      Movie movieToUpdate = null;
      Scanner in = new Scanner(System.in);
  
      if(movieList.size() == 0){
        System.out.println("There is no movie available.");
        System.out.println("Returning to staff menu...\n");
        return;
      }
      else{
        System.out.println("Enter title of movie to be updated: ");
        String movieName = in.nextLine();
        int found = 0;
  
        for (int i = 0; i < movieList.size(); i++) {
            if(movieList.get(i).getMovieTitle().equalsIgnoreCase(movieName)){
                movieToUpdate = movieList.get(i);
                found = 1;
                break;
            }
        }
  
        if (found == 0){
            System.out.println("No such movie exists!");
            System.out.println("Returning to staff menu...\n");
            return;
        }
        else{
            System.out.println("Current movie status: "+ movieToUpdate.getMovieStatus());
            String currentMovieStatus = movieToUpdate.getMovieStatus();
            switch (currentMovieStatus){
                case "Coming_Soon":
                movieToUpdate.setMovieStatus(Movie.status.Preview);
                break;
                case "Preview":
                movieToUpdate.setMovieStatus((Movie.status.Now_Showing));
                break;
                case "Now_Showing":
                movieToUpdate.setMovieStatus(Movie.status.End_Of_Showing);
            }
            System.out.println("Movie status changed to "+movieToUpdate.getMovieStatus());
        }
  
        movieio.writeData(movieList, new Movie());
  
        if(movieToUpdate.getMovieStatus().equals("End_Of_Showing")){
          MovieController.removeMovie(movieToUpdate.getMovieTitle());
          MovieScreeningController.removeMovieScreeningWithMovie(movieToUpdate);
        }
        else{
          MovieScreeningController.updateMovieScreeningWithMovie(movieToUpdate);
        }     
        }
  }
  
  
  /** 
   * Method to remove a movie by changing the status of the movie to End of Showing.
   * Update the corresponding movie screening by removing it.
   * @param movieName Title of movie to be deleted
   * @throws Exception
   */
  public static void removeMovie(String movieName) throws Exception{
      FileInOut<Movie> movieio = new FileInOut<Movie>();
      ArrayList<Movie> movieList = movieio.readData(new Movie());
  
      if (movieList == null || movieList.size()<1){
        System.out.println("There is no movie available.");
        System.out.println("Returning to staff menu...\n");
      }
  
      int found = 0;
  
      for (int i = 0; i < movieList.size(); i++) {
          if(movieList.get(i).getMovieTitle().equalsIgnoreCase(movieName)){
            
              movieList.get(i).setMovieStatus(Movie.status.End_Of_Showing);
              MovieScreeningController.removeMovieScreeningWithMovie(movieList.get(i));
              found = 1;
              System.out.println(movieList.get(i).getMovieTitle()+" successfully deleted!");
              break;
          }
        }
  
      if (found == 0){
          System.out.println("No such movie exists!");
          System.out.println("Returning to staff menu...\n");
      }
  
      movieio.writeData(movieList, new Movie());
  }
  
  /** 
   * Method to display all available movie and its corresponding status and rating
   * @return ArrayList<Movie> List of Movies with its coresponding title, status and rating.
   * @throws Exception
   */
  public static ArrayList<Movie> showMovieList() throws Exception{
    FileInOut<Movie> movieio = new FileInOut<Movie>();
    ArrayList<Movie> allMovieList = movieio.readData(new Movie());
    ArrayList<Movie> movieList = getAvailableMovieList(allMovieList);
  
    for (int index = 0; index < movieList.size(); index++) {
        System.out.println(index+1 +". "+ movieList.get(index).getMovieTitle());
        System.out.println("Status: "+movieList.get(index).getMovieStatus());
  
        if(movieList.get(index).getMovieAverageRating(movieList.get(index).getMovieRating())== 0.0){
          System.out.println("Ratings: NA");
        }
        else{
          System.out.println("Ratings: "+     movieList.get(index).getMovieAverageRating(movieList.get(index).getMovieRating()) +"\n");
        }
        System.out.println();
      }
  
    movieio.writeData(allMovieList, new Movie());
    return movieList;
  }
  
  
  /** 
   * Helper function to retrieve all the available movies.
   * @param arrListToBeLooped The list of all movies to be passed in.
   * @return ArrayList<Movie> List of available movies that status are not End of Showing.
   * @throws Exception
   */
  public static ArrayList<Movie> getAvailableMovieList(ArrayList<Movie> arrListToBeLooped) throws Exception{
    ArrayList<Movie> movieList = new ArrayList<Movie>();
  
    for (int i = 0; i < arrListToBeLooped.size(); i++) {
      if(!arrListToBeLooped.get(i).getMovieStatus().equals("End_Of_Showing") && !arrListToBeLooped.get(i).getMovieStatus().equals("Coming_Soon")){
        movieList.add(arrListToBeLooped.get(i));
      }
    }
    return movieList;
  }
  
  
  /** 
   * Method to show the details of the movie selected
   * @param movieTitle Title of the movie for its details to be shown
   * @throws Exception
   */
  public static void showMovieDetail(String movieTitle) throws Exception{
    FileInOut<Movie> movieio = new FileInOut<Movie>();
    ArrayList<Movie> movieList = movieio.readData(new Movie());
    int choice = 0;
    for(int i=0; i<movieList.size(); i++){
      if(movieList.get(i).getMovieTitle().equals(movieTitle)){
        choice= i;
        break;
      }
    }
  
    System.out.println();
    System.out.println("==============  Information on " + movieList.get(choice).getMovieTitle() + " ==============");
    System.out.println("Movie Title: " + movieList.get(choice).getMovieTitle());
    System.out.println("Movie Runtime: " + movieList.get(choice).getMovieRuntime());
    System.out.println("Movie Status: " + movieList.get(choice).getMovieStatus());
    System.out.println("Movie Dimensions: " + movieList.get(choice).getMovieDims());
    System.out.println("Movie Rating: " + movieList.get(choice).getRating());
    System.out.println("Director of film: " + movieList.get(choice).getMovieDirector());
    System.out.print("Movie Cast: || ");
    for (String member : movieList.get(choice).getMovieCast()) System.out.print(member + " || ");
    System.out.println();
    System.out.println("Movie Synopsis");
    System.out.println(movieList.get(choice).getMovieSypnosis());
  
    if(movieList.get(choice).getMovieAverageRating(movieList.get(choice).getMovieRating())== 0.0){
      System.out.println("Ratings: NA");
    }
    else{
      System.out.println("Ratings: "+ movieList.get(choice).getMovieAverageRating(movieList.get(choice).getMovieRating()) +"\n");
    }
  
    if(movieList.get(choice).getPastReviews().size() == 0){
      System.out.println("Reviews: NA");
    }
    else{
      System.out.println("Reviews:");
  
      for (int index = 0; index < movieList.get(choice).getPastReviews().size(); index++) {
        System.out.println(index+1 +". "+ movieList.get(choice).getPastReviews().get(index));
      }
    }
  }
  
  
  /** 
   * Method to search for the movie by the user.
   * Allows user to search for available movies without typing out the entire movie title due to substring matching.
   * @param movieTitle Title of the movie user wants to search
   * @return ArrayList<Movie> the list of available movies that the user may want to search.
   * @throws Exception
   */
  public static ArrayList<Movie> searchMovieList(String movieTitle) throws Exception{
    FileInOut<Movie> movieio = new FileInOut<Movie>();
    ArrayList<Movie> allMovieList = movieio.readData(new Movie());
    ArrayList<Movie> movieList = MovieController.getAvailableMovieList(allMovieList);
    ArrayList<Movie> listToReturn = new ArrayList<>();
  
    for (Movie movie : movieList) {
      if (movie.getMovieTitle().toLowerCase().contains(movieTitle.toLowerCase())) listToReturn.add(movie);
    }
  
    movieio.writeData(allMovieList, new Movie());
    return listToReturn;
  }
  
  /** 
   * Method to sort the ArrayList of movies based on user input and print the top 5 available movies based on 
   * (1) by rating, (2) sales
   * @throws Exception
   */
  public static void sortMovie()throws Exception{
    Scanner input = new Scanner(System.in);
    int choice = 0;
    FileInOut<Movie> movieio = new FileInOut<Movie>();
    ArrayList<Movie> allMovieList = movieio.readData(new Movie());
    ArrayList<Movie> movieList = MovieController.getAvailableMovieList(allMovieList);
  
    do{
      System.out.println("\nView top 5 movies");
      System.out.println("1. By Ratings");
      System.out.println("2. By Sales Volume");
      try{
        choice = Integer.parseInt(input.nextLine());
      } catch(NumberFormatException e){
          System.out.println("Please input a valid number!");
          System.out.println("Returning to staff menu...\n");
          return;
      }
    }
    if(choice<1 || choice >2){
      System.out.println("Please input a valid number!");
      System.out.println("Returning to staff menu...\n");
      return;
    }
  
    if (choice == 1){
      Collections.sort(movieList, MovieController.movieRatingComparator);
    }
    else{
      Collections.sort(movieList, MovieController.movieSalesComparator);
    }
    
    for (int index = 0; index < (movieList.size() < 5 ? movieList.size():5); index++) {
      if(!movieList.get(index).getMovieStatus().equalsIgnoreCase("End_Of_Showing")){
        System.out.println(index+1 +". "+ movieList.get(index).getMovieTitle());
        System.out.println("Status: "+movieList.get(index).getMovieStatus());
        System.out.println("Rating: "+ String.format(" %.2f",movieList.get(index).getMovieAverageRating(movieList.get(index).getMovieRating())));
        System.out.println("Sale Volume: "+movieList.get(index).getSaleVolume());
        System.out.println();
      }
      
    }
    movieio.writeData(allMovieList, new Movie());
  }

}