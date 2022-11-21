package useCases;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class AddToTextFile{
    public AddToTextFile(String s, String username){
        try {
            FileWriter writer = new FileWriter("file1.txt");
            writer.write(username + ": " + s);
            writer.close();
            }
        catch (IOException error){
            error.printStackTrace();
            }
    }
}
