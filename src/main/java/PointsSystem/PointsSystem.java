package PointsSystem;

import entities.User;

import java.util.Hashtable;



public class PointsSystem{
    public Hashtable<String, Integer> spendCases = new Hashtable<>();

    public Hashtable<String, Integer> earnCases = new Hashtable<>();


    public void loadSpendCases(){ // leaves scope for future developers to incorporate newer methods to spend points
        spendCases.put("SpendSkip", 10);
        spendCases.put("SpendChoose", 20);
        spendCases.put("SpendExtend", 20);
    }
    public void loadEarnCases(){ // leaves scope to incorporate newer methods of earning points
        earnCases.put("ChatEnd", 10);
        earnCases.put("GameChatEnd", 15);
    }



    public void PointSpender(User X, String spendCase){
        if (! spendCases.containsKey(spendCase)){
            throw new java.lang.Error("Invalid spend case");
        }

        else{
            int pointsRequired = spendCases.get(spendCase);

            if (X.getPoints() >= pointsRequired){
                X.points -= pointsRequired;
            }

        }
    }
    public void PointRenewer(User X, String earnCase) {
        if (earnCases.containsKey(earnCase)){
        int newPoints = earnCases.get(earnCase);
        X.points += newPoints;
        }
    }




}
