package Interfaces;

import java.io.IOException;

/**
 * Interface for the CreateChatText class to implement that will be used in ChatManager to create a new text file
 * so that the code follows clean architecture.
 *
 * @author Arian Khademi
 * @version 1.0
 * @since December 5th, 2022
 */
public interface CreateChatInt {
    /**
     * Method for opening a new text file to contain the chat of the users.
     *
     * @param name1 the username of the main user who is accessing the GUI.
     * @param name2 the username of the user that has been matched to the main user to chat with them.
     * @throws IOException if creating the new file to contain the chat fails.
     */
    void newChat(String name1, String name2) throws IOException;
}
