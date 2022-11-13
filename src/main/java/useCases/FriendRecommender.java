package useCases;

import entities.User;

import java.util.ArrayList;

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

}
