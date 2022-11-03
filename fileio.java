import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class fileio {

    //filename naming convention [className][Data].txt e.g movieScreeningData.txt

    private static String getDir (String filename){                        //Change Function Name
        String currentDirectory;
        currentDirectory = System.getProperty("user.dir");
        String toReturn = "";

        String myOs = System.getProperty("os.name").toLowerCase();
        if (myOs.indexOf("win") >= 0) {
            String[] myArr = currentDirectory.split("\\\\");
            for(int i =0;i<myArr.length;i++){
                toReturn+= (myArr[i] + "\\\\");
            }
        } else if (myOs.indexOf("mac") >= 0) {
            toReturn = currentDirectory + "/";
        } 

        
        toReturn+=filename;                            //Change to right txt file
        return toReturn;
    }

    public static ArrayList<MovieScreening> readMovieScreeningData() throws Exception{                      //change function name and return type generics
        String txtdir = fileio.getDir("movieScreeningData.txt");                                                      //call the right Dir() method
        ArrayList<MovieScreening> mylist = new ArrayList<MovieScreening>();
    
        try
        {
            FileInputStream fileIn = new FileInputStream(txtdir);// Read serial file.
            ObjectInputStream in = new ObjectInputStream(fileIn);// input the read file.
            mylist = (ArrayList)in.readObject();// allocate it to the object file already instanciated.
            in.close();//closes the input stream.
            fileIn.close();//closes the file data stream.
        }
        catch(IOException i)//exception stuff
        {
            i.printStackTrace();
            return null;
        }

        return mylist;
    }

    public static void writeMovieScreeningData(ArrayList<MovieScreening> mylist) throws Exception{              //Change function name and parameter generics
       FileOutputStream fileOut = new FileOutputStream("movieScreeningData.txt");         // Change txt file name
       ObjectOutputStream out = new ObjectOutputStream(fileOut);
       out.writeObject(mylist);
       out.close();
       fileOut.close();
    }
    
    public static ArrayList<Movie> readMovieData() throws Exception{                      //change function name and return type generics
        String txtdir = fileio.getDir("movieData.txt");                                                      //call the right Dir() method
        ArrayList<Movie> mylist = new ArrayList<Movie>();
    
        try
        {
            FileInputStream fileIn = new FileInputStream(txtdir);// Read serial file.
            ObjectInputStream in = new ObjectInputStream(fileIn);// input the read file.
            mylist = (ArrayList)in.readObject();// allocate it to the object file already instanciated.
            in.close();//closes the input stream.
            fileIn.close();//closes the file data stream.
        }
        catch(IOException i)//exception stuff
        {
            i.printStackTrace();
            return null;
        }

        return mylist;
    }

    public static void writeMovieData(ArrayList<Movie> mylist) throws Exception{              //Change function name and parameter generics
       FileOutputStream fileOut = new FileOutputStream("movieData.txt");         // Change txt file name
       ObjectOutputStream out = new ObjectOutputStream(fileOut);
       out.writeObject(mylist);
       out.close();
       fileOut.close();
    }

    public static ArrayList<Cinema> readCinemaData() throws Exception{                      //change function name and return type generics
        String txtdir = fileio.getDir("cinemaData.txt");                                                      //call the right Dir() method
        ArrayList<Cinema> mylist = new ArrayList<Cinema>();
    
        try
        {
            FileInputStream fileIn = new FileInputStream(txtdir);// Read serial file.
            ObjectInputStream in = new ObjectInputStream(fileIn);// input the read file.
            mylist = (ArrayList)in.readObject();// allocate it to the object file already instanciated.
            in.close();//closes the input stream.
            fileIn.close();//closes the file data stream.
        }
        catch(IOException i)//exception stuff
        {
            i.printStackTrace();
            return null;
        }

        return mylist;
    }

    public static void writeCinemaData(ArrayList<Cinema> mylist) throws Exception{              //Change function name and parameter generics
       FileOutputStream fileOut = new FileOutputStream("cinemaData.txt");         // Change txt file name
       ObjectOutputStream out = new ObjectOutputStream(fileOut);
       out.writeObject(mylist);
       out.close();
       fileOut.close();
    }

    public static ArrayList<MovieTicket> readMovieTicketData() throws Exception{                      //change function name and return type generics
        String txtdir = fileio.getDir("movieTicketData.txt");                                                      //call the right Dir() method
        ArrayList<MovieTicket> mylist = new ArrayList<MovieTicket>();
    
        try
        {
            FileInputStream fileIn = new FileInputStream(txtdir);// Read serial file.
            ObjectInputStream in = new ObjectInputStream(fileIn);// input the read file.
            mylist = (ArrayList)in.readObject();// allocate it to the object file already instanciated.
            in.close();//closes the input stream.
            fileIn.close();//closes the file data stream.
        }
        catch(IOException i)//exception stuff
        {
            i.printStackTrace();
            return null;
        }

        return mylist;
    }

    public static void writeMovieTicketData(ArrayList<MovieTicket> mylist) throws Exception{              //Change function name and parameter generics
       FileOutputStream fileOut = new FileOutputStream("movieTicketData.txt");         // Change txt file name
       ObjectOutputStream out = new ObjectOutputStream(fileOut);
       out.writeObject(mylist);
       out.close();
       fileOut.close();
    }
        
    public static ArrayList<Cineplex> readCineplexData() throws Exception{                      //change function name and return type generics
        String txtdir = fileio.getDir("cineplexData.txt");                                                      //call the right Dir() method
        ArrayList<Cineplex> mylist = new ArrayList<Cineplex>();
    
        try
        {
            FileInputStream fileIn = new FileInputStream(txtdir);// Read serial file.
            ObjectInputStream in = new ObjectInputStream(fileIn);// input the read file.
            mylist = (ArrayList) in.readObject();// allocate it to the object file already instanciated.
            in.close();//closes the input stream.
            fileIn.close();//closes the file data stream.
        }
        catch(IOException i)//exception stuff
        {
            i.printStackTrace();
            return null;
        }

        return mylist;
    }

    public static void writeCineplexData(ArrayList<Cineplex> mylist) throws Exception{              //Change function name and parameter generics
       FileOutputStream fileOut = new FileOutputStream("cineplexData.txt");         // Change txt file name
       ObjectOutputStream out = new ObjectOutputStream(fileOut);
       out.writeObject(mylist);
       out.close();
       fileOut.close();
    }

    public static ArrayList<User> readUserData() throws Exception{                      //change function name and return type generics
        String txtdir = fileio.getDir("userData.txt");                                                      //call the right Dir() method
        ArrayList<Cineplex> mylist = new ArrayList<Cineplex>();
    
        try
        {
            FileInputStream fileIn = new FileInputStream(txtdir);// Read serial file.
            ObjectInputStream in = new ObjectInputStream(fileIn);// input the read file.
            mylist = (ArrayList) in.readObject();// allocate it to the object file already instanciated.
            in.close();//closes the input stream.
            fileIn.close();//closes the file data stream.
        }
        catch(IOException i)//exception stuff
        {
            i.printStackTrace();
            return null;
        }

        return mylist;
    }

    public static void writeUserData(ArrayList<User> mylist) throws Exception{              //Change function name and parameter generics
       FileOutputStream fileOut = new FileOutputStream("userData.txt");         // Change txt file name
       ObjectOutputStream out = new ObjectOutputStream(fileOut);
       out.writeObject(mylist);
       out.close();
       fileOut.close();
    }

    public static ArrayList<Staff> readStaffData() throws Exception{                      //change function name and return type generics
        String txtdir = fileio.getDir("staffData.txt");                                                      //call the right Dir() method
        ArrayList<Cineplex> mylist = new ArrayList<Cineplex>();
    
        try
        {
            FileInputStream fileIn = new FileInputStream(txtdir);// Read serial file.
            ObjectInputStream in = new ObjectInputStream(fileIn);// input the read file.
            mylist = (ArrayList) in.readObject();// allocate it to the object file already instanciated.
            in.close();//closes the input stream.
            fileIn.close();//closes the file data stream.
        }
        catch(IOException i)//exception stuff
        {
            i.printStackTrace();
            return null;
        }

        return mylist;
    }

    public static void writeStaffData(ArrayList<Staff> mylist) throws Exception{              //Change function name and parameter generics
       FileOutputStream fileOut = new FileOutputStream("staffData.txt");         // Change txt file name
       ObjectOutputStream out = new ObjectOutputStream(fileOut);
       out.writeObject(mylist);
       out.close();
       fileOut.close();

}
