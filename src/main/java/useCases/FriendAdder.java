package useCases;

import entities.*;

import java.io.IOException;
import java.util.ArrayList;

/*
Adds friend to current users list of friends as well as manipulates graph to create a new edge between the current user and the added friend
*/
public class FriendAdder {
    public FriendAdder(){
    }

    /**
     * @param currUser the user that wants to add a friend
     * @param friend the friend that needs to be added
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void addFriend(User currUser, User friend) throws IOException, ClassNotFoundException {
        AccountManager temp = new AccountManager();
        temp.addFriend(currUser, friend);
        ArrayList friends = new ArrayList<>();
        friends = currUser.getFriends();
        friends.add(friend);
        currUser.setFriends(friends);
    }
}
