package controllers;

import entities.User;
import useCases.AccountManager;

import java.io.IOException;

public class AddFriendController {

    AccountManager addFriend = new AccountManager();

    public void AddFriendCon(User user, String username) throws IOException, ClassNotFoundException {
        for (User i : addFriend.getGraph().getUsers()){
            if (i.getUsername().equals(username)){
                addFriend.addFriend(user, i);
            }
        }

    }
}
