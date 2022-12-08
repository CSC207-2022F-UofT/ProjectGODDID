package useCases;
import Databases.ReadGraph;
import Interfaces.ReadGraphInt;

import entities.Graph;
import entities.User;

public class UserFactory {


    /**
     * @param username username of the user
     * @param password of the user
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
            user.setNum_strikes(0);
        }
        return user;
    }
}
