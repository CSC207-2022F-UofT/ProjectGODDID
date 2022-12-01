package useCases;

import entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test the FriendRemover use case and see if it succesfully removes a user to current users friends list
 * and deletes edge between the two user vertices in the graph
 */
public class FriendRemoverTest {

//    AccountManager accountManager = new AccountManager();
//    User user1, user2, user3, user4, user5;
//
//    @BeforeEach
//    void setUp() throws IOException, ClassNotFoundException {
//        user1 = new User("Alpha", "");
//        accountManager.addUser(user1);
//
//        user2 = new User("Beta", "");
//        accountManager.addUser(user2);
//
//        user3 = new User("Charlie", "");
//        accountManager.addUser(user3);
//
//        user4 = new User("Delta", "");
//        accountManager.addUser(user4);
//
//        user5 = new User("Echo", "");
//        accountManager.addUser(user5);
//
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    /**
//     * To test use case:
//     * 1) Create 5 users and add them to the graph
//     * 2) Typically we call a controller as this function is controlled by a UI
//     * but here we can bypass the controller and just directly call the FriendAdder
//     * use case for testing purposes
//     * 3) Add the friends to various users using FriendAdder
//     * 4) Remove friends from user1 using FriendRemover
//     * 5) Check that right friends are removed from the corresponding users
//     */
//    @Test
//    void removeFriend() throws IOException, ClassNotFoundException {
//        FriendRemover fr = new FriendRemover();
//        FriendAdder fd = new FriendAdder();
//        fd.addFriend(user1, user2, accountManager);
//        fd.addFriend(user1, user3, accountManager);
//        fd.addFriend(user1, user4, accountManager);
//
//        fr.remove(user1, user2, accountManager);
//        fr.remove(user1, user3, accountManager);
//
//        assert(!user1.getFriends().contains(user2));
//        assert(!user1.getFriends().contains(user3));
//        assert(user1.getFriends().contains(user4));
//
//    }
}