package useCases;

import Databases.ReadGraph;
import Interfaces.ReadGraphInt;
import entities.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Tests the UserRemover class to see if the user is removed from the graph stored in the ser file.
 */
public class UserRemoverTest {
    UserFactory user_f = new UserFactory();
    UserRemover user_r = new UserRemover();


    @Test
    void addUser() throws IOException, ClassNotFoundException {
        AccountManager am=new AccountManager();
        User user_new = user_f.CreateUser("james", "1357");
        am.addUser(user_new);
        ReadGraphInt rg = new ReadGraph();
        user_r.removeUser(user_new);
        assert(!rg.readobject().accounts.containsKey(user_new.getUsername()));
    }
}
