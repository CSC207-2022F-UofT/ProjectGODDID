package useCases;

import UI.ChatScreen;
import entities.User;
import java.io.File;
import java.io.IOException;

public class ChatManager {
    // Declaring instance attributes
    User mainUser;
    User matchedUser;

    // Constructor w/ 3 parameters
    public ChatManager(User mainUser, User otherUser, String typeUse){
        // Sets the mainUser instance attribute to the one passed to the constructor
        this.mainUser = mainUser;

        // Depending on the cases calls the appropriate method and sets the matchedUser to the returned user
        switch (typeUse) {
            case "skip" -> this.matchedUser = skipMatch(otherUser);
            case "choose", "extend" -> this.matchedUser = choseMatch(otherUser);
        }
    }

    // Constructor w/ 1 parameter
    public ChatManager(User mainUser){
        // Sets the mainUser instance attribute to the one passed and the matchedUser to a random user
        this.mainUser = mainUser;
        this.matchedUser = randomMatch();
    }

    // Method randomly finds a matched user
    public User randomMatch(){
        int index = (int)(Math.random() * this.mainUser.getFriends().size());
        return mainUser.getFriends().get(index);
    }

    // Method randomly finds a matched user which is not the skipped user
    public User skipMatch(User otherUser){
        User tempUser = otherUser;

        while (otherUser.getUsername().equals(tempUser.getUsername())){
            tempUser = randomMatch();
        }

        return tempUser;
    }

    // Method returns the user chosen / extended with (redundant for now but will not be when cases are edited)
    public User choseMatch(User otherUser){
        return otherUser;
    }

    // Starts a new chat between the mainUser and matchedUser
    public void openChat() throws IOException, InterruptedException {
        String s1 = "src/" + mainUser.getUsername() + matchedUser.getUsername() + ".txt";
        String s2 = "src/" + matchedUser.getUsername() + mainUser.getUsername() + ".txt";

        if (!new File(s2).exists() && !new File(s1).exists()){
            File f = new File(s1);
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            } else {
                System.out.println("File already exists.");
            }
        }

        ChatScreen testChat = new ChatScreen(mainUser, matchedUser);
        testChat.setVisible(true);
    }
}
