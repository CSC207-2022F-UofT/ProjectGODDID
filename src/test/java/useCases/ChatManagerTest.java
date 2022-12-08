package useCases;

import entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ChatManagerTest {
    AccountManager accountManager = new AccountManager();
    User user1, user2, user3, user4, user5;

    /**
     * To test use case:
     * 1) Create 5 users and add them to the graph
     * 2) Typically we call a controller as this function is controlled by a UI
     * but here we can bypass the controller and just directly call the FriendAdder
     * use case for testing purposes
     * 3) Add the friends to various users using FriendAdder
     * 4) Check that right friends are added to corresponding users
     * 5) Check right amount of friends are added to corresponding user
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
    void randomMatch() throws IOException, ClassNotFoundException {
        FriendAdder fd = new FriendAdder();
        fd.addFriend(user1, user2);
        fd.addFriend(user1, user3);
        ChatManager chatManager = new ChatManager(user1);
        User matched;
        chatManager.randomMatch();
        matched = chatManager.getMatchedUser();
        assert(matched.equals(user2) || matched.equals(user3));
    }

    @Test
    void skipMatch() throws IOException, ClassNotFoundException {
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