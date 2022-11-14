package useCases;

import entities.User;

import java.util.ArrayList;
import java.util.List;

public class FriendRequester {
    private int amountWanted;
    private ArrayList<User> recommendations;

    public FriendRequester(){
    }

    public void request(User currUser, User friend, int amount){
        for (User i : currUser.getFriends())
            if (i.getUsername().equals(friend.getUsername())){
                FriendRecommender friendRecommender = new FriendRecommender();
                recommendations = friendRecommender.getRecommendation(friend);
            }
    }

    public int getAmountWanted(){
        return amountWanted;
    }
}
