package controllers;

import entities.User;
import useCases.ChatManager;

/**
 * This is a controller to be used by WelcomePage and FriendsPage which can interact with ChatManager. This controller
 * is required for these pages because it will allow them to match the user with a new user and also get the match so
 * that the users can know who they are matched with before they open a chat so that they can skip or change their
 * matched user if they don't like their currently matched user.
 *
 * @author Arian Khademi
 * @version 1.0
 * @since December 5th, 2022
 */
public class ChatManagerController {
    ChatManager manager;

    /**
     * Constructor for ChatManagerController.
     * This is a constructor for ChatManagerController, which creates a sets the manager instance attribute to a new
     * ChatManager object that passes the mainUser in as a parameter.
     *
     * @param mainUser the user which is accessing the GUI and being matched with another user.
     */
    public ChatManagerController(User mainUser){
        this.manager = new ChatManager(mainUser);
    }

    /**
     * Method for randomly matching user.
     * This method uses the randomMatch method of the ChatManager class to set a randomized user as the matched user of
     * manager's matchedUser attribute.
     */
    public void randomize(){
        manager.randomMatch();
    }

    /**
     * Method for skipping or choosing matched user.
     * This method uses the skipMatch and choseMatch methods of the ChatManager class to allow the user to either
     * skip or choose their match depending on the matchType passed into the method.
     *
     * @param matchType the type of match that the main user wants to be made.
     * @param otherUser the user that the main user either wants to skip or match with.
     */
    public void makeMatch(String matchType, User otherUser){
        if (matchType.equals("Skip")) {
            manager.skipMatch(otherUser);
        } else if (matchType.equals("Choose")) {
            manager.choseMatch(otherUser);
        }
    }

    /**
     * Method for getting the matched user.
     * This method uses the getMatchedUser method from ChatManager class to get who the matched user is.
     *
     * @return the matched user.
     */
    public User getMatch(){
        return manager.getMatchedUser();
    }
}
