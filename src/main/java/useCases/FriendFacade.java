package useCases;


import entities.User;

import java.io.IOException;


/**
 * Facade design pattern to call add friend and remove friend
 */
public class FriendFacade {

    public void addFriend(User currUser, User friend) throws IOException, ClassNotFoundException {
        FriendAdder fd = new FriendAdder();
        fd.addFriend(currUser, friend);
    }

    public void removeFriend(User currUser, User friend) throws IOException, ClassNotFoundException {
        FriendRemover fr = new FriendRemover();
        fr.remove(currUser, friend);
    }


}