package useCases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

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


    public ArrayList<String> ReadFiles(String username1, String username2) {
        ArrayList<String> list_of_messages = new ArrayList<String>();
        String s = "src/" + username1 + username2;
        String s1 = "src/" + username2 + username1;
        try{
            File file = Paths.get(s);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                String data = sc.nextLine();
                list_of_messages.add(data);
            }
            sc.close();
        }
        return list_of_messages;
    }


    public void DeleteTextFile(String username1, String username2) throws IOException {
        String s = "src/" + username1 + username2;
        String s1 = "src/" + username2 + username1;
        Files.deleteIfExists(Paths.get(s));
        Files.deleteIfExists(Paths.get(s1));
    }
}
