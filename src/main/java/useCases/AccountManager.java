package useCases;
import Databases.ReadFile;
import Databases.WriteFile;
import entities.*;
import UI.*;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

//public class AccountManager extends User {
public class AccountManager implements Serializable {
    public static Graph user_graph = new Graph();//so that every time a new user is registered (while doing this
    //an object of account manager is created), each object of account manager refers to the same graph.
    ReadFile reader = new ReadFile();
    WriteFile writer = new WriteFile();

    public void addUser(String name, String pWord, String acc_type) throws IOException, ClassNotFoundException {
        User new_user = new User(name, acc_type);
        user_graph = reader.readobject();
        if(!user_graph.accounts.containsKey(new_user))
        {
            new_user.setUsername(name);
            new_user.setPassword(pWord);
            new_user.setAccountType(acc_type);
            new_user.setNum_strikes();
            new_user.setBlocked_friends();
            user_graph.accounts.putIfAbsent(new_user, new ArrayList<>());
            writer.writefile(user_graph);
        }
    }

    public void addUser(User user) throws IOException, ClassNotFoundException {
        user_graph = reader.readobject();
        user_graph.accounts.putIfAbsent(user, new ArrayList<>());
        writer.writefile(user_graph);
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

    public void removeFriend(User currUser, User friendToRemove) throws IOException, ClassNotFoundException {
        user_graph = reader.readobject();
        if(user_graph.accounts.containsKey(currUser))
        {
            ArrayList<User> users = user_graph.getUsers();
            for (User i : users) {
                if (i.equals(currUser)) {
                    user_graph.accounts.get(currUser).remove(friendToRemove);
                    writer.writefile(user_graph);
                    break;
                }
            }
        }
    }

    public void removeUser(User userToBeRemoved) throws IOException, ClassNotFoundException {
        user_graph = reader.readobject();
        if(user_graph.accounts.containsKey(userToBeRemoved))
        {
            user_graph.accounts.values().forEach(e -> e.remove(userToBeRemoved));
            user_graph.accounts.remove(userToBeRemoved);
            writer.writefile(user_graph);
        }
    }

    public void blockUser(User user1, User user2) throws IOException, ClassNotFoundException {
        user_graph = reader.readobject();
        user_graph.getUsers();
        this.removeFriend(user1, user2);
        user1.addblocked(user2);
        writer.writefile(user_graph);
    }

    public Graph getGraph() throws IOException, ClassNotFoundException {
        user_graph = reader.readobject();
        return user_graph;
    }

}

