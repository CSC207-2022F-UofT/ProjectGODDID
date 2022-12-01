package PointSystem;

import Databases.ReadGraph;
import Databases.WriteGraph;
import entities.Graph;
import entities.User;
import useCases.ReadGraphInt;
import useCases.WriteGraphInt;

import java.io.IOException;
import java.util.Hashtable;

public class PointSystemR extends PointSystem{
    /**
     * PointSystemR, subclass of the abstract PointSystem class, is meant to implement the point manipulation methods
     * related to Renewing of points (via chats or games) .
     */

    public Hashtable<String, Integer> earnCases = new Hashtable<>();
    ReadGraphInt rg = new ReadGraph();
    WriteGraphInt wg = new WriteGraph();
    Graph users;

    public PointSystemR(){ // leaves scope to incorporate newer methods of earning points
        earnCases.put("ChatEnd", 10);
        earnCases.put("GameChatEnd", 15);
    }

    @Override
    public void adjustPoints(User X, String earnCase) throws IOException {
        if (earnCases.containsKey(earnCase)){
            users = rg.readobject();
            int newPoints = earnCases.get(earnCase);
            X.points += newPoints;
            users.accounts.put(X.getUsername(), X);
            wg.writeGraph(users);
        }
    }
}
