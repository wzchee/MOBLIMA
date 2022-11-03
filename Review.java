import java.util.Scanner;
import java.util.ArrayList;

public class Review{

    private double rating;
    private Movie movie;
    private String review;
    private User user;

    public static void writeReview(User sessionUser) throws Exception{
        Scanner input = new Scanner(System.in);

        // check if user has watched the movie or not??
        System.out.println("Here is a list of movies.");
        ArrayList<Movie> movielist = fileio.readMovieData();
        for(int i=0; i<movielist.size(); i++){
            System.out.print(i+1);
            System.out.print(". " + movielist.get(i).getMovieTitle());
        }
        System.out.println("Enter the number of the corresponding movie: ");
        int choice = input.nextInt();
        Movie moviechosen = movielist.get(choice-1);

        System.out.print("Please give a rating out of 10: ");
        int movierating = input.nextInt();

        System.out.println("Please type in your review in full below:");
        String moviereview = input.next();

        ArrayList<Review> reviewlist = fileio.readReviewData();
        reviewlist.add(new Review(movierating, moviechosen, moviereview));
        fileio.writeReviewData(reviewlist);

        System.out.println("Your review has been recorded. Thank you for reviewing.");
        System.out.println("Returning to user menu...");
    }
    
    public static void viewReview(User sessionUser, Movie moviechosen) throws Exception{
        ArrayList<Review> reviewlist = fileio.readReviewData();
        
        System.out.println("Here are the reviews for this movie:-");

        // display the review for that movie
        int reviewcount = 0;
        for(int i=0; i<reviewlist.size(); i++){
            if(reviewlist.get(i).getMovie() == moviechosen){
                reviewcount++;
                System.out.println(reviewcount + ". Reviewed by user " + sessionUser.getName());
                System.out.println("Rating given: " + reviewlist.get(i).getRating());
                System.out.println(reviewlist.get(i).getReview() + "\n");
            }
        }
        
    }

    public Review(double rating, Movie movie, String review){
        this.rating = rating;
        this.movie = movie;
        this.review = review;
    }

    public double getRating(){return rating;}
    public void setRating(double rating){this.rating = rating;}

    public Movie getMovie(){return movie;}
    public void setMovie(Movie movie){this.movie = movie;}

    public String getReview(){return review;}
    public void setReview(String review){this.review = review;0
    
    }
}