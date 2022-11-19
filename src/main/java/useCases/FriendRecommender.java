
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

    public ArrayList<String> getRecommendation(User user) {
        if (user.getFriends().size() >= 1) {
            ArrayList<String> recs = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                int randomNum = (int) (Math.random() * user.getFriends().size());
                recs.add(user.getFriends().get(randomNum).getUsername());
            }

            return recs;
        }
        return new ArrayList<>();
    }


/*
    Given a Vertex which represents a user and a Graph representing all the users in the app, search graph of
    users and finds highest count of mutual friends among the users neighbours.
    */

    public String getRecommend(User user, Graph allUsers) {
        //Vertex recs = new Vertex("","",new User());
        Map<String, Integer> friends = new HashMap<>();
        for (User i : allUsers.getUsers()) {
            //friends.putIfAbsent(i, 0);
            System.out.println(allUsers.accounts.get(i));
            int counter = 0;
            for (User j : i.getFriends()) {
                System.out.println(friends.containsKey(j.getUsername()));
                if (friends.containsKey(j.getUsername()) == false) {
                    friends.putIfAbsent(i.getUsername(), 0);
                } else {
                    friends.put(j.getUsername(), friends.get(j.getUsername()) + 1);
                }
                counter++;
                //recs.add(user.getFriends().get(randomNum));
            }
        }
        System.out.println(friends.keySet());
        int currMax = 0;
        for (String a : friends.keySet()) {
            if (friends.get(a) >= currMax) {
                System.out.println(friends.get(a));
                currMax = friends.get(a);
                System.out.println(currMax);
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

//    public User helper(HashMap<User, Integer> map, ArrayList<User> maxuserlist) throws IndexOutOfBoundsException{
//        Integer max = - 1;
//        maxuserlist.set(0, new User("Bob", "Casual"));
//        for (User user: map.keySet()){
//            if (map.get(user) > max) {
//                if (maxuserlist.size() > 0) {
//                    maxuserlist.set(0, user);
//                } else {
//                    maxuserlist.set(0, user);
//                }
//                max = map.get(user);
//            }
//        }
//        return maxuserlist.get(0);
//    }
//
//
//    public User hetRecommended(User user){
//        ArrayList<User> maxuserlist = new ArrayList<User>();
//        ArrayList<User> counterchecker = new ArrayList<User>();
//        HashMap<User, Integer> counter = new HashMap<User, Integer>();
//        ArrayList<User> friends = user.getFriends();
//        if (friends.size() != 0) {
//            for (User friend : friends) {
//                if (friend.getFriends().size() != 0) {
//                    ArrayList<User> mutuals = friend.getFriends();
//                    if (mutuals.size() > 0) {
//                        for (User mutual : mutuals) {
//                            if (!(friends.contains(mutual) && user != mutual)) {
//                                if (counterchecker.contains(mutual)) {
//                                    Integer temp = counter.get(mutual);
//                                    temp += 1;
//                                    counter.remove(mutual);
//                                    counter.put(mutual, temp);
//                                } else {
//                                    Integer x = 1;
//                                    counter.put(mutual, x);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return helper(counter, maxuserlist);
//    }


