package controllers;

import entities.User;
import useCases.FriendRecommender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FriendRecommenderController {
    private FriendRecommender friendRecommender = new FriendRecommender();

    Random random = new Random();

    public String getRandomRec(User target, User curr) throws IOException, ClassNotFoundException {
        String friend = friendRecommender.getRecommendRandom(target, curr);

        return friend;
    }

    public String getRec(User target) throws IOException, ClassNotFoundException {

        return friendRecommender.getRecommend(target);

    }

}
