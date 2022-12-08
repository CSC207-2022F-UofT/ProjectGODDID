package useCases;

import Databases.ReadGraph;
import Interfaces.ReadGraphInt;
import entities.Graph;
import entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Tests the UserFactory class to see if the user is created and added to the graph
 */
public class UserFactoryTest {
    UserFactory user_f = new UserFactory();



    @BeforeEach
    void setUp(){


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addUser() throws IOException, ClassNotFoundException {
        AccountManager am=new AccountManager();
        User user_new = user_f.CreateUser("bob", "1357");
        am.addUser(user_new);
        ReadGraphInt rg = new ReadGraph();
        assert(rg.readobject().accounts.containsKey(user_new.getUsername()));
    }
}
