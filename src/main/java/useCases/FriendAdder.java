package useCases;

import entities.User;

public class FriendAdder {
    public FriendAdder(){

    }

    public void add(User currUser, User friend)
    {
        currUser.getFriends().add(friend);
    }
}
