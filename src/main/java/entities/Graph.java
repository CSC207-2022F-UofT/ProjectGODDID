package entities;
import java.io.Serializable;
import java.util.*;

public class Graph  implements Serializable {
    public Map<String, User> accounts = new HashMap<>();

    public ArrayList<User> getUsers()
    {
        return new ArrayList<>(this.accounts.values());
    }

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