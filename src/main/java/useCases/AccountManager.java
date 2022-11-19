package useCases;
import entities.*;
import UI.*;

import java.lang.reflect.Array;
import java.util.*;

//public class AccountManager extends User {
public class AccountManager{
    public static Graph user_graph = new Graph();//so that every time a new user is registered (while doing this
    //an object of account manager is created), each object of account manager refers to the same graph.

    public void addUser(String name, String pWord, String acc_type) {
        User new_user = new User(name, acc_type);
        if(!user_graph.accounts.containsKey(new_user))
        {
            new_user.setUsername(name);
            new_user.setPassword(pWord);
            new_user.setAccountType(acc_type);
            new_user.setNum_strikes();
            new_user.setBlocked_friends();
            user_graph.accounts.putIfAbsent(name, new_user);
        }
    }

    public void addUser(User user)
    {
        user_graph.accounts.putIfAbsent(user.getUsername() ,user);
    }

    public void addFriend(User currUser, User friendToAdd) {
        if(user_graph.accounts.containsKey(currUser.getUsername()))
        {
            ArrayList<User> users = user_graph.getUsers();
            for (User i : users) {
                if (i.equals(currUser)) {
                    currUser.friends.add(friendToAdd);
                    break;
                }
            }
        }
    }

    public void removeFriend(User currUser, User friendToRemove) {
        if(user_graph.accounts.containsKey(currUser.getUsername()))
        {
            ArrayList<User> users = user_graph.getUsers();
            for (User i : users) {
                if (i.equals(currUser)) {
                    currUser.friends.remove(friendToRemove);
                    break;
                }
            }
        }
    }

    public void removeUser(User userToBeRemoved)
    {
        if(user_graph.accounts.containsKey(userToBeRemoved.getUsername()))
        {
            user_graph.accounts.remove(userToBeRemoved.getUsername());
        }
    }

    public void blockUser(User user1, User user2) {
        user_graph.getUsers();
        this.removeFriend(user1, user2);
        user1.addblocked(user2);
    }

    public Graph getGraph() {
        return user_graph;
    }

}



