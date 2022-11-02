    public void createMovie(){
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


    }

    public void updateMovie(){

    }

    public void removeMovie(){

    }