package controllers;

import entities.Graph;
import entities.User;
import useCases.AccountManager;
import useCases.FriendAdder;

import java.io.IOException;

public class AddFriendController {

    AccountManager adder = new AccountManager();

    Graph adderg = new Graph();

    public AddFriendController() throws IOException, ClassNotFoundException {
    }

    public void AddFriendCon(User user, String username) throws IOException, ClassNotFoundException {

        adder.addFriend(user, adderg.accounts.get(username));
    }
}



