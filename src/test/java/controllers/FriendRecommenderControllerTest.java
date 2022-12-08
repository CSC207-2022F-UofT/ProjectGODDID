package controllers;

import entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import useCases.AccountManager;
import useCases.FriendRecommender;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the FriendRecommenderController and see if it succesfully recommends a user for current user
 * to add to their friends via the random method or using an algorithim that determines
 * the best user to recommend based on mutual friend count based on neighbours in the graph.
 */

public class FriendRecommenderControllerTest {
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
     * 4) Call FriendRecommender.getRecommendRandom for user4 to get randomly
     * selected users from user4's friend list
     * 5) Check to see if returned String is one of user4's friends or if it is the current
     * user in which case the String "Try again" is returned
     * 6) Call FriendRecommender.getRecommend for curUser to get most common friend among
     * their neighbours / friends
     * 7) Check to see if recommended friend is user5 who is the most common shared friend
     * among user3 and user4 who are both in curUsers friend list / neighbours
     */
    @BeforeEach
    void setUp() throws IOException, ClassNotFoundException {
        curUser = new User("Alpha");
        accountManager.addUser(curUser);
        System.out.println(curUser + curUser.getUsername());

        user2 = new User("Beta");
        accountManager.addUser(user2);

        user3 = new User("Charlie");
        accountManager.addUser(user3);

        user4 = new User("Delta");
        accountManager.addUser(user4);

        user5 = new User("Echo");
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
    void getRandomRec() throws IOException, ClassNotFoundException {
        FriendRecommenderController fRec = new FriendRecommenderController();
        String recs;
        recs = fRec.getRandomRec(user4, curUser);
        assertTrue(recs.equals("Try again")||recs.equals(user5.getUsername())||recs.equals(user3.getUsername()));
    }

    @Test
    void getRec() throws IOException, ClassNotFoundException {
        FriendRecommenderController fRec = new FriendRecommenderController();
        String rec = fRec.getRec(curUser);
        assertEquals(user5.getUsername(), rec); //Get the most common friend between your friends
    }

}