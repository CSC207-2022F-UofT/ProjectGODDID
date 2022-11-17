package useCases;
import entities.User;

public class UserCreator {
    public User CreateUser(String username, String password) {
        User user = new User(username, "Casual");
        user.setUsername(username);
        user.setPassword(password);
        user.setBlocked_friends();
        user.setNum_strikes();
        return user;
    }
}