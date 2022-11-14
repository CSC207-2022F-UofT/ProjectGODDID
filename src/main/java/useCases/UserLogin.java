package useCases;

import entities.User;

public class UserLogin {

    public void login(User user){
        User currUser = user;
        System.out.println("User login" + currUser.getUsername());
    }
}
