package controllers;

import entities.User;
import useCases.ChatManager;

import java.io.IOException;

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
     *
     *
     * @param mainUser
     */
    public ChatManagerController(User mainUser){
        this.manager = new ChatManager(mainUser);
    }

    /**
     *
     */
    public void randomize(){
        manager.randomMatch();
    }

    /**
     *
     * @param matchType
     * @param otherUser
     */
    public void makeMatch(String matchType, User otherUser){
        if (matchType.equals("Skip")) {
            manager.skipMatch(otherUser);
        } else if (matchType.equals("Choose")) {
            manager.choseMatch(otherUser);
        }
    }

    /**
     *
     * @return
     */
    public User getMatch(){
        return manager.getMatchedUser();
    }

    public void createChat() throws IOException, InterruptedException {
        manager.openChat();
    }
}
