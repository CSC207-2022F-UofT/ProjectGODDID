

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

    /*
    Add friend to current users friends list and create new edge between the two in the graph of users
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
