package useCases;

import entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    ArrayList<User> alphaFriends, deltaFriends;

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

        accountManager.addFriend(curUser, user2);
        accountManager.addFriend(curUser, user3);
        accountManager.addFriend(curUser, user4);

        deltaFriends = new ArrayList<>();
        deltaFriends.add(user3);
        deltaFriends.add(user5);
        user5.setFriends(deltaFriends);

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
        ArrayList<User> recs = new ArrayList<>();
        recs = fRec.getRecommendRandom(user5);
        ArrayList<User> friends = new ArrayList<>();
        friends = curUser.getFriends();
        for (int i = 0; i < recs.size(); i++) {
            friends.add(recs.get(i));
        }
        curUser.setFriends(friends);
        assertEquals(5, curUser.getFriends().size()); //adds repeat friends????
    }

    @Test
    void getRecommend() {
        FriendRecommender fRec = new FriendRecommender();
        User rec = fRec.getRecommend(curUser, accountManager.getGraph());
        System.out.println(rec);
        assertEquals(user5, rec); //Get the most common friend between your friends
    }
}



