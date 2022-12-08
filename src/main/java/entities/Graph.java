package entities;
import java.io.Serializable;
import java.util.*;

public class Graph  implements Serializable {
    /**
     * Creates a hashmap that includes the all of the users accounts refer to the vertices
     */
    public Map<String, User> accounts = new HashMap<>();


    /**
     *
     * @return all the users in the accounts hashmap
     */
    public ArrayList<User> getUsers()
    {
        return new ArrayList<>(this.accounts.values());
    }

    /**
     *
     * @param username of the user
     * @return the specific chosen user from the graph
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