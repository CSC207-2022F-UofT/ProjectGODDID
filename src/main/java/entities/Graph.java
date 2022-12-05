package entities;
import java.io.Serializable;
import java.util.*;

/**
 * public class that represents the graph entity which is responsible for storing the users and their friends
 */
public class Graph  implements Serializable {
    public Map<String, User> accounts = new HashMap<>();


    /**
     * @return an arraylist of user from the graph
     */
    public ArrayList<User> getUsers()
    {
        return new ArrayList<>(this.accounts.values());
    }

    /**
     * @param username the username of the user that needs to be returned
     * @return the user from the graph who's user name corresponds to the parameter username
     */
    public User getUser(String username) {
        ArrayList<User> allusers = this.getUsers();
        for (User alluser : allusers) {
            if (Objects.equals(username, alluser.getUsername())) {
                return alluser;
            }
        }
        return null;
    }
}