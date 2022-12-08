package Databases;

import Interfaces.CreateChatInt;

import java.io.File;
import java.io.IOException;

/**
 * Class to create a new text file which contains the chat between the main user and the matched user.
 *
 * @author Arian Khademi
 * @version 1.0
 * @since December 5th, 2022
 */
public class CreateChatText implements CreateChatInt {
    /**
     *
     * @param name1 the username of the main user (who is accessing the UI)
     * @param name2 the username of the user they have been matched with
     * @throws IOException if creating the new file fails
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
