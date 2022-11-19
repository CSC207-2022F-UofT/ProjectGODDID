package PointSystem;

import entities.User;

import java.util.Hashtable;

public class PointSystemR extends PointSystem{
    /**
     * PointSystemR, subclass of the abstract PointSystem class, is meant to implement the point manipulation methods
     * related to Renewing of points (via chats or games).
     */

    public Hashtable<String, Integer> earnCases = new Hashtable<>();

    public PointSystemR(){ // leaves scope to incorporate newer methods of earning points
        earnCases.put("ChatEnd", 10);
        earnCases.put("GameChatEnd", 15);
    }

    @Override
    public void adjustPoints(User X, String earnCase) {
        if (earnCases.containsKey(earnCase)){
            int newPoints = earnCases.get(earnCase);
            X.points += newPoints;
        }
    }
}
