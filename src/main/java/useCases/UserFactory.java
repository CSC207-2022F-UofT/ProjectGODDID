package useCases;
import Databases.ReadGraph;
import Databases.WriteGraph;
import Interfaces.ReadGraphInt;
import Interfaces.WriteGraphInt;
import entities.Graph;
import entities.User;

public class UserFactory {


    /**
     * @param username
     * @param password
     * @return creates a user object and returns it following the factory design pattern
     */
    public User CreateUser(String username, String password) {
        User user = new User(username);
        ReadGraphInt rg = new ReadGraph();

        Graph user_graph = rg.readobject();

        if(!user_graph.accounts.containsKey(username))
        {
            user.setUsername(username);
            user.setPassword(password);
            user.setNum_strikes();
        }
        return user;
    }
}
