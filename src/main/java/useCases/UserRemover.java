package useCases;

import Databases.ReadGraph;
import Databases.WriteGraph;
import Interfaces.ReadGraphInt;
import Interfaces.WriteGraphInt;
import entities.Graph;
import entities.User;

import java.io.IOException;
import java.util.ArrayList;

public class UserRemover {

    /**
     * Remove a user from the system completely, also ensures the removal of the user from the friends of
     * other users.
     * @param userToBeRemoved the user that needs to be removed from the system
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public void removeUser(User userToBeRemoved) throws IOException {
        ReadGraphInt rg = new ReadGraph();
        WriteGraphInt wg = new WriteGraph();
        Graph user_graph = rg.readobject();
        user_graph.accounts.remove(userToBeRemoved.getUsername());
        for(User i:user_graph.accounts.values()){
            int j = 0;
            for (User removal : i.getFriends()){
                if (removal.getUsername().equals(userToBeRemoved.getUsername())){
                    ArrayList<User> friends = user_graph.accounts.get(i).getFriends();
                    friends.remove(j);
                    i.setFriends(friends);
                    user_graph.accounts.put(i.getUsername(), i);
                }
                j += 1;
            }
        }
        userToBeRemoved.friends.clear();
        wg.writeGraph(user_graph);
    }
}
