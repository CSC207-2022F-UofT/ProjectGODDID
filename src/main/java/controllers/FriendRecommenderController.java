package controllers;

import entities.User;
import useCases.FriendRecommender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FriendRecommenderController {
    private FriendRecommender friendRecommender = new FriendRecommender();

    Random random = new Random();

    public String getRandomRec(User curr) throws IOException, ClassNotFoundException {

        int index = 0;
        if (curr.getFriends().size() > 0) {
            index = random.nextInt(curr.getFriends().size() - 1);
            ArrayList<String> friends = friendRecommender.getRecommendRandom(curr.getFriends().get(index), curr);
            String recommended_friends = friends.get(0) + ", " + friends.get(1) + ", " + friends.get(2);

            return recommended_friends;
        }
        return "Add a friend to get recommendation";
    }

//    public void getRandomRec(User target, User curr) throws IOException, ClassNotFoundException {
//        friendRecommender.getRecommendRandom(target, curr);
//    }
    public String getRec(User target) throws IOException, ClassNotFoundException {
        int index = 0;
        if (target.getFriends().size() > 0) {
            index = random.nextInt(target.getFriends().size() - 1);
            return friendRecommender.getRecommend(target.getFriends().get(index));
        }

        return "Add a friend to get recommendation";
    }

}
