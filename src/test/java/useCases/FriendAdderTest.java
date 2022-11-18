package useCases;

import entities.Graph;
import entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class FriendAdderTest {

        AccountManager accountManager = new AccountManager();
        User user1, user2, user3, user4, user5;

    @BeforeEach
        void setUp() {
        user1 = new User("Alpha", "");
        accountManager.addUser(user1);

        user2 = new User("Beta", "");
        accountManager.addUser(user2);

        user3 = new User("Charlie", "");
        accountManager.addUser(user3);

        user4 = new User("Delta", "");
        accountManager.addUser(user4);

        user5 = new User("Echo", "");
        accountManager.addUser(user5);

    }

        @AfterEach
        void tearDown() {
        }

        @Test
        void addFriend() {
            FriendAdder fd = new FriendAdder();
            fd.addFriend(user1, user2, accountManager);
            fd.addFriend(user2, user1, accountManager);
            fd.addFriend(user5, user1, accountManager);
            fd.addFriend(user3, user4, accountManager);
            fd.addFriend(user3, user5, accountManager);
            //assert(user1.getFriends().contains(user2));
            //assert(user2.getFriends().contains(user1));
            //assert(user5.getFriends().contains(user1));
            //assert(user3.getFriends().contains(user4));
            //assert(user3.getFriends().contains(user5));
            assertEquals(2, user3.getFriends().size());
        }

    }


