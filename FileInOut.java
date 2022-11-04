import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileInOut<o>{
    
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

    public void writeData(ArrayList<o> mylist, o obj) throws Exception{              //Change function name and parameter generics
       FileOutputStream fileOut = new FileOutputStream(obj.getClass().getSimpleName() + "Data.txt");         // Change txt file name
       ObjectOutputStream out = new ObjectOutputStream(fileOut);
       out.writeObject(mylist);
       out.close();
       fileOut.close();
    }
}