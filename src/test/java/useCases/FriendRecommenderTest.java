package useCases;

import entities.Graph;
import entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FriendRecommenderTest {

    AccountManager accountManager = new AccountManager();
    User curUser, user2, user3, user4, user5;

    ArrayList<User> alphaFriends, deltaFriends;


    @BeforeEach
    void setUp() {
        curUser = new User("Alpha", "");
        accountManager.addUser(curUser);

        user2 = new User("Beta", "");
        accountManager.addUser(user2);

        user3 = new User("Charlie", "");
        accountManager.addUser(user3);

        user4 = new User("Delta", "");
        accountManager.addUser(user4);

        user5 = new User("Echo", "");
        accountManager.addUser(user5);

        alphaFriends = new ArrayList<>();
        alphaFriends.add(user2);
        alphaFriends.add(user3);
        curUser.setFriends(alphaFriends);
        ArrayList<User> keys = new ArrayList<>(accountManager.getGraph().accounts.keySet());
        accountManager.addFriend(curUser, user2);
        accountManager.addFriend(curUser, user3);
        accountManager.addFriend(curUser, user4);
        //accountManager.addFriend(keys.get(0), keys.get(1));
        //accountManager.addFriend(keys.get(0), keys.get(2));
        //accountManager.addFriend(keys.get(0), keys.get(3));

        deltaFriends = new ArrayList<>();
        deltaFriends.add(user3);
        deltaFriends.add(user5);
        user5.setFriends(deltaFriends);
        accountManager.addFriend(user4, user5);
        accountManager.addFriend(user4, user3);
        accountManager.addFriend(user3, user5);
        //accountManager.addFriend(keys.get(3), keys.get(4));
        //accountManager.addFriend(keys.get(3), keys.get(2));
        //accountManager.addFriend(keys.get(2), keys.get(4));
    }

    @AfterEach
    void tearDown() {
    }

    /*@Test
    void getRecommendation() {
        ArrayList<User> keys = new ArrayList<>(accountManager.getGraph().accounts.keySet());
        FriendRecommender fRec = new FriendRecommender();
        ArrayList<User> recs = new ArrayList<>();
        recs = fRec.getRecommendation(user5);
        ArrayList<User> friends = new ArrayList<>();
        friends = user5.getFriends();
        for (int i = 0; i < recs.size(); i++) {
            friends.add(recs.get(i));
        }
        curUser.setFriends(friends);
        assertEquals(5, curUser.getFriends().size()); //adds repeat friends????
    }*/

    @Test
    void getRecommend() {
        ArrayList<User> keys = new ArrayList<>(accountManager.getGraph().accounts.keySet());
        System.out.println(keys);
        FriendRecommender fRec = new FriendRecommender();
        User rec = fRec.getRecommend(keys.get(0), accountManager.getGraph());
        assertEquals(keys.get(4), rec); //Get the most common friend between your friends
    }
}



