package useCases;

import entities.*;

import java.io.IOException;

/*
Adds friend to current users list of friends as well as manipulates graph to create a new edge between the current user and the added friend
*/
public class FriendRemover {

    public FriendRemover(){}
    /*
    Add friend to current users friends list and create new edge between the two in the graph of users
    */
    public void remove(User currUser, User friend, AccountManager manager) throws IOException, ClassNotFoundException {
        currUser.getFriends().remove(friend);
        friend.getFriends().remove(currUser);
        manager.removeFriend(currUser, friend);
        //allUsers.removeEdge(currUser.getUsername(),currUser.getAccountType(), currUser, friend.getUsername(), friend.getAccountType(), friend);
    }
}