package useCases;

import entities.Vertex;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FriendRemoverTest {

    AccountManager accountManager;
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

    @Test
    void remove() {
        FriendAdder friendAdder = new FriendAdder();
        ArrayList<Vertex> keys = new ArrayList<>(accountManager.getGraph().accounts.keySet());
        friendAdder.add(keys.get(0).curr_user, keys.get(1).curr_user, accountManager);
        friendAdder.add(keys.get(0).curr_user, keys.get(2).curr_user, accountManager);
        FriendRemover friendRemover = new FriendRemover();
        friendRemover.remove(keys.get(0).curr_user, keys.get(1).curr_user, accountManager);
        //assertEquals(1, keys.get(0).curr_user.getFriends().size());
        assertEquals(1, accountManager.getGraph().accounts.get(keys.get(0)).size());
    }
}