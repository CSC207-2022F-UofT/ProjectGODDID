package useCases;

import UI.ChatScreen;
import entities.User;
import javax.swing.*;
import java.io.File;

public class ChatManager {
    User mainUser;
    User matchedUser;
    public ChatManager(User mainUser, User otherUser, String typeUse){
        this.mainUser = mainUser;

        switch (typeUse) {
            case "skip" -> this.matchedUser = skipMatch(otherUser);
            case "choose", "extend" -> this.matchedUser = choseMatch(otherUser);
        }
    }

    public ChatManager(User mainUser){
        this.mainUser = mainUser;
        this.matchedUser = randomMatch();
    }

    public User randomMatch(){
        int index = (int)(Math.random() * this.mainUser.getFriends().size());
        return mainUser.getFriends().get(index);
    }

    public User skipMatch(User otherUser){
        User tempUser = otherUser;

        while (otherUser.getUsername().equals(tempUser.getUsername())){
            tempUser = randomMatch();
        }

        return tempUser;
    }

    public User choseMatch(User otherUser){
        return otherUser;
    }

    public void openChat() {
        // Opens a new ChatScreen with the selected user
        try
        {
            String s = "src/" + this.mainUser.getUsername() + this.matchedUser.getUsername() + ".txt";
            File file = new File(s);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
            else {
                System.out.println("File already exists.");
            }
        }
        catch(Exception e)
        {
            //handle exception
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ChatScreen chat = new ChatScreen(this.mainUser, this.matchedUser);
        chat.setVisible(true);
    }
}
