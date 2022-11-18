package useCases;
import Databases.*;

import entities.Graph;
import entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GraphFileTest {

    AccountManager accountManager = new AccountManager();
    User user1, user2;
    Graph gr = new Graph();
    ReadGraph rg = new ReadGraph();
    WriteGraph wg = new WriteGraph();



    @BeforeEach
    void setUp() throws IOException, ClassNotFoundException {

        user1 = new User("", "");
        user1.setUsername("Alpha");
        user1.setAccountType("casual");

        user2 = new User("beta", "casual");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void GraphTester() throws IOException, ClassNotFoundException {
        ArrayList<User> temp = new ArrayList<>();
        temp.add(user2);
        user1.setPoints(0);
        gr.accounts.put(user1, temp);
        wg.writefile(gr);

        System.out.println("");

        Graph tempGraph = rg.readobject();
        System.out.println("username:"+tempGraph.getUsers().get(0).getUsername());
        System.out.println("points:"+tempGraph.getUsers().get(0).getPoints());
        ArrayList<List<User>> a = new ArrayList<>(tempGraph.accounts.values());
        for(User u: a.get(0))
            System.out.println("friend:"+u.getUsername());

        System.out.println("");

        tempGraph.getUsers().get(0).setPoints(5);//changing the points of the first user
        wg.writefile(tempGraph);
        Graph tempGraph2 = rg.readobject();//reading it into a different graph
        System.out.println("username:"+tempGraph2.getUsers().get(0).getUsername());
        System.out.println("points:"+tempGraph2.getUsers().get(0).getPoints());
        ArrayList<List<User>> b = new ArrayList<>(tempGraph2.accounts.values());
        for(User u: b.get(0)){
            //u.setUsername("max");
            System.out.println("friend:"+u.getUsername());}



    }
}
