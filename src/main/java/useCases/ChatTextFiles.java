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
    public void AddToTextFile(String messages, String username1, String username2, String usernameactiveuser){
        String s = "src/" + username1 + username2 + ".txt";
        String s1 = "src/" + username2 + username1 + ".txt";
        File f = new File(s);
        if (f.exists()) {
            try {
                FileWriter writer = new FileWriter(s);
                writer.write(usernameactiveuser + ": " + messages);
                writer.close();
            } catch (IOException error) {
                error.printStackTrace();
            }
        }
        else{
            try {
                FileWriter writer = new FileWriter(s1);
                writer.write(usernameactiveuser + ": " + messages);
                writer.close();
            } catch (IOException error) {
                error.printStackTrace();
            }
        }
    }

    public void CreateTextFile(String username1, String username2) throws IOException {
        String s = "src/" + username1 + username2 + ".txt";
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
        String s = "src/" + username1 + username2 + ".txt";
        String s1 = "src/" + username2 + username1 + ".txt";
        File f = new File(s);
        if (f.exists()) {
            try {
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    String data = sc.nextLine();
                    list_of_messages.add(data);
                }
                sc.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            try {
                File file = new File(s1);
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String data = sc.nextLine();
                    list_of_messages.add(data);
                }
                sc.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return list_of_messages;
    }




    public void DeleteTextFile(String username1, String username2) throws IOException {
        String s = "src/" + username1 + username2 + ".txt";
        String s1 = "src/" + username2 + username1 + ".txt";
        Files.deleteIfExists(Paths.get(s));
        Files.deleteIfExists(Paths.get(s1));
    }
}
