package useCases;
import Databases.ReadGraph;
import Databases.WriteGraph;
import Interfaces.ReadGraphInt;
import Interfaces.WriteGraphInt;
import entities.*;

import java.io.IOException;

/**
 * Public class that is responsible for the addition and removal of users and user's friends.
 * Contains a static Graph object which is initialized so in order to ensure that the same graph is
 * refered to each time an accountmanager object is made.
 */
public class AccountManager{
    public static Graph user_graph = new Graph();

    ReadGraphInt rg = new ReadGraph();
    WriteGraphInt wg = new WriteGraph();


    /**
     * Adds a user, only if they do not already exist
     * @param name name of the user
     * @param pWord password of the user
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public void addUser(String name, String pWord) throws IOException, ClassNotFoundException {
        ReadGraphInt read_graph = new ReadGraph();
        WriteGraphInt write_graph = new WriteGraph();

        UserFactory userCreator = new UserFactory();

        Graph user_graph = read_graph.readobject();
        User user = userCreator.CreateUser(name, pWord);
        user_graph.accounts.putIfAbsent(user.getUsername(), user);

        write_graph.writeGraph(user_graph);

    }

    /**
     * Adds a user, only if they do not already exist
     * @param user the user object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void addUser(User user) throws IOException, ClassNotFoundException {
        Graph user_graph = rg.readobject();
        user_graph.accounts.putIfAbsent(user.getUsername(), user);
        wg.writeGraph(user_graph);
    }

    /**
     * Remove a user from the system completely, also ensures the removal of the user from the friends of
     * other users. Calls the UserRemover class which follows the Facade design principle since the changes between them
     * do not have a hard dependence
     * @param userToBeRemoved the user that needs to be removed from the system
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void removeUser(User userToBeRemoved) throws IOException, ClassNotFoundException {
        UserRemover remover = new UserRemover();
        remover.removeUser(userToBeRemoved);
    }

//    public void removeUser(User userToBeRemoved) throws IOException, ClassNotFoundException {
//        Graph user_graph = rg.readobject();
//        user_graph.accounts.remove(userToBeRemoved.getUsername());
//        for(User i:user_graph.accounts.values()){
//            if(i.getFriends().contains(userToBeRemoved))
//                i.friends.remove(userToBeRemoved);
//        }
//        userToBeRemoved.friends.clear();
//        wg.writeGraph(user_graph);
//    }

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
            for (int i = 0; i < currUser.getFriends().size(); i++){
                if (currUser.getFriends().get(i).getUsername().equals(friendToRemove.getUsername())) {
                    currUser.friends.remove(i);
                    user_graph.accounts.put(currUser.getUsername(), currUser);
                    break;
                }
            }
//            currUser.friends.remove(friendToRemove);
//            user_graph.accounts.put(currUser.getUsername(), currUser);
            wg.writeGraph(user_graph);
        }
    }

    /**
     * @return the graph that contains all the users and friends.
     */
    public Graph getGraph() {
        return user_graph;
    }



}


