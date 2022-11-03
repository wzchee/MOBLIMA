import java.util.*;
import java.io.Serializable;

//hello
// hihi
enum status{
   Coming_Soon,Preview,Now_Showing,End_Of_Showing
  }
enum dimension{
  TwoD,ThreeD
}
public class Movie implements Serializable{
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
public int getMovieRating(){
  return this.rating;
}
public String[] getPastReviews(){
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

public static void createMovie()throws Exception{
        Movie newMovie = new Movie();
        Scanner in = new Scanner(System.in);
        System.out.println("Movie title: ");
        newMovie.setMovieTitle(in.next());
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
        System.out.println("Movie Sypnosis: ");
        newMovie.setMovieSypnosis(in.next());
        System.out.println("Director: ");
        newMovie.setMovieDirector(in.next());
        System.out.println("Sale Volume: ");
        newMovie.setSaleVolume(in.nextInt());
        
        ArrayList<Movie> movieList = null;
        movieList = fileio.readMovieData();
        movieList.add(newMovie);
        fileio.writeMovieData(movieList);

    }

    public static String updateMovie() throws Exception{
        ArrayList<Movie> movieList = null;
        movieList = fileio.readMovieData();
        Movie movieToUpdate = null;
        Scanner in = new Scanner(System.in);
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
            System.out.println("No such movie exists");
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
        }
        fileio.writeMovieData(movieList);
        MovieScreening.updateMovieScreeningWithMovie(movieToUpdate);


        return movieToUpdate.getMovieStatus();
    }

    public static void removeMovie() throws Exception{
        ArrayList<Movie> movieList = null;
        movieList = fileio.readMovieData();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter title of movie to be deleted: ");
        String movieName = in.next();
        int found = 0;
        for (int i = 0; i < movieList.size(); i++) {
            if(movieList.get(i).getMovieTitle().equals(movieName)){
                removeMovieScreeningWithMovie(movieName);
                movieList.remove(i);
                found = 1;
                break;
            }
        }
        if (found == 0){
            System.out.println("No such movie exists");
        }


        fileio.writeMovieData(movieList);
    }
}
