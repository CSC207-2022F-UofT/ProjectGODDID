package controllers;

import entities.User;
import useCases.FriendRecommender;

import java.io.IOException;
import java.util.ArrayList;

public class FriendRecommenderController {
    private FriendRecommender friendRecommender = new FriendRecommender();

    public String getRandomRec(User target) throws IOException, ClassNotFoundException {
        ArrayList<String> friends = friendRecommender.getRecommendRandom(target);

        String recommended_friends = friends.get(0) + ", " + friends.get(1) + ", " + friends.get(2);

        return recommended_friends;
    }
    public String getRec(User target) throws IOException, ClassNotFoundException {
        return friendRecommender.getRecommend(target);
    }

}
