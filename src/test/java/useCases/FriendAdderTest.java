package useCases;

import entities.Graph;
import entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing the FriendAdder usecase and the add method
 */
class FriendAdderTest {

    AccountManager accountManager;
    User curUser, user2, user3, user4, user5;

    ArrayList<User> alphaFriends, deltaFriends;

    /**
     * Create graph of users to simulate a working app with users in it
     */
    @BeforeEach
    void setUp() {
        accountManager = new AccountManager();

        curUser = new User("Alpha", "casual");
        user2 = new User("Beta", "casual");
        user3 = new User("Charlie", "casual");
        accountManager.addUser(curUser);
        accountManager.addUser(user2);
        accountManager.addUser(user3);
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Add two friends to user Alpha and test if successfully added both users
     */
    @Test
    void add() {
        FriendAdder friendAdder = new FriendAdder();
        //ArrayList<Vertex> keys = new ArrayList<>(accountManager.getGraph().accounts.keySet());
        friendAdder.addFriend(curUser,user2,accountManager);
        friendAdder.addFriend(curUser,user3,accountManager);
        assertEquals(2, curUser.getFriends().size());
        assertEquals(2, accountManager.getGraph().accounts.get(curUser).size());
    }
}