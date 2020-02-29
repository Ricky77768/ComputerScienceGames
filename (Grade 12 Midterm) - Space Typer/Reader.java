import java.util.Scanner;
import java.io.InputStream;

/**
* A reader for files that you want to use within a Greenfoot project.
* 
* @author (Mr.Chan)
* @date (April 11, 2019)
*/
public class Reader 
{
    /**
    * Opens a text file inside the package folder and returns a scanner to
    * read it. This works for text files inside jar files.
    * 
    * @param name The name of the text file
    * @return A Scanner object that is used to read the contents of the text   
    *  file.
    */
    public Scanner getScanner(String filename){
        InputStream myFile = getClass().getResourceAsStream(filename);
        if(myFile != null){
            return new Scanner(myFile);
        }
        return null;
    }
}
