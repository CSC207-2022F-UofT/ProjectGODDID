package useCases;
import entities.User;

public class UserCreator {
    public User CreateUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}