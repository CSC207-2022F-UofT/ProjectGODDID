package useCases;

import entities.User;
import org.junit.jupiter.api.Test;
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
        ArrayList<User> l2 = user1.getBlocked_friends();
        assert(report.readFiles().size() == 4);
        assert(report.hateWords().size() == 60);
        assert(report.convertToListOfStrings("Bob: ").contains(":"));
        assert(report.checkOffensive("Fuck yourself"));
        assert(l2.contains(user2));
        assert(user2.getNum_strikes() == 1);
    }

}
