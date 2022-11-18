package useCases;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChatTextFiles {

    public ChatTextFiles(){

    }
    public void AddToTextFile(String s, String username){
        try {
            FileWriter writer = new FileWriter("file1.txt");
            writer.write(username + ": " + s);
            writer.close();
        }
        catch (IOException error){
            error.printStackTrace();
        }
    }

    public void CreateTextFile(String username1, String username2) throws IOException {
        String s = "src/" + username1 + username2;
        File file = new File(s);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
            else {
                System.out.println("File already exists.");
            }

    }


    public void DeleteTextFile(String username1, String username2) throws IOException {
        String s = "src/" + username1 + username2;
        String s1 = "src/" + username2 + username1;
        Files.deleteIfExists(Paths.get(s));
        Files.deleteIfExists(Paths.get(s1));
    }
}
