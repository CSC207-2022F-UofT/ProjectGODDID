package useCases;

import UI.ChatScreen;
import entities.User;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ChatManager {
    // Declaring instance attributes
    User mainUser;
    User matchedUser;

    // Constructor w/ 1 parameter
    public ChatManager(User mainUser){
        // Sets the mainUser instance attribute to the one passed and the matchedUser to a random user
        this.mainUser = mainUser;
        randomMatch();
    }

    // Method randomly finds a matched user
    public void randomMatch(){
        //int index = (int)((Math.random() * this.mainUser.getFriends().size()));
        if(mainUser.getFriends().size() >= 1) {
            Random rg = new Random();
            int index = rg.nextInt(this.mainUser.getFriends().size());
            this.matchedUser = mainUser.getFriends().get(index);
        }
    }

    // Method randomly finds a matched user which is not the skipped user
    public void skipMatch(User otherUser){
        User tempUser = otherUser;

        while (otherUser.getUsername().equals(tempUser.getUsername())){
            randomMatch();
            tempUser = this.matchedUser;
        }
    }

    // Method returns the user chosen / extended with (redundant for now but will not be when cases are edited)
    public void choseMatch(User otherUser){
        this.matchedUser = otherUser;
    }

    public User getMatchedUser(){
        return this.matchedUser;
    }

    // Starts a new chat between the mainUser and matchedUser
    public void openChat() throws IOException, InterruptedException {
        String s1 = "src/" + this.mainUser.getUsername() + this.matchedUser.getUsername() + ".txt";
        String s2 = "src/" + this.matchedUser.getUsername() + this.mainUser.getUsername() + ".txt";

        if (!new File(s2).exists() && !new File(s1).exists()){
            File f = new File(s1);
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            } else {
                System.out.println("File already exists.");
            }
        }

        ChatScreen testChat = new ChatScreen(this.mainUser, this.matchedUser);
        testChat.setVisible(true);
    }


}
