package entities;

import java.util.ArrayList;

public class User {
    private ArrayList<User> friends;
    private String username, email, password;
    private Boolean premium;
    private int points;



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
