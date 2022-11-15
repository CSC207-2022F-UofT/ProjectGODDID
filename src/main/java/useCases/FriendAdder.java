package useCases;

import entities.*;

/*
Adds friend to current users list of friends as well as manipulates graph to create a new edge between the current user and the added friend
*/
public class FriendAdder {
    public FriendAdder(){
    }

    /*

    */
    public void add(User currUser, User friend, Graph allUsers)
    {
        currUser.getFriends().add(friend);
        allUsers.addEdge(currUser.getUsername(),currUser.getAccountType(), currUser, friend.getUsername(), friend.getAccountType(), friend);
    }
}
