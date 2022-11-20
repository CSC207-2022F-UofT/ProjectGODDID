
import UI.GameUI;
import entities.User;


import java.io.IOException;

public class GameMain {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        User user1 = new User("mert", "casual");
        User user2 = new User("manit", "casual");

        new GameUI(user1, user2);
    }
}