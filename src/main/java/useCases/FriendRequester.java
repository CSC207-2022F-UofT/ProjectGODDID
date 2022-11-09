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
            if (i.getUsername() == friend.getUsername()){
                FriendRecommender friendRecommender = new FriendRecommender();
                //friendRecommender.getRecommendation(friend);
            }
        User user = new User();
        //user.setPassword(pWord);
        //user.setUsername(name);
        //user.setEmail(mail);
        System.out.println("User made: " + user.getEmail());
    }

    public int getAmountWanted(){
        return amountWanted;
    }
}
