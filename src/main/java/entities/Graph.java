package entities;
import java.io.Serializable;
import java.util.*;

public class Graph  implements Serializable {
    public Map<User, List<User>> accounts = new HashMap<>();

    public ArrayList<User> getUsers()
    {
        return new ArrayList<>(this.accounts.keySet());
    }

    public User getUser(String username) {
        ArrayList<User> allusers = this.getUsers();
        for (int i = 0; i < allusers.size(); i++){
            if (username == allusers.get(i).getUsername()) {
                return allusers.get(i);
            }
        }
        return null;
    }
}