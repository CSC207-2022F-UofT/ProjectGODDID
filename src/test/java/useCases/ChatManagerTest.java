package useCases;

import entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * This file exists to test the ChatManager class to ensure that all the methods are working as they should be.
 *
 * @author Brandon
 * @version 1.0
 * @since December 7th, 2022
 */
public class ChatManagerTest {
    AccountManager accountManager = new AccountManager();
    User user1, user2, user3, user4, user5;

    /**
     * To prepare for testing the use cases, we create 5 users and add them to the accountManager we created.
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

    /**
     * Exists to be run after each test. Code can be added inside the method if configuration is desired.
     */
    @AfterEach
    void tearDown() {
    }

    /**
     * This test checks whether randomMatch correctly matches the user to one of their friends.
     *
     * @throws IOException if addFriend fails.
     * @throws ClassNotFoundException if addFriend fails.
     */
    @Test
    void randomMatchTest() throws IOException, ClassNotFoundException {
        FriendAdder fd = new FriendAdder();
        fd.addFriend(user1, user2);
        fd.addFriend(user1, user3);
        ChatManager chatManager = new ChatManager(user1);
        User matched;
        chatManager.randomMatch();
        matched = chatManager.getMatchedUser();
        assert(matched.equals(user2) || matched.equals(user3));
    }

    /**
     * This test checks whether skipMatch correctly matches the user to a different user than the user they chose
     * to skip (who they were previously matched with).
     *
     * @throws IOException if addFriend fails.
     * @throws ClassNotFoundException if addFriend fails.
     */
    @Test
    void skipMatchTest() throws IOException, ClassNotFoundException {
        FriendAdder fd = new FriendAdder();
        fd.addFriend(user1, user2);
        fd.addFriend(user1, user3);
        ChatManager chatManager = new ChatManager(user1);
        User matched;
        chatManager.skipMatch(user2);
        matched = chatManager.getMatchedUser();
        assert(matched.equals(user3));
    }
}
