package useCases;

import entities.Graph;
import entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserRemoverTest {

    AccountManager accountManager = new AccountManager();
    User user1, user2, user3, user4, user5;



    @BeforeEach
    void setUp() throws IOException, ClassNotFoundException {

        user1 = new User("", "");
        user1.setUsername("Alpha");
        user1.setAccountType("casual");
        accountManager.addUser(user1);
        //accountManager.addUser("Alpha", "123","mail","Casual");

        user2 = new User("", "");
        user2.setUsername("Beta");
        user2.setAccountType("casual");
        accountManager.addUser(user2);
        //accountManager.addUser("Beta", "123","mail","Casual");

        user3 = new User("", "");
        user3.setUsername("Charlie");
        user3.setAccountType("casual");
        accountManager.addUser(user3);
        //accountManager.addUser("Charlie", "123","mail","Casual");

        user4 = new User("", "");
        user4.setUsername("Delta");
        user4.setAccountType("casual");
        accountManager.addUser(user4);
        //accountManager.addUser("Delta", "123","mail","Casual");

        user5 = new User("", "");
        user5.setUsername("Echo");
        user5.setAccountType("casual");
        accountManager.addUser(user5);
        //accountManager.addUser("Echo", "123","mail","Casual");


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void removeUser() throws IOException, ClassNotFoundException {
        AccountManager am = new AccountManager();
        AccountManager.user_graph.accounts.clear();
        am.addUser(user1);
        am.addUser(user2);
        am.removeUser(user2);
        System.out.println(am.getGraph().getUsers());
        assert(am.getGraph().getUsers().size() == 1);
    }

}
