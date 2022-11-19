package useCases;
import entities.*;
import UI.*;
import useCases.GraphToFile;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

//public class AccountManager extends User {
public class AccountManager{
    public static Graph user_graph = new Graph();//so that every time a new user is registered (while doing this
    //an object of account manager is created), each object of account manager refers to the same graph.
    public GraphToFile g = new GraphToFile();

    public void addUser(String name, String pWord, String acc_type) {
        User new_user = new User(name, acc_type);
        if(!user_graph.accounts.containsKey(new_user))
        {
            new_user.setUsername(name);
            new_user.setPassword(pWord);
            new_user.setAccountType(acc_type);
            new_user.setNum_strikes();
            new_user.setBlocked_friends();
            user_graph.accounts.putIfAbsent(new_user, new ArrayList<>());
        }
    }

    public void addUser(User user)
    {
        user_graph.accounts.putIfAbsent(user, new ArrayList<>());
    }

    public void addFriend(User currUser, User friendToAdd) throws IOException, ClassNotFoundException {
        user_graph = reader.readobject();
        if(user_graph.accounts.containsKey(currUser))
        {
            ArrayList<User> users = user_graph.getUsers();
            for (User i : users) {
                if (i.equals(currUser)) {
                    user_graph.accounts.get(currUser).add(friendToAdd);
                    writer.writefile(user_graph);
                    break;
                }
            }
        }
    }

    public void removeFriend(User currUser, User friendToRemove) {
        if(user_graph.accounts.containsKey(currUser))
        {
            ArrayList<User> users = user_graph.getUsers();
            for (User i : users) {
                if (i.equals(currUser)) {
                    user_graph.accounts.get(currUser).remove(friendToRemove);
                    break;
                }
            }
        }
    }

    public void removeUser(User userToBeRemoved)
    {
        if(user_graph.accounts.containsKey(userToBeRemoved))
        {
            user_graph.accounts.values().forEach(e -> e.remove(userToBeRemoved));
            user_graph.accounts.remove(userToBeRemoved);
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

    public void addGraphToFile(Graph graph)
    {
        g.AddToFile(graph);
    }

    public Graph readGraphFromFile() throws IOException, ClassNotFoundException {
        return g.ReadFromFile();
    }
}



