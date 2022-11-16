package useCases;

import entities.Graph;
import entities.User;
import entities.Vertex;
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

    /**
     * Create graph of users to simulate a working app with users in it
     */
    @BeforeEach
    void setUp() {
        accountManager = new AccountManager();
        accountManager.addUser("Alpha", "","","");
        accountManager.addUser("Beta", "","","");
        accountManager.addUser("Charlie", "","","");
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
        ArrayList<Vertex> keys = new ArrayList<>(accountManager.getGraph().accounts.keySet());
        friendAdder.add(keys.get(0).curr_user, keys.get(1).curr_user, accountManager);
        friendAdder.add(keys.get(0).curr_user, keys.get(2).curr_user, accountManager);
        assertEquals(2, keys.get(0).curr_user.getFriends().size());
        assertEquals(2, accountManager.getGraph().accounts.get(keys.get(0)).size());
    }
}