package useCases;

import entities.*;
import java.util.ArrayList;
import java.util.*;

/*
Automated friend recommender system where a friend is recommended based on mutual friend count.
This use case occurs when a user interacts with the friend adder UI / interfaces.
*/
public class FriendRecommender {
    private User recs; //Made change here
    public FriendRecommender(){
    }

    /*
    Given a user, randomly select three of their friends to recommend back to the current user.
    */
    public ArrayList<User> getRecommendation(User user){
        if (user.getFriends().size() >= 1) {
            ArrayList<User> recs = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                int randomNum = (int) (Math.random() * user.getFriends().size());
                recs.add(user.getFriends().get(randomNum));
            }

            return recs;
        }
        return new ArrayList<>();
    }

    /*
    Given a Vertex which represents a user and a Graph representing all the users in the app, search graph of
    users and finds highest count of mutual friends among the users neighbours.
    */
    public User getRecommend(User user, Graph allUsers){
        //Vertex recs = new Vertex("","",new User());
        Map<User, Integer> friends = new HashMap<>();
        for (User i : allUsers.accounts.get(user)) {
            //friends.putIfAbsent(i, 0);
            System.out.println(allUsers.accounts.get(i));
            int counter = 0;
            for (User j : allUsers.accounts.get(i)) {
                System.out.println(friends.containsKey(j));
                if (friends.containsKey(j) == false) {
                    friends.putIfAbsent(allUsers.accounts.get(i).get(counter), 0);
                } else {
                    friends.put(j, friends.get(j) + 1);
                }
                counter ++;
                //recs.add(user.getFriends().get(randomNum));
            }
        }

        //System.out.println("working");
        System.out.println(friends.keySet());
        int currMax = 0;
        for (User a : friends.keySet()){
            if (friends.get(a) >= currMax){
                System.out.println(friends.get(a));
                currMax = friends.get(a);
                System.out.println(currMax);
                for(User keys : friends.keySet()) {
                    if(friends.get(keys).equals(friends.get(a))) {
                        recs = keys;
                        //System.out.println(recs);
                    }
                }
            }
        }
        return recs;
    }

//    public User getRecommend(User user, Graph allUsers){
//        //Vertex recs = new Vertex("","",new User());
//        Map<User, Integer> friends = new HashMap<>();
//        for (User i : allUsers.accounts.get(user)) {
//            //friends.putIfAbsent(i, 0);
//            System.out.println(allUsers.accounts.get(i));
//            int counter = 0;
//            for (User j : allUsers.accounts.get(i)) {
//                System.out.println(friends.containsKey(j));
//                if (friends.containsKey(j) == false) {
//                    friends.putIfAbsent(allUsers.accounts.get(i).get(counter), 0);
//                } else {
//                    friends.put(j, friends.get(j) + 1);
//                }
//                counter ++;
//                //recs.add(user.getFriends().get(randomNum));
//            }
//        }
//
//        //System.out.println("working");
//        System.out.println(friends.keySet());
//        int currMax = 0;
//        for (User a : friends.keySet()){
//            if (a != user) {
//                if (friends.get(a) >= currMax) {
//                    System.out.println(friends.get(a));
//                    currMax = friends.get(a);
//                    System.out.println(currMax);
//                    for (User keys : friends.keySet()) {
//                        if (friends.get(keys).equals(friends.get(a))) {
//                            recs = keys;
//                            //System.out.println(recs);
//                        }
//                    }
//                }
//            }
//        }
//        return recs;
//    }

}
