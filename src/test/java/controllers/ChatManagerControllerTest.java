package controllers;

import entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This file exists to test the ChatManagerController to ensure that all the methods are working as they should be.
 *
 * @author Arian Khademi
 * @version 1.0
 * @since December 8th, 2022
 */
public class ChatManagerControllerTest {
    User mainUser, user2, user3, user4;
    AccountManager accountManager = new AccountManager();
    FriendAdder friendAdder = new FriendAdder();
    ChatManagerController chatManagerController;

    /**
     * To prepare for testing the use cases, we create 4 users and add them to the accountManager we created.
     */
    @BeforeEach
    void setUp() throws IOException, ClassNotFoundException {
        // Creating the new users and adding them to accountManager
        mainUser = new User("MainUser");
        accountManager.addUser(mainUser);

        user2 = new User("User2");
        accountManager.addUser(user2);

        user3 = new User("User3");
        accountManager.addUser(user3);

        user4 = new User("User4");
        accountManager.addUser(user4);

        chatManagerController = new ChatManagerController(mainUser);
    }

    /**
     * Exists to be run after each test. Code can be added inside the method if configuration is desired.
     */
    @AfterEach
    void tearDown() {
    }

    /**
     * This test checks whether a random user to match with has correctly been chosen
     *
     * @throws IOException if addFriend fails.
     * @throws ClassNotFoundException if addFriend fails.
     */
    @Test
    void randomizeTest() throws IOException, ClassNotFoundException {
        // Adding the 3 other users to MainUser's friends
        friendAdder.addFriend(mainUser, user2);
        friendAdder.addFriend(mainUser, user3);
        friendAdder.addFriend(mainUser, user4);

        chatManagerController.randomize();
        String matchedUsername = chatManagerController.getMatch().getUsername();
        assertTrue(matchedUsername.equals(user2.getUsername()) ||
                matchedUsername.equals(user3.getUsername()) ||
                matchedUsername.equals(user4.getUsername()));
    }

    /**
     * This test checks whether the previous user has correctly been skipped to match with a new user.
     *
     * @throws IOException if addFriend fails.
     * @throws ClassNotFoundException if addFriend fails.
     */
    @Test
    void makeMatchSkipTest() throws IOException, ClassNotFoundException {
        friendAdder.addFriend(mainUser, user2);
        chatManagerController.randomize();
        User matchedUser = chatManagerController.getMatch();

        friendAdder.addFriend(mainUser, user3);
        chatManagerController.makeMatch("Skip", matchedUser);
        User newUser = chatManagerController.getMatch();

        assertEquals("User3", newUser.getUsername());
    }

    /**
     * This test checks whether the chosen match is correctly matched with the user.
     *
     * @throws IOException if addFriend fails.
     * @throws ClassNotFoundException if addFriend fails.
     */
    @Test
    void makeMatchChooseTest() throws IOException, ClassNotFoundException {
        friendAdder.addFriend(mainUser, user2);
        friendAdder.addFriend(mainUser, user3);
        friendAdder.addFriend(mainUser, user4);

        chatManagerController.makeMatch("Choose", user4);
        String matchedUsername = chatManagerController.getMatch().getUsername();

        assertEquals("User4", matchedUsername); //Get the most common friend between your friends
    }

    /**
     * This test checks whether the correct match information is returned when the getter is utilized.
     *
     * @throws IOException if addFriend fails.
     * @throws ClassNotFoundException if addFriend fails.
     */
    @Test
    void getMatchTest() throws IOException, ClassNotFoundException {
        friendAdder.addFriend(mainUser, user2);
        chatManagerController.randomize();
        assertEquals("User2", chatManagerController.getMatch().getUsername());
    }
}
