package useCases;
import Databases.ReadGraph;
import Databases.WriteGraph;
import Interfaces.ReadGraphInt;
import Interfaces.WriteGraphInt;
import entities.*;

import java.io.IOException;

//public class AccountManager extends User {
public class AccountManager{
    public static Graph user_graph = new Graph();//so that every time a new user is registered (while doing this
    //an object of account manager is created), each object of account manager refers to the same graph.
//    WriteGraph wg = new WriteGraph();
//    ReadGraph rg = new ReadGraph();
    ReadGraphInt rg = new ReadGraph();
    WriteGraphInt wg = new WriteGraph();


    public void addUser(String name, String pWord, String acc_type) throws IOException, ClassNotFoundException {
        User new_user = new User(name, acc_type);
        Graph temp = rg.readobject();
        if(!temp.accounts.containsKey(name))
        {
            new_user.setUsername(name);
            new_user.setPassword(pWord);
            new_user.setAccountType(acc_type);
            new_user.setNum_strikes();
            new_user.setBlocked_friends();
            temp.accounts.putIfAbsent(name, new_user);
            user_graph = temp;
            wg.writeGraph(temp);
        }
    }

    public void addUser(User user) throws IOException, ClassNotFoundException {
        Graph user_graph = rg.readobject();
        user_graph.accounts.putIfAbsent(user.getUsername() ,user);
        wg.writeGraph(user_graph);
    }

    public void removeUser(User userToBeRemoved) throws IOException, ClassNotFoundException {
        Graph user_graph = rg.readobject();
        user_graph.accounts.remove(userToBeRemoved.getUsername());
        userToBeRemoved.friends.clear();
        wg.writeGraph(user_graph);
    }

//    public void blockUser(User user1, User user2) throws IOException, ClassNotFoundException {
//        Graph user_graph = rg.readobject();
//        user_graph.getUsers();
//        this.removeFriend(user1, user2);
//        user1.addblocked(user2);
//        wg.writeGraph(user_graph);
//    }

    public Graph getGraph() {
        return user_graph;
    }



}



