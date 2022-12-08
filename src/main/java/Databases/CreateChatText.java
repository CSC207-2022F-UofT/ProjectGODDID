package Databases;

import Interfaces.CreateChatInt;

import java.io.File;
import java.io.IOException;

/**
 *
 */
public class CreateChatText implements CreateChatInt {
    /**
     *
     * @param name1 first user
     * @param name2 second user
     * @throws IOException
     * Starts a new chat between two users
     */
    @Override
    public void newChat(String name1, String name2) throws IOException {
        if (!new File(name1).exists() && !new File(name2).exists()){
            File f = new File(name1);
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            } else {
                System.out.println("File already exists.");
            }
        }
    }
}