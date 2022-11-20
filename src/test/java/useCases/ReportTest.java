package useCases;

import entities.User;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.ArrayList;

public class ReportTest {
    AccountManager manager = new AccountManager();
    User user1, user2;

    @BeforeEach
    void setUp() throws IOException, ClassNotFoundException {
        user1 = new User("TestAdam", "Casual");
        user2 = new User("Bob", "Casual");
        manager.addUser(user1);
        manager.addUser(user2);
        manager.addFriend(user1, user2);

    }

    @AfterEach
    void tearDown(){

    }

    @Test
    public void testReport() throws IOException, ClassNotFoundException {
        Report report = new Report(user1, user2);
        report.checkReport();
        ArrayList<User> l1 = user1.getBlocked_friends();
        assert(l1.contains(user2));
    }

}
