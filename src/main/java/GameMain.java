

import UI.GameUI;

import entities.User;



import java.io.IOException;

public class GameMain {

    /**
     * @param args To test the game manually
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        User user1 = new User("mert");
        User user2 = new User("manit");

        GameUI game = new GameUI(user1, user2);


    }



}