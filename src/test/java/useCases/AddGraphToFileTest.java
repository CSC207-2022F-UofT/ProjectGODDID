package useCases;

import entities.Graph;
import entities.User;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddGraphToFileTest {
    Graph graph;
    AccountManager accountManager = new AccountManager();
    User curUser, user2, user3, user4, user5;
    ArrayList<User> alphaFriends, deltaFriends;

    @BeforeEach
    void setUp() {
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
        curUser.setFriends(alphaFriends);
        //accountManager.addFriend(curUser, user2);
        //accountManager.addFriend(curUser,user3);
        ArrayList<User> keys = new ArrayList<>(accountManager.getGraph().accounts.keySet());
        accountManager.addFriend(keys.get(0), keys.get(1));
        accountManager.addFriend(keys.get(0), keys.get(2));
        accountManager.addFriend(keys.get(0), keys.get(3));
        //System.out.println(graph.getVertices());
        //graph.addEdge("Alpha", "Casual", curUser, "Beta","Casual",user2);
        //graph.addEdge("Alpha", "Casual", curUser, "Charlie","Casual",user3);

        deltaFriends = new ArrayList<>();
        deltaFriends.add(user3);
        deltaFriends.add(user5);
        user5.setFriends(deltaFriends);
        //accountManager.addFriend(user4, user3);
        //accountManager.addFriend(user4,user5);
        accountManager.addFriend(keys.get(3), keys.get(4));
        accountManager.addFriend(keys.get(3), keys.get(2));
        accountManager.addFriend(keys.get(2), keys.get(4));
        //graph.addEdge("Delta", "Casual", user4, "Echo","Casual",user5);
        //graph.addEdge("Delta", "Casual", user4, "Charlie","Casual",user3);
    }

//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void ReadFromFileDemo() throws IOException, ClassNotFoundException {
        Graph grap = accountManager.getGraph();
        AddGraphToFile addfile = new AddGraphToFile();
        addfile.AddToFile(grap);
        Graph g2 = addfile.ReadFromFile();
        assertEquals(grap, g2);
    }
}
