package useCases;

import entities.*;
import java.util.ArrayList;
import java.util.*;

/*
Automated friend recommender system where a friend is recommended based on mutual friend count.
This use case occurs when a user interacts with the friend adder UI / interfaces.
*/
public class FriendRecommender {
    public FriendRecommender(){
    }

    /*
    Given a user, randomly select three of their friends to recommend back to the current user.
    */
    public ArrayList<User> getRecommendation(User user){
        ArrayList<User> recs = new ArrayList<>();
        for (int i = 0; i < 3; i ++) {
            int randomNum = (int)(Math.random()*user.getFriends().size());
            recs.add(user.getFriends().get(randomNum));
        }
        return recs;
    }

    /*
    Given a Vertex which represents a user and a Graph representing all the users in the app, search graph of
    users and finds highest count of mutual friends among the users neighbours.
    */
    public Vertex getRecommend(Vertex user, Graph allUsers){
        Vertex recs = new Vertex("","",new User());
        Map<Vertex, Integer> friends = new HashMap<>();

        for (Vertex i : allUsers.accounts.get(user)) {
            //friends.putIfAbsent(i, 0);
            for (Vertex j : allUsers.accounts.get(i)) {
                if (friends.get(j) == null) {
                    friends.putIfAbsent(i, 0);
                } else {
                    friends.put(j, friends.get(j) + 1);
                }
                //recs.add(user.getFriends().get(randomNum));
            }
        }

        int currMax = 0;
        for (Vertex a : friends.keySet()){
            if (friends.get(a) > currMax){
                for(Vertex keys : friends.keySet()) {
                    if(friends.get(keys).equals(friends.get(a))) {
                        recs = keys;
                    }
                }
            }
        }

        return recs;
    }

}
