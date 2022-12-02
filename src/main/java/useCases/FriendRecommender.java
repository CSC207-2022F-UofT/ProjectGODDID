package useCases;

import entities.*;
import java.util.ArrayList;
import java.util.*;


/*
Automated friend recommender system where a friend is recommended based on mutual friend count.
This use case occurs when a user interacts with the friend adder UI / interfaces.
*/

public class FriendRecommender {
    private String recs; //Made change here

    public FriendRecommender() {
    }
/*
    Given a user, randomly select three of their friends to recommend back to the current user.
    */

    public ArrayList<String> getRecommendRandom(User user, User curr) {
        if (user.getFriends().size() >= 1) {
            ArrayList<String> recs = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                int randomNum = (int) (Math.random() * user.getFriends().size());
                if (user.getFriends().get(randomNum).getUsername().equals(curr.getUsername()) || recs.contains(user.getFriends().get(randomNum).getUsername())){

                }else{
                    recs.add(user.getFriends().get(randomNum).getUsername());
                }
            }

            return recs;
        }
        return new ArrayList<>();
    }


/*
    Given a Vertex which represents a user and a Graph representing all the users in the app, search graph of
    users and finds highest count of mutual friends among the users neighbours.
    */

    public String getRecommend(User user) {
        Map<String, Integer> friends = new HashMap<>();
        System.out.println(user.getUsername());
        for (User i : user.getFriends()) {
            int counter = 0;
            for (User j : i.getFriends()) {
                System.out.println(j.getUsername());
                if (!j.getUsername().equals(user.getUsername())) {
                    System.out.println(j.getUsername());
                    if (friends.containsKey(j.getUsername()) == false) {
                        friends.putIfAbsent(j.getUsername(), 0);
                    } else {
                        friends.put(j.getUsername(), friends.get(j.getUsername()) + 1);
                    }
                    counter++;
                }
            }
        }
        int currMax = 0;
        for (String a : friends.keySet()) {
            if (friends.get(a) >= currMax) {
                currMax = friends.get(a);
                for (String keys : friends.keySet()) {
                    if (friends.get(keys).equals(currMax)) {
                        recs = keys;
                    }
                }
            }
        }
        return recs;
    }
}