package useCases;

import entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test the FriendRecommender use case and see if it succesfully recommends a user for current user
 * to add to their friends via the random method or using an algorithim that determines
 * the best user to recommend based on mutual friend count based on neighbours in the graph.
 */
class FriendRecommenderTest {

    AccountManager accountManager = new AccountManager();
    User curUser, user2, user3, user4, user5;

    ArrayList<User> alphaFriends;

    /**
     * To test use case:
     * 1) Create 5 users and add them to the graph
     * 2) Typically we call a controller as this function is controlled by a UI
     * but here we can bypass the controller and just directly call the FriendAdder
     * use case for testing purposes
     * 3) Add the friends to various users
     * 4) Call FriendRecommender.getRecommendRandom for user 5 to get 3 randomly
     * selected users from user5's friend list
     * 5) Check to see if 3 users are succesfully added to curUsers friend list which
     * already includes 2 users
     * 6) Call FriendRecommender.getRecommend for curUser to get most common friend among
     * their neighbours / friends
     * 7) Check to see if recommended friend is user5 who is the most common shared friend
     * among user3 and user4 who are both in curUsers friend list / neighbours
     */
    @BeforeEach
    void setUp() throws IOException, ClassNotFoundException {
        curUser = new User("Alpha", "");
        accountManager.addUser(curUser);
        System.out.println(curUser + curUser.getUsername());

        user2 = new User("Beta", "");
        accountManager.addUser(user2);

        user3 = new User("Charlie", "");
        accountManager.addUser(user3);

        user4 = new User("Delta", "");
        accountManager.addUser(user4);

        user5 = new User("Echo", "");
        accountManager.addUser(user5);

        alphaFriends = new ArrayList<>();
        alphaFriends.add(user4);
        alphaFriends.add(user3);

        accountManager.addFriend(curUser, user3);
        accountManager.addFriend(curUser, user4);

        accountManager.addFriend(user4, user5);
        accountManager.addFriend(user4, user3);
        accountManager.addFriend(user3, user5);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRecommendRandom() {
        FriendRecommender fRec = new FriendRecommender();
        ArrayList<String> recs = new ArrayList<>();
        recs = fRec.getRecommendRandom(user4);
        ArrayList<User> friends = new ArrayList<>();
        friends = curUser.getFriends();
        for (int i = 0; i < recs.size(); i++) {
            System.out.println(recs.get(i));
            friends.add(accountManager.getGraph().getUser(recs.get(i)));
        }
        curUser.setFriends(friends);
        assertEquals(5, curUser.getFriends().size());
    }

    @Test
    void getRecommend() {
        FriendRecommender fRec = new FriendRecommender();
        String rec = fRec.getRecommend(curUser, accountManager.getGraph());
        assertEquals(user5.getUsername(), rec); //Get the most common friend between your friends
    }

}

