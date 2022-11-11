package database;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Java generic class that handles the reading and writing of data files
 * in this system.
 * <p>
 * Data used by objects in this system are serialized and stored as .txt 
 * files in this system.
 * <p>
 * Each class should create their own {@code FileInOut} object instance
 * before using the methods inside this class.
 * @author  Chee Wen Zhan
 * @version 1.0
 * @since   2022-8-11
 */
public class FileInOut<o>{
    
    /**
     * Concatenates and returns the raw path name of the data file requested
     * <p>
     * Uses {@code System.getProperty("user.dir")} to obtain the path name of the
     * directory running this Java program. Subsequently, concatenate this path
     * name with the filename passed into this method to return the full path
     * of the file with the file name
     * @param   filename Name of the file used for reading or writing
     * @return  Full path name of the file
     * @see     System#getProperty()
     */
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

        
        toReturn+=filename;  //Change to right txt file
        return toReturn;
    }
    
    /**
     * Reads the entire text file for the corresponding Java object
     * <p>
     * The data stored in text files are all in serialized format as one {@code ArrayList}.
     * Upon calling this method, read and return the entire {@code ArrayList} for that object.
     * <p>
     * If the text file does not exist, create the text file and attempt to read the file again.
     * In this case, an empty {@code ArrayList} of that object should be returned.
     * @param   obj Any object instance for the corresponding object to read the file from. Preferably
     * a new object instance created using the {@code new} keyword
     * @return  ArrayList of the object that is read from the file
     * @throws  Exception
     */
    public ArrayList<o> readData(o obj) throws Exception{   //change function name and return type generics
        String txtdir = getDir(obj.getClass().getName() + "Data.txt");   //call the right Dir() method
        ArrayList<o> mylist;
    
        try
        {
            FileInputStream fileIn = new FileInputStream(txtdir);// Read serial file.
            ObjectInputStream in = new ObjectInputStream(fileIn);// input the read file.
            mylist = (ArrayList) in.readObject();// allocate it to the object file already instantiated.
            in.close();//closes the input stream.
            fileIn.close();//closes the file data stream.
        }
        catch(IOException i)
        {
            // no file existing, create a new file
            File newFile = new File(txtdir);
            newFile.createNewFile();
            FileInputStream fileIn = new FileInputStream(txtdir);// Read serial file.

            // write an empty ArrayList into the file first so that ObjectInputStream reads something
            FileOutputStream fileOut = new FileOutputStream(obj.getClass().getSimpleName() + "Data.txt"); 
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(new ArrayList<o>());
            out.close();
            fileOut.close();

            ObjectInputStream in = new ObjectInputStream(fileIn);// input the read file.
            mylist = (ArrayList) in.readObject();// allocate it to the object file already instantiated.
            in.close();//closes the input stream.
            fileIn.close();//closes the file data stream.
        }

        return mylist;
    }

    /**
     * Overwrites the entire file with the {@code ArrayList} passed into this method.
     * <p>
     * The data stored in text files are all in serialized format as one {@code ArrayList}.
     * Upon calling this method, the {@code ArrayList} will first be serialized. Then, the 
     * entire text file will be replaced with the new serialized {@code ArrayList}
     * @param mylist    {@code ArrayList} of that object to replace the existing file
     * @param obj       Any object instance for the corresponding object to read the file 
     * from. Preferably a new object instance created using the {@code new} keyword
     * @throws Exception
     */
    public void writeData(ArrayList<o> mylist, o obj) throws Exception{              //Change function name and parameter generics
       FileOutputStream fileOut = new FileOutputStream(obj.getClass().getSimpleName() + "Data.txt");         // Change txt file name
       ObjectOutputStream out = new ObjectOutputStream(fileOut);
       out.writeObject(mylist);
       out.close();
       fileOut.close();
    }
}