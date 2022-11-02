import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class fileio {
    public static String getMovieScreeningDir(){                        //Change Function Name
        String currentDirectory;
        currentDirectory = System.getProperty("user.dir");
        String[] myArr = currentDirectory.split("\\\\");
        String toReturn = "";
        for(int i =0;i<myArr.length;i++){
            toReturn+= (myArr[i] + "\\\\");
        }
        toReturn+="movieScreeningText.txt";                            //Change to right txt file
        return toReturn;
    }

    public static ArrayList<MovieScreening> readMovieScreeningData() throws Exception{                      //change function name and return type generics
        String txtdir = fileio.getMovieScreeningDir();                                                      //call the right Dir() method
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

    public static void writeMovieScreeningData(ArrayList<MovieScreening> mylist){              //Change function name and parameter generics
       FileOutputStream fileOut = new FileOutputStream("movieScreeningText.txt");         // Change txt file name
       ObjectOutputStream out = new ObjectOutputStream(fileOut);
       out.writeObject(mylist);
       out.close();
       fileOut.close();
    }
    
        




}
