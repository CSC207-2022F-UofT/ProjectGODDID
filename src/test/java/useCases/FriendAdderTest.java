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

/**
 * Test the FriendAdder use case and see if it succesfully adds a user to current users friends list
 * and adds edge between the two user vertices in the graph
 */
public class FriendAdderTest {

        AccountManager accountManager = new AccountManager();
        User user1, user2, user3, user4, user5;

    /**
     * To test use case:
     * 1) Create 5 users and add them to the graph
     * 2) Typically we call a controller as this function is controlled by a UI
     * but here we can bypass the controller and just directly call the FriendAdder
     * use case for testing purposes
     * 3) Add the friends to various users using FriendAdder
     * 4) Check that right friends are added to corresponding users
     * 5) Check right amount of friends are added to corresponding user
     */
    @BeforeEach
        void setUp() throws IOException, ClassNotFoundException {

            user1 = new User("", "");
            user1.setUsername("Alpha");
            user1.setAccountType("casual");
            accountManager.addUser(user1);

            user2 = new User("", "");
            user2.setUsername("Beta");
            user2.setAccountType("casual");
            accountManager.addUser(user2);

            user3 = new User("", "");
            user3.setUsername("Charlie");
            user3.setAccountType("casual");
            accountManager.addUser(user3);

            user4 = new User("", "");
            user4.setUsername("Delta");
            user4.setAccountType("casual");
            accountManager.addUser(user4);

            user5 = new User("", "");
            user5.setUsername("Echo");
            user5.setAccountType("casual");
            accountManager.addUser(user5);


        }

        @AfterEach
        void tearDown() {
        }

        @Test
        void addFriend() throws IOException, ClassNotFoundException {
            FriendAdder fd = new FriendAdder();
            fd.addFriend(user1, user2);
            fd.addFriend(user2, user1);
            fd.addFriend(user5, user1);
            fd.addFriend(user3, user4);
            fd.addFriend(user3, user5);
            assert(user1.getFriends().contains(user2));
            assert(user2.getFriends().contains(user1));
            assert(user5.getFriends().contains(user1));
            assert(user3.getFriends().contains(user4));
            assert(user3.getFriends().contains(user5));
        }

    }


