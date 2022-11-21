package useCases;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

public class CreateTextFile {
    public static void main(String[] args) {
        try {
            File file = new File("file1.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
            else {
                System.out.println("File already exists.");
            }
        }
        catch (IOException error){
        System.out.println("An error has occurred.");
            error.printStackTrace();
        }
    }
}
