package controllers;

import entities.User;
import useCases.FriendAdder;

import java.util.ArrayList;

public class AddUIController {

    private User friend;

    public void addUser(ArrayList<User> allUsers, User currUser, User target) {
        FriendAdder friendAdder = new FriendAdder();
        for (User i : allUsers)
            if (i.getUsername() == target.getUsername()){
                friendAdder.add(currUser, target);
            }
    }
}
