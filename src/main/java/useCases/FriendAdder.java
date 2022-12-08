package useCases;

import Databases.ReadGraph;
import Databases.WriteGraph;
import Interfaces.ReadGraphInt;
import Interfaces.WriteGraphInt;
import entities.*;

import java.io.IOException;

/*
Adds friend to current users list of friends as well as manipulates graph to create a new edge between the current user and the added friend
*/
public class FriendAdder {
    ReadGraphInt rg = new ReadGraph();
    WriteGraphInt wg = new WriteGraph();

    /**
     * @param currUser the user that wants to add a friend
     * @param friendToAdd the friend to be added
     * @throws IOException
     * @throws ClassNotFoundException
     */
    /*
    Add friend to current users friends list and create new edge between the two in the graph of users
    */
    public void addFriend(User currUser, User friendToAdd) throws IOException, ClassNotFoundException {
        Graph temp = rg.readobject();
        if(temp.accounts.containsKey(currUser.getUsername()) && temp.accounts.containsKey(friendToAdd.getUsername()))
        {
            currUser.friends.add(friendToAdd);
            friendToAdd.friends.add(currUser);
            temp.accounts.put(currUser.getUsername(), currUser);
            temp.accounts.put(friendToAdd.getUsername(), friendToAdd);
            wg.writeGraph(temp);
        }
    }
}
