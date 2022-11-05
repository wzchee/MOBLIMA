import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Review{

    private int rating;
    private Movie movie;
    private String review;
    private User user;

    public static void writeReview(User sessionUser) throws Exception{
        Scanner input = new Scanner(System.in);
        
        FileInOut<Review> reviewio = new FileInOut<Review>();
        ArrayList<Review> reviewList = reviewio.readData(new Review());
        //ArrayList<Review> reviewList = fileio.readReviewData();
        ArrayList<Movie> userReviewedMovies = new ArrayList<Movie>();
        // screen through the list of reviews to get the movies that the user has reviewed before
        for(int i=0; i<reviewList.size(); i++){
            if(reviewList.get(i).getUser().equals(sessionUser))
                userReviewedMovies.add(reviewList.get(i).getMovie());
        }

        FileInOut<MovieTicket> movieticketio = new FileInOut<MovieTicket>();
        ArrayList<MovieTicket> movieTicketList = reviewio.readData(new MovieTicket());
        //ArrayList<MovieTicket> movieTicketList = fileio.readMovieTicketData();
        ArrayList<Movie> userPastMovies = new ArrayList<Movie>();
        // screen through the list of tickets to get the movies that the user has watched before
        for(int i=0; i<movieTicketList.size(); i++){
            if(movieTicketList.get(i).getUser().equals(sessionUser) &&
               movieTicketList.get(i).getMovieScreening().getMydate().isAfter(LocalDateTime.now()))
                userPastMovies.add(movieTicketList.get(i).getMovieScreening().getMovieObj());
        }

        // display the list of movies and whether the user reviewed them or not
        if(userPastMovies.isEmpty()){
            System.out.println("Based on our records, you have not watched any reviewable movies");
            System.out.println("Returning to user menu...\n");
            return;
        }
        
        System.out.println("Based on our records, here are a list of movies that you have watched:");
        for(int i=0; i<userPastMovies.size(); i++){
            System.out.print((i+1) + ". " + userPastMovies.get(i).getMovieTitle());
            if(userReviewedMovies.contains(userPastMovies.get(i)))
                System.out.print(" (Already reviewed)\n");
            else
                System.out.print("\n");
        }
        System.out.println("-1: Return to User Menu");
        System.out.print("Enter the number corresponding to the movie you would like to review: ");
        int movieNum;
        try{
            movieNum = input.nextInt();
        } catch(InputMismatchException e){
            System.out.println("Wrong input. Please try again.");
            System.out.println("Returning to user menu...\n");
            return;
        }

        if(movieNum == -1){
            System.out.println("Returning to user menu...\n");
            return;
        }
        Movie movieChosen = userPastMovies.get(movieNum-1);

        // allow user to edit their reviews. If selected movie has been reviewed before, ask if overwrite review
        if(userReviewedMovies.contains(movieChosen)){
            System.out.println("You have reviewed this movie before. Reviewing it again will overwrite your previous review.");
            System.out.println("Would you like to review this movie again (Y/N)?");
            String yesno = input.next();

            if(!yesno.equals("Y")){
                System.out.println("Review cancelled.");
                System.out.println("Returning to user menu...\n");
            }
        }

        System.out.print("Please give a rating out of 10: ");
        int movieRating = input.nextInt();

        System.out.println("Please type in your review in full below:");
        String movieReview = input.next();

        if(userReviewedMovies.contains(movieChosen)){
            // overwrite existing review
            reviewList.get(movieNum-1).setRating(movieRating);
            reviewList.get(movieNum-1).setReview(movieReview);
        } else {
            // submit new review
            reviewList.add(new Review(movieRating, movieChosen, movieReview, sessionUser));
        }
        reviewio.writeData(reviewList, new Review());
        //fileio.writeReviewData(reviewList);

        System.out.println("Your review has been recorded. Thank you for reviewing.");
        System.out.println("Returning to user menu...\n");
    }
    
    public static void viewReview(Movie moviechosen) throws Exception{
        FileInOut<Review> reviewio = new FileInOut<Review>();
        ArrayList<Review> reviewList = reviewio.readData(new Review());
        //ArrayList<Review> reviewList = fileio.readReviewData();
        
        System.out.println("Here are the reviews for this movie:-");

        // display the review for that movie
        int reviewcount = 0;
        for(int i=0; i<reviewList.size(); i++){
            if(reviewList.get(i).getMovie().equals(moviechosen)){
                reviewcount++;
                System.out.println(reviewcount + ". Reviewed by user " + reviewlist.get(i).getUser().getName());
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