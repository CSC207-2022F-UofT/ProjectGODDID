package useCases;

import Databases.ReadGraph;
import Databases.WriteGraph;
import Interfaces.ReadGraphInt;
import Interfaces.WriteGraphInt;
import entities.*;

import java.io.IOException;
import java.util.ArrayList;

/**
Adds friend to current users list of friends as well as manipulates graph to create a new edge between the current user and the added friend
*/
public class FriendRemover {
    ReadGraphInt rg = new ReadGraph();
    WriteGraphInt wg = new WriteGraph();

    ArrayList<User> friends1 = new ArrayList<>();

    ArrayList<User> friends2 = new ArrayList<>();

    public FriendRemover(){}
    /**
    Add friend to current users friends list and create new edge between the two in the graph of users
    */
    public void remove(User currUser, User friend) throws IOException, ClassNotFoundException {
        Graph users = rg.readobject();
        friends1 = currUser.getFriends();
        friend = users.accounts.get(friend.getUsername());
        friends2 = friend.getFriends();

        for (int i = 0; i < friends1.size(); i++){
            if (friends1.get(i).getUsername().equals(friend.getUsername())){
                friends1.remove(i);
            }
        }

        for (int i = 0; i < friends2.size(); i++){
            if (friends2.get(i).getUsername().equals(currUser.getUsername())){
                friends2.remove(i);
            }
        }

        currUser.setFriends(friends1);
        friend.setFriends(friends2);
        users.accounts.put(currUser.getUsername(), currUser);
        users.accounts.put(friend.getUsername(), friend);
        wg.writeGraph(users);
    }
}
