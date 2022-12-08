package PointSystem;

import Databases.ReadGraph;
import Databases.WriteGraph;
import entities.Graph;
import entities.User;
import Interfaces.ReadGraphInt;
import Interfaces.WriteGraphInt;

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

    /**
     * Constructor loads types of earn cases every tıme a PointSystemR object ıs ınıtlıazed.
     * Newer methods of earnıng can be easıly ıncorporated here.
     */
    public PointSystemR(){ // leaves scope to incorporate newer methods of earning points
        earnCases.put("ChatEnd", 10);
        earnCases.put("GameChatEnd", 15);
    }


    /**
     * adjustPoınts renews poınts for a gıven user X dependıng on the type of earn case
     * @param X
     * @param earnCase
     * @throws IOException
     */
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
