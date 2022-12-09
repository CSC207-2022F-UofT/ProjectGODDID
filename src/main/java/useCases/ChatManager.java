package useCases;



import Databases.CreateChatText;
import Interfaces.ChatScreenInt;
import Interfaces.CreateChatInt;
import UI.ChatScreen;
import entities.User;
import java.io.IOException;
import java.util.Random;

/**
 * This class contains methods for the WelcomePage and FriendsPage pages in the GUI to interact with so that they can
 * select the user that the main user (mainUser) is matched with (matchedUser), as well as get the information of whom
 * the user has been matched with if matchedUser has been randomized, and finally open the actual chat with the user.
 *
 * @author Arian Khademi
 * @version 1.0
 * @since November 20th, 2022
 */
public class ChatManager {
    User mainUser;
    User matchedUser;

    /**
     * Constructor for ChatManager.
     * This is the constructor for the ChatManager class, which sets the instance attribute mainUser to the user
     * passed into the constructor, and calls randomMatch to also set the instance attribute matchedUser.
     *
     * @param mainUser the user which is accessing the GUI and looking for a user to match with.
     */
    public ChatManager(User mainUser){
        this.mainUser = mainUser;
        randomMatch();
    }

    /**
     * Method to randomly select matchedUser.
     * This method selects a random user from the mainUser's friend list and sets matchedUser to the result.
     */
    public void randomMatch(){
        if (mainUser.getFriends().size()>= 1) {
            Random rg = new Random();
            int index = rg.nextInt(this.mainUser.getFriends().size());
            this.matchedUser = mainUser.getFriends().get(index);
        }
    }

    /**
     * Method to skip previous matchedUser.
     * This method calls randomMatch to select a random user from the user's friend list and set it as the matchedUser,
     * however, it also keeps finding a randomMatch until the matchedUser is not the same as the user passed in.
     *
     * @param otherUser the user that the mainUser does not want to be matched with.
     */
    public void skipMatch(User otherUser){
        User tempUser = otherUser;

        while (otherUser.getUsername().equals(tempUser.getUsername())){
            randomMatch();
            tempUser = this.matchedUser;
        }
    }

    /**
     * Method to set matchedUser to chosen user.
     * This method sets the matchedUser instance attribute to the user passed to the method, for when the mainUser has
     * specifically chosen to speak with the user that is passed in.
     *
     * @param otherUser the user that the mainUser has chosen to be matched with.
     */
    public void choseMatch(User otherUser){
        this.matchedUser = otherUser;
    }

    /**
     * Getter for matchedUser instance attribute.
     *
     * @return the matched user (matchedUser).
     */
    public User getMatchedUser(){
        return this.matchedUser;
    }


    /**
     * Creates a new ChatScreen, and text file to store messages, so the users can chat with each other.
     *
     * @throws IOException if createNewFile fails
     */
    public void openChat() throws IOException, InterruptedException {
        String s1 = "src/" + this.mainUser.getUsername() + this.matchedUser.getUsername() + ".txt";
        String s2 = "src/" + this.matchedUser.getUsername() + this.mainUser.getUsername() + ".txt";

        CreateChatInt createText = new CreateChatText();
        createText.newChat(s1, s2);

        ChatScreenInt startNew = new ChatScreen(this.mainUser, this.matchedUser);
        startNew.setVisible(true);
    }
}