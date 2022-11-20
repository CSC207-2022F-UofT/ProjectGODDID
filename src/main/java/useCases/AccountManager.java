package useCases;
import Databases.ReadGraph;
import Databases.WriteGraph;
import entities.*;
import UI.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

//public class AccountManager extends User {
public class AccountManager{

    WriteGraph wg = new WriteGraph();
    static ReadGraph rg = new ReadGraph();
    public static Graph user_graph; //so that every time a new user is registered (while doing this

    static {
        try {
            user_graph = rg.readobject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    //an object of account manager is created), each object of account manager refers to the same graph.


    public AccountManager() throws IOException, ClassNotFoundException {
    }

    public void addUser(String name, String pWord, String acc_type) throws IOException, ClassNotFoundException {
        User new_user = new User(name, acc_type);
        if(!user_graph.accounts.containsKey(name))
        {
            new_user.setUsername(name);
            new_user.setPassword(pWord);
            new_user.setAccountType(acc_type);
            new_user.setNum_strikes();
            new_user.setBlocked_friends();
            user_graph.accounts.putIfAbsent(name, new_user);
            wg.writeGraph(user_graph);
        }
    }

    public void addUser(User user) throws IOException, ClassNotFoundException {

        user_graph.accounts.putIfAbsent(user.getUsername() ,user);
        wg.writeGraph(user_graph);
    }

    public void addFriend(User currUser, User friendToAdd) throws IOException, ClassNotFoundException {
        if(user_graph.accounts.containsKey(currUser.getUsername()))
        {
            currUser.getFriends().add(friendToAdd);
            user_graph.accounts.put(currUser.getUsername(), currUser);
            wg.writeGraph(user_graph);

        }
    }

    public void removeFriend(User currUser, User friendToRemove) throws IOException, ClassNotFoundException {
        if(user_graph.accounts.containsKey(currUser.getUsername()))
        {
            ArrayList<User> users = user_graph.getUsers();
            for (User i : users) {
                if (i.equals(currUser)) {
                    currUser.friends.remove(friendToRemove);
                    wg.writeGraph(user_graph);
                    break;
                }
            }
        }
    }

    public void removeUser(User userToBeRemoved) throws IOException, ClassNotFoundException {
        user_graph.accounts.remove(userToBeRemoved.getUsername());
        userToBeRemoved.friends.clear();
        wg.writeGraph(user_graph);
    }

    public void blockUser(User user1, User user2) throws IOException, ClassNotFoundException {
        user_graph.getUsers();
        this.removeFriend(user1, user2);
        user1.addblocked(user2);
        wg.writeGraph(user_graph);
    }

    public Graph getGraph() {
        return user_graph;
    }

}