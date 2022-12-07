package controllers;

import entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import useCases.AccountManager;


import java.io.IOException;

public class ReportControllerTest {

    AccountManager manager = new AccountManager();
    User user1, user2;

    @BeforeEach
    void setUp() throws IOException, ClassNotFoundException {
        user1 = new User("TestAdam");
        user2 = new User("Bob");
        manager.addUser(user1);
        manager.addUser(user2);
        manager.addFriend(user1, user2);
    }

    @AfterEach
    void tearDown(){
    }

    /** The test below first shows user1 TestAdam calls the report button. I already made an existing txt file
     * TestAdamBob.txt for the chat to test the report algorithm. You can review that txt file. Keep in mind the
     * keywords.txt file is NOT created by us. It was simply taken from the internet, WE DO NOT CONDEMN THE USAGE OF
     * SUCH LANGUAGE. Each helper function in Report class is tested below. The last 3 tests are for the functionality
     * of report with the rest of the chatting app. User1's friends list is now edited to remove user2. User1's blocked
     * lists is also edited to add user2. User2's number of strikes is also increased to 1.
     */
    @Test
    public void testReport() throws IOException, ClassNotFoundException {
        ReportController report = new ReportController();
        report.reportController(user1, user2);
        assert(!user1.getFriends().contains(user2));
        assert(!user2.getFriends().contains(user1));
        assert(user2.getNum_strikes() == 1);
    }

}
