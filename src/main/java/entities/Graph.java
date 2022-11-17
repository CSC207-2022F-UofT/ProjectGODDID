package entities;
import java.io.Serializable;
import java.util.*;

public class Graph  implements Serializable {
    public Map<User, List<User>> accounts = new HashMap<>();

    public ArrayList<User> getUsers()
    {
        return new ArrayList<>(this.accounts.keySet());
    }
}