import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Developer {

    public static void Initializer() throws Exception{
        // iniitialize everything
        FileInOut<Cineplex> cineplexinout = new FileInOut<Cineplex>();
        ArrayList<Cineplex> myCineplexList = cineplexinout.readData(new Cineplex());
        //ArrayList<Cineplex> myCineplexList = fileio.readCineplexData();
        if (myCineplexList.isEmpty()) {
            myCineplexList.add(new Cineplex("Cathay Cineplex AMK HUB"));
            myCineplexList.add(new Cineplex("Cathay Cineplex JEM"));
            myCineplexList.add(new Cineplex("Cathay Cineplex Cineleisure"));
            // overwrite the file
            cineplexinout.writeData(myCineplexList, new Cineplex());
            //fileio.writeCineplexData(myCineplexList);
        }
        // FileInOut<Cineplex> cineplexinout = new FileInOut<Cineplex>();
        // ArrayList<Cineplex> myCineplexList = cineplexinout.readData(new Cineplex());
        // cineplexinout.writeData(myCineplexList, new Cineplex());

        FileInOut<Cinema> cinemainout = new FileInOut<Cinema>();
        ArrayList<Cinema> myCinemaList = cinemainout.readData(new Cinema());
        //ArrayList<Cinema> myCinemaList = fileio.readCinemaData();
        if (myCinemaList.isEmpty()) {
            myCinemaList.add(new Cinema("Standard 1", myCineplexList.get(0), false, 100, "AS1"));
            myCinemaList.add(new Cinema("Standard 2", myCineplexList.get(0), false, 100, "AS2"));
            myCinemaList.add(new Cinema("Standard 3", myCineplexList.get(0), false, 100, "AS3"));
            myCinemaList.add(new Cinema("Platinum Movie Suites", myCineplexList.get(1), true, 100, "JP1"));
            myCinemaList.add(new Cinema("Standard 1", myCineplexList.get(1), false, 100, "JS1"));
            myCinemaList.add(new Cinema("Standard 2", myCineplexList.get(1), false, 100, "JS2"));
            myCinemaList.add(new Cinema("Standard 1" ,myCineplexList.get(2), false, 100, "CS1"));
            myCinemaList.add(new Cinema("Standard 2" ,myCineplexList.get(2), false, 100, "CS2"));
            myCinemaList.add(new Cinema("Standard 3" ,myCineplexList.get(2), false, 100, "CS3"));
            // overwrite the file
            cinemainout.writeData(myCinemaList, new Cinema());
            //fileio.writeCinemaData(myCinemaList);
        }

        FileInOut<User> userinout = new FileInOut<User>();
        ArrayList<User> userList = userinout.readData(new User());
        //ArrayList<User> userList = fileio.readUserData();
        if (userList.isEmpty()) {
            userList.add(new User("wz@email.com", "wz", 20, "Wen Zhan", "81234567"));
            userList.add(new User("oliver@email.com", "oliver", 20, "Oliver Low", "81234568"));
            userList.add(new User("bernard@email.com", "bernard", 20, "Bernard", "81234569"));
            userList.add(new User("jiarong@email.com", "jiarong", 20, "Jia Rong", "812345675"));
            userList.add(new User("kid@email.com", "kid", 2, "Rong", "812345675"));
            // overwrite the file
            userinout.writeData(userList, new User());
            //fileio.writeUserData(userList);
        }

        FileInOut<Staff> staffinout = new FileInOut<Staff>();
        ArrayList<Staff> staffList = staffinout.readData(new Staff());
        //ArrayList<Staff> staffList = fileio.readStaffData();
        if (staffList.isEmpty()) {
            staffList.add(new Staff("admin@admin.com", "admin", "ADMIN", myCineplexList.get(0)));
            // overwrite the file
            staffinout.writeData(staffList, new Staff());
            //fileio.writeUserData(userList);
        }
        
        FileInOut<Configurables> configinout = new FileInOut<Configurables>();
        ArrayList<Configurables> configList = configinout.readData(new Configurables());
        //ArrayList<Configurables> configList = fileio.readConfigurablesData();
        if (configList.isEmpty()) {
            configList.add(new Configurables(2022, 12, 15, 7.0));
            // overwrite the file
            configinout.writeData(configList, new Configurables());
            //fileio.writeUserData(configList);
        }

        FileInOut<Movie> movieinout = new FileInOut<Movie>();
        ArrayList<Movie> movieList = movieinout.readData(new Movie());
        String[] cast1 = {"Lupita Nyong'o", "Danai Gurira"};
        String[] cast2 = {"Jesse T.Usher","Sosie Bacon"};
        String[] cast3 = {"Dwayne Johnson","Aldis Hodge"};
        String[] cast4 = {"Zoe Saldana", "Sam Worthington"};
        String[] cast5 = {"Jacqueline","Virginia",};
        String[] cast6 = {"Leonardo DiCaprio", "Elliot Page"};
        String[] cast7 = {"Chris Evans", "Jamie Bell"};
        String[] cast8 = {"Scarlett Johansson ", "Florence Pugh"};
        String[] cast9 = {"Tom Cruise", "Emily Blunt"};
        String[] cast10 = {"Daniel Craig", "Javier Bardem"};
        if (movieList.isEmpty()) {
            movieList.add(new Movie("Black Panther", 161, dimension.TwoD, status.Now_Showing, false, "Queen Ramonda, Shuri, M'Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King T'Challa's death. As the Wakandans strive to embrace their next chapter, the heroes must band together with Nakia and Everett Ross to forge a new path for their beloved kingdom.", "Ryan Coogler", cast1, "PG"));
            movieList.add(new Movie("Inception", 148, dimension.TwoD, status.Now_Showing, false, "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.", "Christopher Nolan", cast6, "M18"));
            movieList.add(new Movie("Snowpiercer", 126, dimension.TwoD, status.Coming_Soon, false, "In a future where a failed climate change experiment has killed all life except for the survivors who boarded the Snowpiercer (a train that travels around the globe), a new class system emerges.", "Bong Joon Ho", cast7, "R21"));
            movieList.add(new Movie("Black Widow", 134, dimension.TwoD, status.Now_Showing, false, "A soldier fighting aliens gets to relive the same day over and over again, the day restarting every time he dies.", "Cate Shortland", cast8, "PG"));
            movieList.add(new Movie("Prey for the devil", 93,dimension.TwoD,status.Now_Showing,false,"The Roman Catholic Church combats a global rise in demonic possessions by opening a school to train priests to perform exorcisms. Although nuns are forbidden to perform this ritual, a professor recognizes Sister Ann's gifts and agrees to train her. Thrust onto the spiritual frontline, she soon finds herself in a battle for the soul of a young girl who's possessed by the same demon that tormented her own mother years earlier.","Daniel Stamm",cast5,"M18"));
            movieList.add(new Movie("Smile",115,dimension.TwoD,status.Now_Showing,false,"After witnessing a bizarre, traumatic incident involving a patient, Dr. Rose Cotter (Sosie Bacon) starts experiencing frightening occurrences that she can't explain. As an overwhelming terror begins taking over her life, Rose must confront her troubling past in order to survive and escape her horrifying new reality.","Parker Finn",cast2,"M18"));
            movieList.add(new Movie("Black Adam",125,dimension.TwoD, status.Now_Showing, false, "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods -- and imprisoned just as quickly -- Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.", "Jaume Collet- Serra", cast3, "PG"));
            movieList.add(new Movie("Avatar: The Way Of Water ",90,dimension.TwoD, status.Coming_Soon, false, "Set more than a decade after the events of the first film, “Avatar: The Way of Water” begins to tell the story of the Sully family (Jake, Neytiri, and their kids), the trouble that follows them, the lengths they go to keep each other safe, the battles they fight to stay alive, and the tragedies they endure", "James Cameron", cast4, "PG"));
            movieList.add(new Movie("Edge of Tomorrow",113,dimension.TwoD,status.Preview,false,"A soldier fighting aliens gets to relive the same day over and over again, the day restarting every time he dies.","Doug Liman",cast9,"PG"));
            movieList.add(new Movie("SkyFall",143,dimension.TwoD,status.Coming_Soon,false,"James Bond's loyalty to M is tested when her past comes back to haunt her. When MI6 comes under attack, 007 must track down and destroy the threat, no matter how personal the cost.","Sam Mendes",cast10,"PG"));
            
        }
        movieinout.writeData(movieList, new Movie());
    }

    public static void clearAllFiles(){
        Scanner input = new Scanner(System.in);

        System.out.println("!!!!! Developer mode only function");
        System.out.print("Delete all files and repopulate (Y/N)?");
        String yesno = input.nextLine();
        if(yesno.equalsIgnoreCase("Y")){
            System.out.println("All files are deleted and reinitialized.\n");

            File Stafftxt = new File("StaffData.txt");
            Stafftxt.delete();
            File Usertxt = new File("UserData.txt");
            Usertxt.delete();
            File Cinematxt = new File("CinemaData.txt");
            Cinematxt.delete();
            File Cineplextxt = new File("CineplexData.txt");
            Cineplextxt.delete();
            File Movietxt = new File("MovieData.txt");
            Movietxt.delete();
            File MovieScreeningtxt = new File("MovieScreeningData.txt");
            MovieScreeningtxt.delete();
            File MovieTickettxt = new File("MovieTicketData.txt");
            MovieTickettxt.delete();
            File Reviewtxt = new File("ReviewData.txt");
            Reviewtxt.delete();
            File Configurablestxt = new File("ConfigurablesData.txt");
            Configurablestxt.delete();
        } else{
            System.out.println("Files will remain how they were.\n");
        }
    }

    public static void peekFiles() throws Exception{
        System.out.println("!!!!! Developer mode only function");
        
        FileInOut<Staff> staffinout = new FileInOut<Staff>();
        ArrayList<Staff> staffList = staffinout.readData(new Staff());
        System.out.println("Showing the contents of Staff.java");
        for(int i=0; i<staffList.size(); i++){
            System.out.print(staffList.get(i).getEmail() + "\t");
            System.out.print(staffList.get(i).getPassword() + "\t");
            System.out.print(staffList.get(i).getName() + "\t");
            System.out.print(staffList.get(i).getWorkplace() + "\t");
        }
        System.out.println("\n");

        FileInOut<User> userinout = new FileInOut<User>();
        ArrayList<User> userList = userinout.readData(new User());
        System.out.println("Showing the contents of User.java");
        for(int i=0; i<staffList.size(); i++){
            System.out.print(userList.get(i).getEmail() + "\t");
            System.out.print(userList.get(i).getPassword() + "\t");
            System.out.print(userList.get(i).getName() + "\t");
            System.out.print(userList.get(i).getAge() + "\t");
            System.out.print(userList.get(i).getMobileNumber() + "\t");
        }
        System.out.println("\n");

        FileInOut<Cinema> cinemainout = new FileInOut<Cinema>();
        ArrayList<Cinema> cinemaList = cinemainout.readData(new Cinema());
        System.out.println("Showing the contents of Cinema.java");
        for(int i=0; i<cinemaList.size(); i++){
            System.out.print(cinemaList.get(i).getCinemaCode() + "\t");
            System.out.print(cinemaList.get(i).getCinemaName() + "\t");
            System.out.print(cinemaList.get(i).getCineplexName() + "\t");
            System.out.print(cinemaList.get(i).getNumOfSeats() + "\t");
        }
        System.out.println("\n");

        FileInOut<Cineplex> cineplexinout = new FileInOut<Cineplex>();
        ArrayList<Cineplex> cineplexList = cineplexinout.readData(new Cineplex());
        System.out.println("Showing the contents of Cineplex.java");
        for(int i=0; i<staffList.size(); i++){
            System.out.print(cineplexList.get(i).getCineplexName() + "\t");
        }
        System.out.println("\n");

        FileInOut<Movie> movieinout = new FileInOut<Movie>();
        ArrayList<Movie> movieList = movieinout.readData(new Movie());
        System.out.println("Showing the contents of Movie.java");
        for(int i=0; i<movieList.size(); i++){
            System.out.print(movieList.get(i).getMovieDims() + "\t");
            System.out.print(movieList.get(i).getMovieDirector() + "\t");
            System.out.print(movieList.get(i).getMovieRuntime() + "\t");
            System.out.print(movieList.get(i).getMovieStatus() + "\t");
            System.out.print(movieList.get(i).getMovieSypnosis() + "\t");
            System.out.print(movieList.get(i).getMovieTitle() + "\t");
            System.out.print(movieList.get(i).getRating() + "\t");
            System.out.print(movieList.get(i).getSaleVolume() + "\t");
            System.out.print(movieList.get(i).getBlockbuster() + "\t");
            System.out.print(movieList.get(i).getMovieCast() + "\t");
            System.out.print(movieList.get(i).getMovieRating() + "\t");
            System.out.print(movieList.get(i).getPastReviews() + "\t");
        }
        System.out.println("\n");

        FileInOut<MovieScreening> movieScreeninginout = new FileInOut<MovieScreening>();
        ArrayList<MovieScreening> movieScreeningList = movieScreeninginout.readData(new MovieScreening());
        System.out.println("Showing the contents of MovieScreening.java");
        for(int i=0; i<movieScreeningList.size(); i++){
            System.out.print(movieScreeningList.get(i).getNumOfOccupiedSeats() + "\t");
            System.out.print(movieScreeningList.get(i).getAvailabilityOfSeats(i) + "\t");
            System.out.print(movieScreeningList.get(i).getMovieObj() + "\t");
            System.out.print(movieScreeningList.get(i).getMovieScreeningLocation() + "\t");
            System.out.print(movieScreeningList.get(i).getMydate() + "\t");
            System.out.print(movieScreeningList.get(i).getSeatArr() + "\t");
        }
        System.out.println("\n");

        FileInOut<MovieTicket> movieTicketinout = new FileInOut<MovieTicket>();
        ArrayList<MovieTicket> movieTicketList = movieTicketinout.readData(new MovieTicket());
        System.out.println("Showing the contents of MovieTicket.java");
        for(int i=0; i<movieTicketList.size(); i++){
            System.out.print(movieTicketList.get(i).getTID() + "\t");
            System.out.print(movieTicketList.get(i).getseatNumber() + "\t");
            System.out.print(movieTicketList.get(i).getPrice() + "\t");
            System.out.print(movieTicketList.get(i).getLocation() + "\t");
            System.out.print(movieTicketList.get(i).getMovieScreening() + "\t");
            System.out.print(movieTicketList.get(i).getUser() + "\t");
        }
        System.out.println("\n");

        FileInOut<Review> reviewinout = new FileInOut<Review>();
        ArrayList<Review> reviewList = reviewinout.readData(new Review());
        System.out.println("Showing the contents of Review.java");
        for(int i=0; i<reviewList.size(); i++){
            System.out.print(reviewList.get(i).getRating() + "\t");
            System.out.print(reviewList.get(i).getReview() + "\t");
            System.out.print(reviewList.get(i).getMovie() + "\t");
            System.out.print(reviewList.get(i).getUser() + "\t");
        }
        System.out.println("\n");

        FileInOut<Configurables> configurablesinout = new FileInOut<Configurables>();
        ArrayList<Configurables> configurablesList = configurablesinout.readData(new Configurables());
        System.out.println("Showing the contents of Configurables.java");
        for(int i=0; i<staffList.size(); i++){
            System.out.print(configurablesList.get(i).getBasePrice() + "\t");
            System.out.print(configurablesList.get(i).getPublicHolidays() + "\t");
        }
        System.out.println("\n");
    }
}
