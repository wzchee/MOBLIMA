import java.util.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.ArrayList;

enum status{
   Coming_Soon,Preview,Now_Showing,End_Of_Showing
  }
enum dimension{
  TwoD,ThreeD
}
public class Movie implements Serializable{
  private String movieTitle;
  private int movieRuntime;
  private String movieSypnosis;
  private String director;
  private String[] cast;
  private int[] rating = new int[6];
  private ArrayList<String> pastReviews;
  private dimension dims;
  private status movieStatus;
  private boolean blockbuster;
  private int saleVolume;
  private String movieRating;
  private double averageRating;
  public Movie(String movieTitle, int movieRuntime, int saleVolume,dimension dims, status movieStatus,boolean blockbuster, String movieSypnosis,String director,String[] cast,int[] rating,ArrayList<String>pastReviews, String movieRating)
{
  this.movieTitle = movieTitle;
  this.movieRuntime = movieRuntime;
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
  this.movieRating = movieRating;
}
public Movie(String movieTitle){
  this.movieTitle = movieTitle;
}
public Movie(){

}
public String getMovieTitle(){
  return this.movieTitle;
}
public int getMovieRuntime() {
  return this.movieRuntime;
}
public String getMovieStatus(){
  return this.movieStatus.toString();
}
public String getMovieDims(){
  return this.dims.toString();
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
public int[] getMovieRating(){
  return this.rating;
}
public String getRating(){
  return this.movieRating;
}
public double getMovieAverageRating(int[] rating){
  double sum = 0;
  double numberOfRatings = 0;
  double average = 0;
  for (int i = 1; i < rating.length; i++) {
    sum+=(rating[i]*i);
    numberOfRatings+=rating[i];
  }
  average = sum/numberOfRatings;
  return average;

}

public ArrayList<String> getPastReviews(){
  return this.pastReviews;
}
public int getSaleVolume(){
  return this.saleVolume;
}
public void setSaleVolume(int saleVolume){
  this.saleVolume = saleVolume;
}
public void setBlockbuster(boolean blockbuster){
  this.blockbuster = blockbuster;
}

public void setMovieDims(dimension dims){
  this.dims = dims;
}

public void setMovieTitle(String movieTitle){
  this.movieTitle = movieTitle ;
}

public void setMovieRuntime(int movieRuntime) {
  this.movieRuntime = movieRuntime;
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
  this.rating[rating] += 1;
}
public void setPastReviews(ArrayList<String> pastReviews){
  this.pastReviews = pastReviews;
}

public void setRating(String movieRating){
  this.movieRating = movieRating;
}

public String toString(){
  return "Movie Title: " + this.getMovieTitle() + "is currently " + this.getMovieStatus() 
  + ". \nSynopsis: " + this.getMovieSypnosis() + ". \nDirector: " + this.getMovieDirector() 
  + "   Cast: " + this.getMovieCast()[0] + ", " + this.getMovieCast()[1] + ". \nRating: ";
}

public void incrementSaleVolume(){
  this.saleVolume += 1;
}
// Comparator for sorting the list by Sale Volume
public static Comparator<Movie> movieSalesComparator = new Comparator<Movie>(){
  public int compare(Movie m1, Movie m2){
    int saleVolume1 = m1.getSaleVolume();
    int saleVolume2 = m2.getSaleVolume();
    //Sort in descending order
    return saleVolume2 - saleVolume1;
  }
};
// Comparator for sorting the list by Sale Volume
public static Comparator<Movie> movieRatingComparator = new Comparator<Movie>(){
  public int compare(Movie m1, Movie m2){
    return Double.compare(m2.getMovieAverageRating(m2.getMovieRating()),m1.getMovieAverageRating(m1.getMovieRating()));
  }
};


public static void createMovie()throws Exception{
        Movie newMovie = new Movie();
        Scanner in = new Scanner(System.in);
        System.out.println("Movie title: ");
        newMovie.setMovieTitle(in.next());
        System.out.println("Movie Runtime: ");
        newMovie.setMovieRuntime(in.nextInt());
        System.out.println("Movie Status");
        System.out.println("1. Coming Soon");
        System.out.println("2. Preview");
        System.out.println("3. Now Showing");
        System.out.println("4. End of Showing");
        int statusChoice = in.nextInt();
        switch (statusChoice){
            case 1:
            newMovie.setMovieStatus(status.valueOf("Coming_Soon"));
            break;
            case 2:
            newMovie.setMovieStatus(status.valueOf("Preview"));
            break;
            case 3:
            newMovie.setMovieStatus(status.valueOf("Now_Showing"));
            break;
            case 4:
            newMovie.setMovieStatus(status.valueOf("End_Of_Showing"));
            break;
            
        }
        System.out.println("BlockBuster?");
        System.out.println("1. True");
        System.out.println("2. False");
        int blockbusterChoice = in.nextInt();
        if (blockbusterChoice == 1){
            newMovie.setBlockbuster(true);
        }else{
            newMovie.setBlockbuster(false);
        }
        System.out.println("MovieDimension: ");
        System.out.println("1. 2D");
        System.out.println("2. 3D");
        int dimChoice = in.nextInt();

        if (dimChoice == 1){
            newMovie.setMovieDims(dimension.valueOf("TwoD"));
        }else{
            newMovie.setMovieDims(dimension.valueOf("ThreeD"));
        }
        System.out.println("Movie Rating: ");
        newMovie.setRating(in.next());
        System.out.println("Movie Sypnosis: ");
        newMovie.setMovieSypnosis(in.next());
        System.out.println("Director: ");
        newMovie.setMovieDirector(in.next());
        newMovie.setSaleVolume(0);
        ArrayList<Movie> movieList = null;
        movieList = fileio.readMovieData();
        if(movieList == null){
          movieList = new ArrayList<Movie>();
        }
        movieList.add(newMovie);
        fileio.writeMovieData(movieList);

    }

    public static String updateMovie() throws Exception{
        ArrayList<Movie> movieList = null;
        movieList = fileio.readMovieData();
        Movie movieToUpdate = null;
        Scanner in = new Scanner(System.in);
        if(movieList == null){
          System.out.println("There is no movie available.");
          fileio.writeMovieData(movieList);
          return null;
        }
        else{
          System.out.println("Enter title of movie to be updated: ");
          String movieName = in.next();
          int found = 0;
          for (int i = 0; i < movieList.size(); i++) {
              if(movieList.get(i).getMovieTitle().equals(movieName)){
                  movieToUpdate = movieList.get(i);
                  found = 1;
                  break;
              }
          }
          if (found == 0){
              System.out.println("No such movie exists!");
              fileio.writeMovieData(movieList);
              return null;
          }
          else{
              System.out.println("Current movie status: "+ movieToUpdate.getMovieStatus());
              String currentMovieStatus = movieToUpdate.getMovieStatus();
              switch (currentMovieStatus){
                  case "Coming_Soon":
                  movieToUpdate.setMovieStatus(status.Preview);
                  break;
                  case "Preview":
                  movieToUpdate.setMovieStatus((status.Now_Showing));
                  break;
                  case "Now_Showing":
                  movieToUpdate.setMovieStatus(status.End_Of_Showing);
              }
              System.out.println("Movie status changed to "+movieToUpdate.getMovieStatus());
          }
          fileio.writeMovieData(movieList);
          if(movieToUpdate.getMovieStatus().equals("End_Of_Showing")){
            MovieScreening.removeMovieScreeningWithMovie(movieToUpdate);
          }else{
            MovieScreening.updateMovieScreeningWithMovie(movieToUpdate);
          }
          return movieToUpdate.getMovieStatus();     
          }
    }

    public static void removeMovie() throws Exception{
        ArrayList<Movie> movieList = null;
        movieList = fileio.readMovieData();
        Scanner in = new Scanner(System.in);
        if (movieList == null || movieList.size()<1){
          System.out.println("There is no movie available.");
        }
        System.out.println("Enter title of movie to be deleted: ");
        String movieName = in.next();
        int found = 0;
        for (int i = 0; i < movieList.size(); i++) {
            if(movieList.get(i).getMovieTitle().equals(movieName)){
              
                movieList.get(i).setMovieStatus(status.End_Of_Showing);
                MovieScreening.removeMovieScreeningWithMovie(movieList.get(i));
                found = 1;
                System.out.println(movieList.get(i).getMovieTitle()+" successfully deleted!");
                break;
            }
        }
        if (found == 0){
            System.out.println("No such movie exists!");
        }


        fileio.writeMovieData(movieList);
    }



    public static void showMovieList() throws Exception{
      ArrayList<Movie> movieList = null;
      movieList = fileio.readMovieData();
      for (int index = 0; index < movieList.size(); index++) {
        if(!movieList.get(index).getMovieStatus().equalsIgnoreCase("End_Of_Showing")){
          System.out.println(index+1 +". "+ movieList.get(index).getMovieTitle());
          System.out.println("Status: "+movieList.get(index).getMovieStatus());
        }
           }
      fileio.writeMovieData(movieList);
    }
// sort the ArrayList of movies based on user input and print the top 5 movies based on 
//(1) by rating, (2) sales
    public static void sortMovie()throws Exception{
      Scanner input = new Scanner(System.in);
      int choice = 0;
      ArrayList<Movie> movieList= fileio.readMovieData(); 
      do{
        System.out.println("View top 5 movies");
        System.out.println("1. By Ratings");
        System.out.println("2. By Sales Volume");
        choice = input.nextInt();
      }
      while(choice<1 || choice >2);
      if (choice == 1){
        Collections.sort(movieList, Movie.movieRatingComparator);
      }
      else{
        Collections.sort(movieList, Movie.movieSalesComparator);
      }
      for (int index = 0; index < 5; index++) {
        if(!movieList.get(index).getMovieStatus().equalsIgnoreCase("End_Of_Showing")){
          System.out.println(index+1 +". "+ movieList.get(index).getMovieTitle());
          System.out.println("Status: "+movieList.get(index).getMovieStatus());
        }
      }
      fileio.writeMovieData(movieList);
    }
    // Rate movie based on user input movie title
    public static void userRate(String movieTitle) throws Exception{
      Scanner input =new Scanner(System.in);
      ArrayList<Movie> movieList= fileio.readMovieData(); 
      for (int i = 0; i < movieList.size(); i++) {
        if(movieList.get(i).getMovieTitle().equalsIgnoreCase(movieTitle)){
          System.out.println("Input rating(1 - 5[best]) : ");
          movieList.get(i).setMovieRating(input.nextInt());
          MovieScreening.updateMovieScreeningWithMovie(movieList.get(i));
          break;
        }
      }
      fileio.writeMovieData(movieList);
    }
}
