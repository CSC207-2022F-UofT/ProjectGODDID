package PointSystem;

import Databases.ReadGraph;
import Databases.WriteGraph;
import entities.Graph;
import entities.User;
import Interfaces.ReadGraphInt;
import Interfaces.WriteGraphInt;

import java.io.IOException;
import java.util.Hashtable;

public class PointSystemS extends PointSystem{
    /**
     * PointSystemS, subclass of the abstract PointSystem Class, is meant to handle the point manipulation methods
     * related to Spending points (via skipping, choosing preference or extending chat) .
     */
    public Hashtable<String, Integer> spendCases = new Hashtable<>();
    User user;
    ReadGraphInt rg = new ReadGraph();
    WriteGraphInt wg = new WriteGraph();
    Graph users;

    /**
     * Constructor loads types of spend cases every tıme a PointSystemR object ıs ınıtlıazed.
     * Newer methods of spendıng can be easıly ıncorporated here.
     */
    public PointSystemS(){ // leaves scope for future developers to incorporate newer methods to spend points
        spendCases.put("SpendSkip", 10);
        spendCases.put("SpendChoose", 20);
        spendCases.put("SpendExtend", 20);
    }

    /**
     * adjustPoınts deducts poınts for a gıven user X dependıng on the type of earn case
     * @param X
     * @param spendCase
     * @throws IOException
     */

    @Override
    public void adjustPoints(User X, String spendCase) throws IOException {
        users = rg.readobject();
        if (! spendCases.containsKey(spendCase)){
            throw new Error("Invalid spend case");
        }

        else{
            int pointsRequired = spendCases.get(spendCase);

            if (X.points >= pointsRequired) {
                X.points -= pointsRequired;
                users.accounts.put(X.getUsername(), X);
                wg.writeGraph(users);
            }
        }
    }

}
