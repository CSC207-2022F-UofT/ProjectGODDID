package useCases;

import entities.*;

/*
Adds friend to current users list of friends as well as manipulates graph to create a new edge between the current user and the added friend
*/
public class FriendAdder {
    public FriendAdder(){
    }

    /*
    Add friend to current users friends list and create new edge between the two in the graph of users
    */
    public void add(User currUser, User friend, AccountManager manager)
    {
        currUser.getFriends().add(friend);
        friend.getFriends().add(currUser);
        manager.addFriend(currUser, friend);
        //allUsers.addEdge(currUser.getUsername(),currUser.getAccountType(), currUser, friend.getUsername(), friend.getAccountType(), friend);
    }
}
