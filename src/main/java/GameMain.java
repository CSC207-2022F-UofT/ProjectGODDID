
import UI.ChatUI;
import UI.GameUI;
import UI.LoginPage;
import entities.User;


import java.io.IOException;

public class GameMain {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        User user1 = new User("mert", "casual");
        User user2 = new User("manit", "casual");

        GameUI game = new GameUI(user1, user2);


        LoginPage loginPage = new LoginPage();


    }



}