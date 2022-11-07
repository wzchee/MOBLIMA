package Models;
import java.util.Scanner;

import FileInOut;

import java.util.ArrayList;
import java.io.Serializable;

public class Review implements Serializable{

    private int rating;
    private Movie movie;
    private String review;
    private User user;

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


                Movie.updateReviews2(oldReview, oldRating, reviewToBeChanged);

            }else{
                System.out.println("Returning you back to main page");
                return;
            }
        }
        reviewio.writeData(reviewList, new Review());
    }
    
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

    public Review(int rating, Movie movie, String review, User user){
        this.rating = rating;
        this.movie = movie;
        this.review = review;
        this.user = user;
    }

    public Review(){

    }

    public int getRating(){return rating;}
    public void setRating(int rating){this.rating = rating;}

    public Movie getMovie(){return movie;}
    public void setMovie(Movie movie){this.movie = movie;}

    public String getReview(){return review;}
    public void setReview(String review){this.review = review;}

    public User getUser(){return user;}
    public void setUser(User user){this.user = user;}
}