package controllers;

import entities.User;
import useCases.AccountManager;
import useCases.FriendAdder;

import java.io.IOException;
import java.util.ArrayList;

public class AddUIController {

    private User friend;

    public void addUser(ArrayList<User> allUsers, User currUser, User target) throws IOException, ClassNotFoundException {
        FriendAdder friendAdder = new FriendAdder();
        for (User i : allUsers)
            if (i.getUsername().equals(target.getUsername())){
                friendAdder.addFriend(currUser, target);
            }
    }
}
