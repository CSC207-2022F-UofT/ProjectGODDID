package useCases;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;

public class ReadFile {
    String files;

    public ReadFile(String filename) {
        files = filename;
    }

    public ArrayList<String> ReadFiles() {
        ArrayList<String> list_of_messages = new ArrayList<String>();
        try{
        File file = new File(files);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            String data = sc.nextLine();
            list_of_messages.add(data);
        }
        sc.close();
        } catch (FileNotFoundException error){
            error.printStackTrace();
        }
        return list_of_messages;
    }
}
