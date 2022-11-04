import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Review{

    private double rating;
    private Movie movie;
    private String review;
    private User user;

    public static void writeReview(User sessionUser) throws Exception{
        Scanner input = new Scanner(System.in);
        
        ArrayList<Review> reviewList = fileio.readReviewData();
        ArrayList<Movie> userReviewedMovies  = null;
        // screen through the list of reviews to get the movies that the user has reviewed before
        for(int i=0; i<reviewList.size(); i++){
            if(reviewList.get(i).getUser().equals(sessionUser))
                userReviewedMovies.add(reviewList.get(i).getMovie());
        }

        ArrayList<MovieTicket> movieTicketList = fileio.readMovieTicketData();
        ArrayList<Movie> userPastMovies = null;
        // screen through the list of tickets to get the movies that the user has watched before
        for(int i=0; i<movieTicketList.get(i).size(); i++){
            if(movieTicketList.get(i).getUser().equals(sessionUser) &&
               movieTicketList.get(i).getMovieScreening().getMydate().isAfter(LocalDateTime.now()))
                userPastMovies.add(movieTicketList.get(i).getMovieScreening().getMovieObj());
        }

        // display the list of movies and whether the user reviewed them or not
        if(userPastMovies.isEmpty()){
            System.out.println("Base on our records, you have not watched any reviewable movies");
            System.out.println("Returning to main menu...\n");
            break;
        }
        System.out.println("Here is a list of movies that you have watched.");
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
    public void setReview(String review){this.review = review;}

    public User getUser(){return user;}
    public void setUser(User user){this.user = user;}
}

// WZ's to-do
// add time attribute
// add validatebooking method