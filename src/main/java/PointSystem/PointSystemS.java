package PointSystem;

import entities.User;

import java.util.Hashtable;

public class PointSystemS extends PointSystem{
    /**
     * PointSystemS, subclass of the abstract PointSystem Class, is meant to handle the point manipulation methods
     * related to Spending points (via skipping, choosing preference or extending chat) .
     */
    public Hashtable<String, Integer> spendCases = new Hashtable<>();

    public PointSystemS(){ // leaves scope for future developers to incorporate newer methods to spend points
        spendCases.put("SpendSkip", 10);
        spendCases.put("SpendChoose", 20);
        spendCases.put("SpendExtend", 20);
    }

    @Override
    public void adjustPoints(User X, String spendCase){
        if (! spendCases.containsKey(spendCase)){
            throw new java.lang.Error("Invalid spend case");
        }

        else{
            int pointsRequired = spendCases.get(spendCase);

            if (X.points >= pointsRequired) {
                X.points -= pointsRequired;
            }
        }
    }

}
