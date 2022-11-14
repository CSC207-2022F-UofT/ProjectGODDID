package PointsSystem;

import entities.User;
import java.util.Hashtable;



public class PointsSystem{
    public static Hashtable<String, Integer> spendCases;

    public static Hashtable<String, Integer> earnCases;

    public static void loadSpendCases(){ // leaves scope for future developers to incorporate newer methods to spend points
        spendCases.put("SpendSkip", 10);
        spendCases.put("SpendChoose", 20);
        spendCases.put("SpendExtend", 20);
    }
    public static void loadEarnCases(){ // leaves scope to incorporate newer methods of earning points
        earnCases.put("ChatEnd", 10);
        earnCases.put("GameChatEnd", 15);
    }



    public static String PointSpender(User X, String spendCase){
        if (! spendCases.contains(spendCase)){
            return "Invalid choice for spending points";
        }

        else{
            int pointsRequired = spendCases.get(spendCase);

            if (X.points >= pointsRequired){
                X.points -= pointsRequired;
                return "Spend successful";
            }

        }
        return "Inadequate Points ";
    }
    public static String PointRenewer(User X, String earnCase){
        if (! earnCases.contains(earnCase)){
            return "Invalid way to earn points";
        }

        else{
            int newPoints = earnCases.get(earnCase);
            X.points += newPoints;
            return "Points renewed successfully"
        }
        return "Points could not be renewed";
    }




}
