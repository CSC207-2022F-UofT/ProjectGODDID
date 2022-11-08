package entities;

import java.util.ArrayList;

public class User {
    private ArrayList<User> friends;
    private String username, email, password;
    private Boolean premium;
    private int points;

    public User(String name, String mail, String pWord, Boolean prem){
        points = 0;
        friends = new ArrayList<User>();
        username = name;
        email = mail;
        password = pWord;

    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getPoints() {
        return points;
    }

    public Boolean getPremium() {
        return premium;
    }
}
