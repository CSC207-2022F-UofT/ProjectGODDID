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


public class FriendAdderTest {

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
        void addFriend() throws IOException, ClassNotFoundException {
            FriendAdder fd = new FriendAdder();
            //AccountManager am = new AccountManager();
            System.out.println("user1:"+user1.getFriends());
            fd.addFriend(user1, user2);
            fd.addFriend(user2, user1);
            fd.addFriend(user5, user1);
            fd.addFriend(user3, user4);
            fd.addFriend(user3, user5);
            fd.addFriend(user4, user1);

            /*am.addFriend(user1, user2);
            am.addFriend(user2, user1);
            am.addFriend(user5, user1);
            am.addFriend(user3, user4);
            am.addFriend(user3, user5);
            am.addFriend(user4, user1);*/
            System.out.println(user2);
            System.out.println("user1:"+user1.getFriends());
            System.out.println("user3:"+user3.getFriends());
            System.out.println("user4:"+user4.getFriends());
            System.out.println("user5:"+user5.getFriends());
            assert(user1.getFriends().contains(user2));
            assert(user2.getFriends().contains(user1));
            assert(user5.getFriends().contains(user1));
            assert(user3.getFriends().contains(user4));
            assert(user3.getFriends().contains(user5));
            assert(user4.getFriends().contains(user1));
        }

    }


