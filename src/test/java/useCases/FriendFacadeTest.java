package useCases;

import entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test the FriendFacade and see if it succesfully adds or removes friends for current user.
 * This is similar to the FriendAdderTest and FriendRemoverTest
 */

public class FriendFacadeTest {

    AccountManager accountManager = new AccountManager();
    User user1, user2, user3, user4, user5;

    /**
     * To test use case:
     * 1) Create 5 users and add them to the graph
     * 2) Typically we call a controller as this function is controlled by a UI
     * but here we can bypass the controller and just directly call the FriendFacade
     * use case for testing purposes
     * 3) Add the friends to various users using FriendFacade
     * 4) Check that right friends are added to corresponding users
     * 5) Check right amount of friends are added to corresponding user
     * 6) Remove the friends from various users using FriendFacade and see if corresponding users are removed
     */
    @BeforeEach
    void setUp() throws IOException, ClassNotFoundException {
        user1 = new User("Alpha");
        accountManager.addUser(user1);

        user2 = new User("Beta");
        accountManager.addUser(user2);

        user3 = new User("Charlie");
        accountManager.addUser(user3);

        user4 = new User("Delta");
        accountManager.addUser(user4);

        user5 = new User("Echo");
        accountManager.addUser(user5);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addFriend() throws IOException, ClassNotFoundException {
        //FriendAdder fd = new FriendAdder();
        FriendFacade friendFacade = new FriendFacade();
        friendFacade.addFriend(user1, user2);
        friendFacade.addFriend(user2, user1);
        friendFacade.addFriend(user5, user1);
        friendFacade.addFriend(user3, user4);
        friendFacade.addFriend(user3, user5);
        assert(user1.getFriends().contains(user2));
        assert(user2.getFriends().contains(user1));
        assert(user5.getFriends().contains(user1));
        assert(user3.getFriends().contains(user4));
        assert(user3.getFriends().contains(user5));
        assertEquals(2, user3.getFriends().size());
    }

    @Test
    void removeFriend() throws IOException, ClassNotFoundException {
        FriendFacade fr = new FriendFacade();
        fr.addFriend(user1, user2);
        fr.addFriend(user1, user3);
        fr.addFriend(user1, user4);

        fr.removeFriend(user1, user2);
        fr.removeFriend(user1, user3);

        assert(!user1.getFriends().contains(user2));
        assert(!user1.getFriends().contains(user3));
        assert(user1.getFriends().contains(user4));
    }

}