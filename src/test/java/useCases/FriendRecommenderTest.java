
package useCases;

import entities.Graph;
import entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FriendRecommenderTest {

    Graph graph;

    AccountManager accountManager = new AccountManager();
    User curUser, user2, user3, user4, user5;

    ArrayList<User> alphaFriends, deltaFriends;


    @BeforeEach
    void setUp() throws IOException, ClassNotFoundException {
        graph = new Graph();

        curUser = new User("", "");
        curUser.setUsername("Alpha");
        curUser.setAccountType("casual");
        accountManager.addUser(curUser);
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

        alphaFriends = new ArrayList<>();
        alphaFriends.add(user2);
        alphaFriends.add(user3);
        //curUser.setFriends(alphaFriends);
        //accountManager.addFriend(curUser, user2);
        //accountManager.addFriend(curUser,user3);
        //ArrayList<User> keys = new ArrayList<>(accountManager.getGraph().accounts.keySet());
        FriendAdder fd = new FriendAdder();
        fd.addFriend(curUser, user2, accountManager);
        fd.addFriend(curUser,user3, accountManager);
        fd.addFriend(curUser,user4, accountManager);

        //System.out.println(graph.getVertices());
        //graph.addEdge("Alpha", "Casual", curUser, "Beta","Casual",user2);
        //graph.addEdge("Alpha", "Casual", curUser, "Charlie","Casual",user3);

        deltaFriends = new ArrayList<>();
        //deltaFriends.add(user3);
        //deltaFriends.add(user5);
        //user5.setFriends(deltaFriends);
        fd.addFriend(user4, user3, accountManager);
        fd.addFriend(user4,user5, accountManager);
        fd.addFriend(user3,user5, accountManager);
        //accountManager.addFriend(user4, user3);
        //accountManager.addFriend(user4,user5);
        //accountManager.addFriend(keys.get(3), keys.get(4));
        //accountManager.addFriend(keys.get(3), keys.get(2));
        //accountManager.addFriend(keys.get(2), keys.get(4));

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRecommendation() {
        //ArrayList<User> keys = new ArrayList<>(accountManager.getGraph().accounts.keySet());
        //System.out.println(graph.accounts.get(keys.get(0)));
        //System.out.println(user5.getFriends());
        System.out.println(curUser.getFriends());
        System.out.println(accountManager.getGraph().accounts.size());
        FriendRecommender fRec = new FriendRecommender();
        ArrayList<String> recs = new ArrayList<>();
        recs = fRec.getRecommendation(curUser);
        System.out.println(recs.size());
        ArrayList<User> friends = new ArrayList<>();
        friends = user4.getFriends();
        for (int i = 0; i < recs.size(); i++) {
            //System.out.println(recs.get(i));
            friends.add(accountManager.getGraph().getUser(recs.get(i)));
        }
        //System.out.println(friends.size());
        user4.setFriends(friends);
        System.out.println(user4.getFriends());
        assertEquals(5, curUser.getFriends().size()); //adds repeat friends????
    }

    @Test
    void getRecommend() {
        //ArrayList<User> keys = new ArrayList<>(accountManager.getGraph().accounts.keySet());
        //System.out.println(keys);
        FriendRecommender fRec = new FriendRecommender();
        String rec = fRec.getRecommend(curUser, graph);
        assertEquals(user4.getUsername(), rec); //Get the most common friend between your friends

    }
}

