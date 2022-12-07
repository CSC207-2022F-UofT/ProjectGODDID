package useCases;
import Databases.ReadGraph;
import Databases.WriteGraph;
import Interfaces.ReadGraphInt;
import Interfaces.WriteGraphInt;
import entities.*;

import java.io.IOException;


public class AccountManbackup{
    public static Graph user_graph = new Graph();//so that every time a new user is registered (while doing this
    //an object of account manager is created), each object of account manager refers to the same graph.

    ReadGraphInt rg = new ReadGraph();
    WriteGraphInt wg = new WriteGraph();


    public void addUser(String name, String pWord) throws IOException, ClassNotFoundException {
        ReadGraphInt read_graph = new ReadGraph();
        WriteGraphInt write_graph = new WriteGraph();

        UserFactory userCreator = new UserFactory();

        Graph user_graph = read_graph.readobject();
        User user = userCreator.CreateUser(name, pWord);
        user_graph.accounts.put(user.getUsername(), user);

        write_graph.writeGraph(user_graph);

    }

    public void addUser(User user) throws IOException, ClassNotFoundException {
        Graph user_graph = rg.readobject();
        user_graph.accounts.putIfAbsent(user.getUsername() ,user);
        wg.writeGraph(user_graph);
    }

    /**
     * Adds a friend to a user, if the friend is a current user and not already a friend of the user
     * @param currUser the user who wants to add a friend
     * @param friendToAdd the friend that currUser wants to add
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void addFriend(User currUser, User friendToAdd) throws IOException, ClassNotFoundException {
        Graph temp = rg.readobject();
        if(temp.accounts.containsKey(currUser.getUsername()) && temp.accounts.containsKey(friendToAdd.getUsername()))
        {
            currUser.friends.add(friendToAdd);
            friendToAdd.friends.add(currUser);
            temp.accounts.put(currUser.getUsername(), currUser);
            temp.accounts.put(friendToAdd.getUsername(), friendToAdd);
            user_graph = temp;
            wg.writeGraph(user_graph);
        }
    }



    /**
     * Removes a friend from a user's set of friends, if the friend exists.
     * @param currUser the user who wants to remove a friend
     * @param friendToRemove the friend that currUser wants to remove
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void removeFriend(User currUser, User friendToRemove) throws IOException, ClassNotFoundException {
        Graph user_graph = rg.readobject();
        if(user_graph.accounts.containsKey(currUser.getUsername()))
        {
            currUser.friends.remove(friendToRemove);
            user_graph.accounts.put(currUser.getUsername(), currUser);
            wg.writeGraph(user_graph);
        }
    }



    /**
     * Remove a user from the system completely, also ensures the removal of the user from the friends of
     * other users.
     * @param userToBeRemoved the user that needs to be removed from the system
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void removeUser(User userToBeRemoved) throws IOException, ClassNotFoundException {
        Graph user_graph = rg.readobject();
        user_graph.accounts.remove(userToBeRemoved.getUsername());
        for(User i:user_graph.accounts.values()){
            if(i.getFriends().contains(userToBeRemoved))
                i.friends.remove(userToBeRemoved);
        }
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
