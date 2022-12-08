package useCases;

import entities.Graph;
import entities.User;

import java.io.IOException;
import java.util.ArrayList;

public class FriendFacade {

    /**
     * @param currUser the user who wants to add a friend
     * @param friend the friend to be added
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void addFriend(User currUser, User friend) throws IOException, ClassNotFoundException {
        FriendAdder fd = new FriendAdder();
        fd.addFriend(currUser, friend);
    }

    /**
     * @param currUser the user who wants to remove a friend
     * @param friend the friend to be removed
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void removeFriend(User currUser, User friend) throws IOException, ClassNotFoundException {
        FriendRemover fr = new FriendRemover();
        fr.remove(currUser, friend);
    }


}