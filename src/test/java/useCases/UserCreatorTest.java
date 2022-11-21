package useCases;

import entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserCreatorTest {

    AccountManager accountManager = new AccountManager();
    User user1, user2;



    @BeforeEach
    void setUp() throws IOException, ClassNotFoundException {
        /*User user1 = new User("alpha","casual");
        User user2 = new User("beta", "premium");*/

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addUser() throws IOException, ClassNotFoundException {
        User user1 = new User("alpha","casual");
        User user2 = new User("beta", "premium");
        AccountManager am = new AccountManager();
        AccountManager.user_graph.accounts.clear();
        am.addUser(user1);
        am.addUser(user2);
        am.addUser("delta", "asd123","casual");
        assert(am.getGraph().getUsers().size() == 3);
    }

}