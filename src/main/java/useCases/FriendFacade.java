package useCases;

import entities.User;

import java.io.IOException;

public class FriendFacade {

    /**
     * @param currUser the user that wants to add a friend
     * @param friend the friend that is to be added
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void addFriend(User currUser, User friend) throws IOException, ClassNotFoundException {
        FriendAdder fd = new FriendAdder();
        fd.addFriend(currUser, friend);
    }

    /**
     * @param currUser the user that wants to remove a friend
     * @param friend the friend that needs to be removed
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void removeFriend(User currUser, User friend) throws IOException, ClassNotFoundException {
        FriendRemover fr = new FriendRemover();
        fr.remove(currUser, friend, new AccountManager());
    }



}
