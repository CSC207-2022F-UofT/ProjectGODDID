package useCases;

import entities.*;

/*
Adds friend to current users list of friends as well as manipulates graph to create a new edge between the current user and the added friend
*/
public class FriendRemover {

    public FriendRemover(){}
    /*
    Add friend to current users friends list and create new edge between the two in the graph of users
    */
    public void remove(Vertex currUser, Vertex friend, AccountManager manager)
    {
        manager.removeFriend(currUser, friend);
        currUser.curr_user.getFriends().remove(friend.curr_user);
        //allUsers.removeEdge(currUser.getUsername(),currUser.getAccountType(), currUser, friend.getUsername(), friend.getAccountType(), friend);
    }
}
