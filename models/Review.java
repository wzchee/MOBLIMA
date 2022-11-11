package models;
import java.io.Serializable;

/**
 * Java object to store details of one review for one movie by one user.
 * @author  Chee Wen Zhan
 * @version 1.0
 * @since   2022-8-11
 */
public class Review implements Serializable{

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