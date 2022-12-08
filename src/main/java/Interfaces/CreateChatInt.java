package Interfaces;

import java.io.IOException;

/**
 *
 */
public interface CreateChatInt {
    /**
     *
     * @param name1 first user
     * @param name2 second user
     * @throws IOException
     *
     * An interface for creating a new chat
     */
    void newChat(String name1, String name2) throws IOException;
}