package useCases;

import EventPackage.Event;
import PointSystem.PointSystemR;
import PointSystem.PointSystemS;
import entities.Graph;
import entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointSystemTest {

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


        accountManager.addFriend(user2, user3);
        accountManager.addFriend(user2, user4);
        accountManager.addFriend(user2, user5);
        //System.out.println(graph.getVertices());
        //graph.addEdge("Alpha", "Casual", curUser, "Beta","Casual",user2);
        //graph.addEdge("Alpha", "Casual", curUser, "Charlie","Casual",user3);

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void RenewTest(){
        ArrayList<User> userlist = new ArrayList<>();
        userlist.add(user2);
        userlist.add(user3);
        PointSystemR testPS = new PointSystemR();

        Event e1 = new Event("ChatEnd", userlist);
        e1.execute(testPS);

        assertEquals(user2.points, 10);
        assertEquals(user3.points, 10);
    }

    @Test
    void SpendTest(){  // also tests that non-involved user vertices are not touched
        ArrayList<User> userlist = new ArrayList<>();
        userlist.add(user2);
        userlist.add(user3);
        PointSystemR testPS = new PointSystemR();

        Event e1 = new Event("ChatEnd", userlist);
        e1.execute(testPS);

        ArrayList<User> ul2 = new ArrayList<User>();
        PointSystemS t = new PointSystemS();
        ul2.add(user2);
        Event e2 =  new Event("SpendSkip", ul2);
        e2.execute(t);

        assertEquals(user2.points, 0);
        assertEquals(user3.points, 10);
    }
}




   /* void setUp() {
        graph = new Graph();
        //accountManager = new AccountManager();
        curUser = new User();
        curUser.setUsername("Alpha");
        currVer = new Vertex("Alpha", "Casual",curUser);
        graph.addVertex("Alpha", "Casual",curUser);
        //accountManager.addUser("Alpha", "123","mail","Casual");

        user2 = new User();
        curUser.setUsername("Beta");
        ver2 = new Vertex("Beta", "Casual", user2);
        graph.addVertex("Beta", "Casual", user2);
        //accountManager.addUser("Beta", "123","mail","Casual");

        user3 = new User();
        curUser.setUsername("Charlie");
        //Vertex ver3 = new Vertex("Charlie", "Casual", user3);
        graph.addVertex("Charlie", "Casual", user3);
        //accountManager.addUser("Charlie", "123","mail","Casual");

        user4 = new User();
        curUser.setUsername("Delta");
        //Vertex ver4 = new Vertex("Delta", "Casual", user4);
        graph.addVertex("Delta", "Casual", user4);
        //accountManager.addUser("Delta", "123","mail","Casual");

        user5 = new User();
        curUser.setUsername("Echo");
        //Vertex ver5 = new Vertex("Echo", "Casual", user5);
        graph.addVertex("Echo", "Casual", user5);
        //accountManager.addUser("Echo", "123","mail","Casual");

        alphaFriends = new ArrayList<>();
        alphaFriends.add(user2);
        alphaFriends.add(user3);
        curUser.setFriends(alphaFriends);
        //accountManager.addFriend(curUser, user2);
        //accountManager.addFriend(curUser,user3);
        ArrayList<Vertex> keys = new ArrayList<>(graph.accounts.keySet());
        graph.addEdge(keys.get(0),keys.get(1));
        graph.addEdge(keys.get(0),keys.get(2));
        graph.addEdge(keys.get(0),keys.get(3));
        //System.out.println(graph.getVertices());
        //graph.addEdge("Alpha", "Casual", curUser, "Beta","Casual",user2);
        //graph.addEdge("Alpha", "Casual", curUser, "Charlie","Casual",user3);

        deltaFriends = new ArrayList<>();
        deltaFriends.add(user3);
        deltaFriends.add(user5);
        user5.setFriends(deltaFriends);
        //accountManager.addFriend(user4, user3);
        //accountManager.addFriend(user4,user5);
        graph.addEdge(keys.get(3),keys.get(4));
        graph.addEdge(keys.get(3),keys.get(2));
        graph.addEdge(keys.get(2),keys.get(4));
        //graph.addEdge("Delta", "Casual", user4, "Echo","Casual",user5);
        //graph.addEdge("Delta", "Casual", user4, "Charlie","Casual",user3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRecommendation() {
        ArrayList<Vertex> keys = new ArrayList<>(graph.accounts.keySet());
        //System.out.println(graph.accounts.get(keys.get(0)));
        //System.out.println(user5.getFriends());
        FriendRecommender fRec = new FriendRecommender();
        ArrayList<User> recs = new ArrayList<>();
        recs = fRec.getRecommendation(user5);
        ArrayList<User> friends = new ArrayList<>();
        friends = user5.getFriends();
        for (int i = 0; i < recs.size(); i ++){
            //System.out.println(recs.get(i));
            friends.add(recs.get(i));
        }
        //System.out.println(friends.size());
        curUser.setFriends(friends);
        assertEquals(5,curUser.getFriends().size()); //adds repeat friends????
    }

    @Test
    void getRecommend() {
        ArrayList<Vertex> keys = new ArrayList<>(graph.accounts.keySet());
        System.out.println(keys);
        FriendRecommender fRec = new FriendRecommender();
        Vertex rec = fRec.getRecommend(keys.get(0), graph);
        assertEquals(keys.get(4),rec); //Get the most common friend between your friends

    }
}*/

//        @BeforeEach
//    void setUp() {
//        graph = new Graph();
//        accountManager = new AccountManager();
//        curUser = new User("Alpha", "Casual");
//        curUser.setUsername("Alpha");
//        graph.addVertex(curUser);
//        accountManager.addUser("Alpha", "123","mail","Casual");
//
//        user2 = new User("Beta", "Casual");
//        curUser.setUsername("Beta");
//        graph.addVertex(user2);
//        accountManager.addUser("Beta", "123","mail","Casual");
//
//        user3 = new User("Charlie", "Casual");
//        curUser.setUsername("Charlie");
//        graph.addVertex(user3);
//        accountManager.addUser("Charlie", "123","mail","Casual");
//
//        user4 = new User("Delta", "Casual");
//        curUser.setUsername("Delta");
//        //Vertex ver4 = new Vertex("Delta", "Casual", user4);
//        graph.addVertex(user4);
//        accountManager.addUser("Delta", "123","mail","Casual");
//
//        user5 = new User("Echo", "Casual");
//        curUser.setUsername("Echo");
//        //Vertex ver5 = new Vertex("Echo", "Casual", user5);
//        graph.addVertex(user5);
//        accountManager.addUser("Echo", "123","mail","Casual");
//
//        alphaFriends = new ArrayList<>();
//        alphaFriends.add(user2);
//        alphaFriends.add(user3);
//        curUser.setFriends(alphaFriends);
//        accountManager.addFriend(curUser, user2);
//        accountManager.addFriend(curUser,user3);
//        ArrayList<User> keys = new ArrayList<>(graph.accounts.keySet());
//        graph.addEdge(keys.get(0),keys.get(1));
//        graph.addEdge(keys.get(0),keys.get(2));
//        graph.addEdge(keys.get(0),keys.get(3));
//        //System.out.println(graph.getVertices());
//        graph.addEdge(curUser, user2);
//        graph.addEdge(curUser, user3);
//
//        deltaFriends = new ArrayList<>();
//        deltaFriends.add(user3);
//        deltaFriends.add(user5);
//        user5.setFriends(deltaFriends);
//        accountManager.addFriend(user4, user3);
//        accountManager.addFriend(user4,user5);
//        graph.addEdge(keys.get(3),keys.get(4));
//        graph.addEdge(keys.get(3),keys.get(2));
//        graph.addEdge(keys.get(2),keys.get(4));
//        graph.addEdge(user4, user5);
//        graph.addEdge(user4, user3);
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void getRecommendation() {
//        ArrayList<User> keys = new ArrayList<>(graph.accounts.keySet());
//        //System.out.println(graph.accounts.get(keys.get(0)));
//        //System.out.println(user5.getFriends());
//        FriendRecommender fRec = new FriendRecommender();
//        ArrayList<User> recs = new ArrayList<>();
//        recs = fRec.getRecommendation(user5);
//        ArrayList<User> friends = new ArrayList<>();
//        friends = user5.getFriends();
//        for (int i = 0; i < recs.size(); i ++){
//            //System.out.println(recs.get(i));
//            friends.add(recs.get(i));
//        }
//        //System.out.println(friends.size());
//        curUser.setFriends(friends);
//        assertEquals(5,curUser.getFriends().size()); //adds repeat friends????
//    }
//
//    @Test
//    void getRecommend() {
//        ArrayList<User> keys = new ArrayList<>(graph.accounts.keySet());
////        System.out.println(keys);
//        FriendRecommender fRec = new FriendRecommender();
//        User rec = fRec.getRecommend(keys.get(0), graph);
//        assertEquals(keys.get(4),rec); //Get the most common friend between your friends
//
//    }



