import java.util.Scanner;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Java object to store details of one review for one movie by one user.
 * @author  Chee Wen Zhan
 * @version 1.0
 * @since   2022-8-11
 */
public class Review implements Serializable{

    /**
     * Allows current User to write one review for one movie
     * <p>
     * Contains a User Interface that first asks the user for the movie
     * they would like to review. This method collects the rating and the
     * worded review of the user.
     * <p>
     * Each movie can only be reviewed once by the user. This method
     * allows the user to change their existing review by selecting the 
     * same movie to review
     * @param   sessionUser   Current User object logged into the system
     * @see     Movie
     * @throws  Exception
     */
    public static void writeReview(User sessionUser) throws Exception{
        FileInOut<Movie> movieio = new FileInOut<Movie>();
        ArrayList<Movie> movieList = movieio.readData(new Movie());
        FileInOut<Review> reviewio = new FileInOut<Review>();
        ArrayList<Review> reviewList = reviewio.readData(new Review());


        Scanner in = new Scanner(System.in);

        String choice = null;
        System.out.println("Please choose from the movie listings to review");
        for(int i=0;i<movieList.size();i++){
            System.out.println((i+1) + ". " + movieList.get(i).getMovieTitle());
        }
        System.out.println("Enter you choice of movie: ");
        choice = in.nextLine();

        Movie movieToBeReviewed = movieList.get(Integer.parseInt(choice)-1);

        Review reviewToBeChanged = null;
        for(int i=0;i<reviewList.size();i++){
            if(reviewList.get(i).getMovie().getMovieTitle().equals(movieToBeReviewed.getMovieTitle()) && reviewList.get(i).getUser().getEmail().equals(sessionUser.getEmail())){
                reviewToBeChanged = reviewList.get(i);
            }
        }

        if (reviewToBeChanged == null){
            System.out.print("Please give a rating out of 5: ");
            int movieRating = Integer.parseInt(in.nextLine());

            System.out.println("Please type in your review in full below:");
            String movieReview = in.nextLine();

            Review reviewToAdd = new Review(movieRating, movieToBeReviewed, movieReview, sessionUser);
            Movie.addReview(reviewToAdd);
            reviewList.add(reviewToAdd);
        }else{
            String yesnochoice = null;
            System.out.println("You have reviewed this movie before");
            System.out.println("Do you want to update it? [y/n]");
            yesnochoice = in.nextLine();
            if(yesnochoice.equalsIgnoreCase("y")){
                
                String oldReview = reviewToBeChanged.getReview();
                int oldRating = reviewToBeChanged.getRating();
                
                System.out.print("Please give a rating out of 5: ");
                int newmovieRating = Integer.parseInt(in.nextLine());
    
                System.out.println("Please type in your review in full below:");
                String newmovieReview = in.nextLine();

                reviewToBeChanged.setRating(newmovieRating);
                reviewToBeChanged.setReview(newmovieReview);


                Movie.updateReviews(oldReview, oldRating, reviewToBeChanged);

            }else{
                System.out.println("Returning you back to main page");
                return;
            }
        }
        reviewio.writeData(reviewList, new Review());
    }
    
    /**
     * Method to show the existing reviews of a movie. This method is called
     * when the user wishes to see details of that movie.
     * <p>
     * Contains a User Interface that returns the list of ratings and reviews
     * for the movie chosen by the user. Also show who made the review by
     * returning the name of the user who made that review.
     * @param   moviechosen   Movie to check the reviews for
     * @see     Staff#loggedin(String)
     * @see     Movie
     * @throws  Exception
     */
    public static void viewReview(Movie moviechosen) throws Exception{
        FileInOut<Review> reviewio = new FileInOut<Review>();
        ArrayList<Review> reviewList = reviewio.readData(new Review());
        
        System.out.println("Here are the reviews for this movie:-");

        // display the review for that movie
        int reviewcount = 0;
        for(int i=0; i<reviewList.size(); i++){
            if(reviewList.get(i).getMovie().equals(moviechosen)){
                reviewcount++;
                System.out.println(reviewcount + ". Reviewed by user " + reviewList.get(i).getUser().getName());
                System.out.println("Rating given: " + reviewList.get(i).getRating());
                System.out.println(reviewList.get(i).getReview() + "\n");
            }
        }
        
    }

    /**
     * Constructor that initializes all attributes for a Review instance
     * @param rating    Rating out of 5 given by the user for that movie
     * @param movie     Movie that this review is made for
     * @param review    A description of what the user thinks and feels about the movie
     * @param user      The User object that wrote this review
     */
    public Review(int rating, Movie movie, String review, User user){
        this.rating = rating;
        this.movie = movie;
        this.review = review;
        this.user = user;
    }

    public Review(){

    }

    /**
     * Rating out of 5 given by the user for the specific movie
     */
    private int rating;
    /**
     * Retrieve the rating of this specific review
     * @return Rating of the review
     */
    public int getRating(){
        return rating;
    }
    /**
     * Set the new rating of this specific review
     * @param rating New rating of the review
     */
    public void setRating(int rating){
        this.rating = rating;
    }

    /**
     * Movie reviewed by the user
     * @see Movie
     */
    private Movie movie;
    /**
     * Retrieve the movie that this review is made for
     * @return Movie targetted by this review
     */
    public Movie getMovie(){
        return movie;
    }
    /**
     * Set the new movie that this review is made for
     * @param movie New movie targetted by this review
     */
    public void setMovie(Movie movie){
        this.movie = movie;
    }

    /**
     * A description of what the user thinks and feels about the movie
     */
    private String review;
    /**
     * Retrieves the worded review of the user for this movie
     * @return The review of the user for this movie
     */
    public String getReview(){
        return review;
    }
    /**
     * Sets the new worded review of the user for this movie
     * @param review The new review of the user for this movie
     */
    public void setReview(String review){
        this.review = review;
    }

    /**
     * The User object who wrote this review
     * @see User
     */
    private User user;
    /**
     * Retrieves the User object who wrote this review
     * @return  User instance for this review
     */
    public User getUser(){
        return user;
    }
    /**
     * Sets the new User object for this review
     * @param user New User object for this review
     */
    public void setUser(User user){
        this.user = user;
    }
}