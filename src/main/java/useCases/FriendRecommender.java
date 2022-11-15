package useCases;

import entities.*;

//import useCases.AccountManager.*;

import java.util.ArrayList;
import java.util.*;

public class FriendRecommender {
    public FriendRecommender(){

    }

    public ArrayList<User> getRecommendation(User user){
        ArrayList<User> recs = new ArrayList<>();
        for (int i = 0; i < 3; i ++) {
            int randomNum = (int)(Math.random()*user.getFriends().size());
            recs.add(user.getFriends().get(randomNum));
        }
        return recs;
    }

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
